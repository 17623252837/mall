package com.hrh.mall.controller;

import com.hrh.mall.domain.UmsAdmin;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.controller
 * @ClassName: ProfileController
 * @Author: HuRonghua
 * @Description: 用户信息api
 * @Date: 2020/5/6 10:28
 * @Version: 1.0
 */
@Api("用户信息API")
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Resource
    private UmsAdminService umsAdminService;

    /**
     * 获取用户信息
     * @param umsAdminId {@link UmsAdmin}
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/info/{umsAdminId}")
    public ResponseResult<UmsAdmin> getUserInfo(@PathVariable long umsAdminId){
        UmsAdmin umsAdmin = umsAdminService.getUserInfoById(umsAdminId);
        return umsAdmin != null ? new ResponseResult<UmsAdmin>(ResponseResult.CodeStatus.OK,"获取成功",umsAdmin) : new ResponseResult<>(ResponseResult.CodeStatus.FAIL,"获取失败");
    }

    /**
     * 跟新用户信息
     * @param umsAdmin {@link UmsAdmin}
     * @return
     */
    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public ResponseResult<Integer> update(@RequestBody UmsAdmin umsAdmin){
        int update = umsAdminService.update(umsAdmin);
        return update > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"更新成功",update) : new ResponseResult<>(ResponseResult.CodeStatus.FAIL,"更新失败");
    }
}
