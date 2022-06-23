package com.easy.facade.beans.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.facade.beans.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户详细数据
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:29
 */
@ApiModel(value = "用户详细数据")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_user_info")
public class UserInfo extends BaseEntity {
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户id")
    private String userId;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    @ApiModelProperty(value = "昵称")
    private String nickname;
}
