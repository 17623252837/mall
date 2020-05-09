package com.hrh.mall.dto;

import com.hrh.mall.domain.UmsMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.dto
 * @ClassName: LoginInfo
 * @Author: HuRonghua
 * @Description: 登录信息
 * @Date: 2020/3/21 5:13
 * @Version: 1.0
 */
@Data
public class LoginInfo implements Serializable {
    private Long id;
    private String name;
    private String avatar;
    private String nickName;
    private String[] roles;
    /**
     * 菜单
     */
    private List<UmsMenu> menus;
}
