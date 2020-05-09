package com.hrh.mall.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.domain
 * @ClassName:      UmsAdminRoleRelation
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/25 17:35
 * @Version:    1.0
 */
@Data
@Table(name = "ums_admin_role_relation")
public class UmsAdminRoleRelation implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "role_id")
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}
