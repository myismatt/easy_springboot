package com.easy.facade.util;

import com.easy.facade.beans.vo.MenuVO;
import com.easy.facade.beans.vo.RoleMenuVO;
import com.easy.facade.enums.MenuTypeEnum;
import com.easy.facade.framework.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单工具类
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/15 14:50
 */
public class MenuUtils {

    private final static Logger logger = LoggerFactory.getLogger(MenuUtils.class);

    /**
     * 构建树形结构的菜单权限
     *
     * @param sourceList 菜单列表
     * @return RoleMenuVO树形
     */
    public static List<RoleMenuVO> roleMenuTree(List<RoleMenuVO> sourceList) {
        if (sourceList == null) {
            logger.error("构建树形菜单权限错误, 传入菜单数据为null; Exception Class: RoleService.class");
            throw new CustomException("菜单数据异常");
        }
        // 对权限按照类型进行分组
        Map<MenuTypeEnum, List<RoleMenuVO>> sourceListMap = sourceList.stream().collect(Collectors.groupingBy(RoleMenuVO::getMenuType));
        // 取出类型
        List<MenuTypeEnum> collect = new ArrayList<>(sourceListMap.keySet());
        MenuTypeEnum max = Collections.min(collect);
        return sourceListMap.get(max).stream().map(rootVO -> roleMenuTreeByRoot(sourceList, rootVO)).collect(Collectors.toList());
    }

    /**
     * 根据顶级目录构建树形菜单权限
     *
     * @param sourceList 菜单列表
     * @param rootVO     顶级目录
     * @return RoleMenuVO树形
     */
    public static RoleMenuVO roleMenuTreeByRoot(List<RoleMenuVO> sourceList, RoleMenuVO rootVO) {
        if (sourceList == null) {
            return null;
        }
        List<RoleMenuVO> childList = sourceList.stream().filter(child -> rootVO.getMenuId().equals(child.getParentId())).map(child -> roleMenuTreeByRoot(sourceList, child)).collect(Collectors.toList());
        if (childList.size() == 0) {
            return rootVO;
        }
        rootVO.setChild(childList);
        return rootVO;
    }

    /**
     * 构建树形结构的菜单权限
     *
     * @param sourceList 菜单列表
     * @return RoleMenuVO树形
     */
    public static List<MenuVO> menuTree(List<MenuVO> sourceList) {
        if (sourceList == null) {
            logger.error("构建树形菜单权限错误, 传入菜单数据为null; Exception Class: RoleService.class");
            throw new CustomException("菜单数据异常");
        }
        // 对权限按照类型进行分组
        Map<MenuTypeEnum, List<MenuVO>> sourceListMap = sourceList.stream().collect(Collectors.groupingBy(MenuVO::getMenuType));
        // 取出类型
        List<MenuTypeEnum> collect = new ArrayList<>(sourceListMap.keySet());
        MenuTypeEnum max = Collections.min(collect);
        return sourceListMap.get(max).stream().map(rootVO -> menuTreeByRoot(sourceList, rootVO)).collect(Collectors.toList());
    }

    /**
     * 根据顶级目录构建树形菜单权限
     *
     * @param sourceList 菜单列表
     * @param rootVO     顶级目录
     * @return RoleMenuVO树形
     */
    public static MenuVO menuTreeByRoot(List<MenuVO> sourceList, MenuVO rootVO) {
        if (sourceList == null) {
            return null;
        }
        List<MenuVO> childList = sourceList.stream().filter(child -> rootVO.getId().equals(child.getParentId())).map(child -> menuTreeByRoot(sourceList, child)).collect(Collectors.toList());
        if (childList.size() == 0) {
            return rootVO;
        }
        rootVO.setChild(childList);
        return rootVO;
    }
}