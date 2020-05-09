package com.hrh.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.domain
 * @ClassName:      UmsResource
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
@Data
@Table(name = "ums_resource")
public class UmsResource implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 资源名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 资源URL
     */
    @Column(name = "url")
    private String url;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 资源分类ID
     */
    @Column(name = "category_id")
    private Long categoryId;

    private static final long serialVersionUID = 1L;
}
