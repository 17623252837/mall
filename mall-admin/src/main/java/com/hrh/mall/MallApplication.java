package com.hrh.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall
 * @ClassName: MallApplication
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/4/25 14:58
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.hrh.mall.mapper")
public class MallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class,args);
    }
}
