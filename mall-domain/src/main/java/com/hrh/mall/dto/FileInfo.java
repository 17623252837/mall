package com.hrh.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.oss.dto
 * @ClassName: FileInfo
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/3/22 14:56
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {

    private static final long serialVersionUID = -8613493315102451225L;
    /**
     * 文件路径
     */
    private String path;
}
