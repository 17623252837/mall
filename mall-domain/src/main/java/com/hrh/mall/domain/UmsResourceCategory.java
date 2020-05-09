package com.hrh.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.domain
 * @ClassName:      UmsResourceCategory
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
@Data
@Table(name = "ums_resource_category")
public class UmsResourceCategory implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 分类名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    private static final long serialVersionUID = 1L;
}
