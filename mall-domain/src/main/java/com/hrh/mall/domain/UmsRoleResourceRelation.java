package com.hrh.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.domain
 * @ClassName:      UmsRoleResourceRelation
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:11
 * @Version:    1.0
 */
@Data
@Table(name = "ums_role_resource_relation")
public class UmsRoleResourceRelation implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 资源ID
     */
    @Column(name = "resource_id")
    private Long resourceId;

    private static final long serialVersionUID = 1L;
}
