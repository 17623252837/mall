package com.hrh.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.domain
 * @ClassName:      UmsPermission
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
@Data
@Table(name = "ums_permission")
public class UmsPermission implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父级权限id
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 权限值
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 前端资源路径
     */
    @Column(name = "uri")
    private String uri;

    /**
     * 启用状态；0->禁用；1->启用
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    private static final long serialVersionUID = 1L;
}
