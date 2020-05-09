package com.hrh.mall.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.domain
 * @ClassName: UmsOss
 * @Author: HuRonghua
 * @Description: ${description}
 * @Date: 2020/5/8 15:51
 * @Version: 1.0
 */
@Data
@Table(name = "ums_oss")
public class UmsOss implements Serializable {
    /**
     * oss 云存储id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 阿里云存储接口路径
     */
    @Column(name = "endpoint")
    private String endpoint;

    /**
     * 云存储 key
     */
    @Column(name = "access_key_id")
    private String accessKeyId;

    /**
     * 云存储 secret
     */
    @Column(name = "access_key_secret")
    private String accessKeySecret;

    /**
     * 云存储 上传文件夹name
     */
    @Column(name = "bucket_name")
    private String bucketName;

    /**
     * oss 状态 0 -> 启用 1-> 停用
     */
    @Column(name = "oss_state")
    private Integer ossState;

    private static final long serialVersionUID = 1L;
}