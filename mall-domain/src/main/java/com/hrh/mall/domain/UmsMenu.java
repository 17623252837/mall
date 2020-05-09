package com.hrh.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.domain
 * @ClassName:      UmsMenu
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
@Data
@Table(name = "ums_menu")
public class UmsMenu implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 菜单名称
     */
    @Column(name = "title")
    private String title;

    /**
     * 菜单级数
     */
    @Column(name = "`level`")
    private Integer level;

    /**
     * 菜单排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 前端名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 前端图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 前端隐藏
     */
    @Column(name = "hidden")
    private Integer hidden;

    private static final long serialVersionUID = 1L;
}
