package com.hrh.mall.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * @ProjectName:    mall-monomer 
 * @Package:        com.hrh.mall.domain
 * @ClassName:      UmsEmailConfig
 * @Author:     HuRonghua
 * @Description:  ${description}  
 * @Date:    2020/5/8 10:59
 * @Version:    1.0
 */
@Data
@Table(name = "ums_email_config")
public class UmsEmailConfig implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 收件人
     */
    @Column(name = "from_user")
    private String fromUser;

    /**
     * 邮件服务器SMTP地址
     */
    @Column(name = "`host`")
    private String host;

    /**
     * 密码
     */
    @Column(name = "pass")
    private String pass;

    /**
     * 端口
     */
    @Column(name = "port")
    private String port;

    /**
     * 发件者用户名
     */
    @Column(name = "`user`")
    private String user;

    private static final long serialVersionUID = 1L;
}