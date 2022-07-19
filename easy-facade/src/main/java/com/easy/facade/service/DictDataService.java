package com.easy.facade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.DictDataDTO;
import com.easy.facade.beans.dto.DictDataSearchDTO;
import com.easy.facade.beans.dto.DictDataUpdateDTO;
import com.easy.facade.beans.model.DictData;
import com.easy.facade.dao.DictDataMapper;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.utils.bean.BeanUtils;
import com.easy.utils.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典数据
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:36
 */
@Service
public class DictDataService extends ServiceImpl<DictDataMapper, DictData> {

    private final RedisUtils redisUtils;

    public DictDataService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 集合查询
     *
     * @param dto 分页查询入参
     * @return 分页数据
     */
    public List<DictData> listDictData(DictDataSearchDTO dto) {
        return lambdaQuery()
                .eq(StringUtils.isNotBlank(dto.getDictTypeId()), DictData::getDictTypeId, dto.getDictTypeId())
                .eq(StringUtils.isNotBlank(dto.getDictType()), DictData::getDictType, dto.getDictType())
                .eq(StringUtils.isNotBlank(dto.getDictLabel()), DictData::getDictLabel, dto.getDictLabel())
                .eq(StringUtils.isNotNull(dto.getEnable()), DictData::getEnable, dto.getEnable()).list();
    }

    /**
     * 分页查询
     *
     * @param dto 查询入参
     * @return 返回枫叶
     */
    public IPage<DictData> pageDictData(DictDataSearchDTO dto) {
        return lambdaQuery()
                .eq(StringUtils.isNotBlank(dto.getDictTypeId()), DictData::getDictTypeId, dto.getDictTypeId())
                .eq(StringUtils.isNotBlank(dto.getDictType()), DictData::getDictType, dto.getDictType())
                .eq(StringUtils.isNotBlank(dto.getDictLabel()), DictData::getDictLabel, dto.getDictLabel())
                .eq(StringUtils.isNotNull(dto.getEnable()), DictData::getEnable, dto.getEnable())
                .page(new Page<DictData>().setSize(dto.getSize()).setCurrent(dto.getCurrent()));
    }


    /**
     * 根据字典类型 获取字典数据
     *
     * @param dictType 字典类型
     * @return List<DictDataVO>
     */
    public List<DictData> getDictDataListByDictType(String dictType) {
        return lambdaQuery().eq(DictData::getDictType, dictType).list();
    }

    /**
     * 新增字典数据
     *
     * @param dto 参数
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDictData(DictDataDTO dto) {
        DictData dictData = new DictData();
        BeanUtils.populate(dto, dictData);
        this.save(dictData);
    }

    /**
     * 更新字典数据
     *
     * @param updateDTO 字典数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDictData(DictDataUpdateDTO updateDTO) {
        DictData updateDictData = new DictData();
        BeanUtils.populate(updateDTO, updateDictData);
        this.updateById(updateDictData);

    }

    /**
     * 删除字典数据
     *
     * @param ids 主键id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void delDictData(List<String> ids) {
        removeByIds(ids);
    }

}