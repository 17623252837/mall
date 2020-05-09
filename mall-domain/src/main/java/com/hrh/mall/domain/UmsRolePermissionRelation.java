package com.hrh.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.domain
 * @ClassName:      UmsRolePermissionRelation
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:11
 * @Version:    1.0
 */
@Data
@Table(name = "ums_role_permission_relation")
public class UmsRolePermissionRelation implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "permission_id")
    private Long permissionId;

    private static final long serialVersionUID = 1L;
}
