package com.example.springbootmybatisplusdemo.dal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author chen
 * @since 2022-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MultDruid extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 钱
     */
    private Integer money;

    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField(value = "create_at")
    private Timestamp createAt;

    @TableField(value = "update_at")
    private Timestamp updateAt;


}
