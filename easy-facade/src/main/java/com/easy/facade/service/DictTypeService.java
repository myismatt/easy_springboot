package com.easy.facade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.DictTypeDTO;
import com.easy.facade.beans.dto.DictTypeSearchDTO;
import com.easy.facade.beans.dto.DictTypeUpdateDTO;
import com.easy.facade.beans.model.DictData;
import com.easy.facade.beans.model.DictType;
import com.easy.facade.beans.vo.DictDataVO;
import com.easy.facade.beans.vo.DictTypeVO;
import com.easy.facade.constant.RedisKeyConsts;
import com.easy.facade.dao.DictTypeMapper;
import com.easy.facade.enums.YesOrNoEnum;
import com.easy.facade.framework.exception.CustomizeException;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.utils.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 字典类型
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:35
 */
@Service
public class DictTypeService extends ServiceImpl<DictTypeMapper, DictType> {

    private final DictDataService dictDataService;
    private final RedisUtils redisUtils;

    public DictTypeService(DictDataService dictDataService, RedisUtils redisUtils) {
        this.dictDataService = dictDataService;
        this.redisUtils = redisUtils;
    }

    /**
     * 初始化缓存
     */
    public void initDictCache() {
        // 查询所有字典类型
        List<DictTypeVO> dictTypeList = this.baseMapper.selectAllDictType(YesOrNoEnum.YES);
        // 查询所有字典数据
        List<DictDataVO> dictDataList = dictDataService.getBaseMapper().selectAllDictData(YesOrNoEnum.YES);
        // 按字典类型对数据进行分组
        Map<String, List<DictDataVO>> dictDataMap = dictDataList.stream().collect(Collectors.groupingBy(DictDataVO::getDictType));
        // 数据筛选组装
        for (DictTypeVO dictTypeVO : dictTypeList) {
            dictTypeVO.setDictDataList(dictDataMap.getOrDefault(dictTypeVO.getDictType(), new ArrayList<>()));
        }
        // 设置缓存
        redisUtils.setCacheObject(RedisKeyConsts.DICT_KEY, dictTypeList);
    }

    /**
     * 获取字典信息
     *
     * @param dictType 字典类型
     * @return 字典数据
     */
    public Optional<DictTypeVO> getDictTypeInfo(String dictType) {
        List<DictTypeVO> cacheList = redisUtils.getCacheObject(RedisKeyConsts.DICT_KEY);
        if (CollectionUtils.isEmpty(cacheList)) {
            throw new CustomizeException("未查询到字典");
        }
        return cacheList.stream().filter(cache -> cache.getDictType().equals(dictType)).findFirst();
    }

    /**
     * 集合查询
     *
     * @param dto 查询入参
     * @return 分页数据
     */
    public List<DictType> listDictType(DictTypeSearchDTO dto) {
        return lambdaQuery().eq(StringUtils.isNotBlank(dto.getDictName()), DictType::getDictName, dto.getDictName())
                .eq(StringUtils.isNotBlank(dto.getDictType()), DictType::getDictType, dto.getDictType()).list();
    }

    /**
     * 分页查询
     *
     * @param dto 查询入参
     * @return 分页数据
     */
    public IPage<DictType> pageDictType(DictTypeSearchDTO dto) {
        return lambdaQuery().eq(StringUtils.isNotBlank(dto.getDictName()), DictType::getDictName, dto.getDictName())
                .eq(StringUtils.isNotBlank(dto.getDictType()), DictType::getDictType, dto.getDictType())
                .page(new Page<DictType>().setCurrent(dto.getCurrent()).setSize(dto.getSize()));
    }

    /**
     * 新增字典类型
     *
     * @param dto 新增入参
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDictType(DictTypeDTO dto) {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(dto, dictType);
        this.save(dictType);
        initDictCache();
    }

    /**
     * 更新字典类型
     *
     * @param dto 字典信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDictType(DictTypeUpdateDTO dto) {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(dto, dictType);
        this.updateById(dictType);
        initDictCache();
    }

    /**
     * 删除字典类型
     *
     * @param id 主键集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void delDictType(String id) {
        DictType dictType = this.getById(id);
        if (dictDataService.lambdaQuery().eq(DictData::getDictTypeId, id).count() > 0) {
            throw new CustomizeException(String.format("%1$s 字典类型已分配,不能删除", dictType.getDictName()));
        }
        this.removeById(id);
        initDictCache();
    }

}