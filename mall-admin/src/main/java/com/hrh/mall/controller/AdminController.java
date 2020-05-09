package com.hrh.mall.controller;

import com.hrh.mall.common.api.CommonPage;
import com.hrh.mall.domain.UmsAdmin;
import com.hrh.mall.domain.UmsRole;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.dto.UmsAdminParam;
import com.hrh.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.controller
 * @ClassName: AdminController
 * @Author: HuRonghua
 * @Description: 后台用户api
 * @Date: 2020/4/25 18:34
 * @Version: 1.0
 */
@Api("后台用户api")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private UmsAdminService adminService;

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
        return new ResponseResult<CommonPage<UmsAdmin>>(ResponseResult.CodeStatus.OK,"获取成功",CommonPage.restPage(adminList));
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return new ResponseResult<UmsAdmin>(ResponseResult.CodeStatus.FAIL);
        }
        return new ResponseResult<UmsAdmin>(ResponseResult.CodeStatus.OK,"注册成功",umsAdmin);
    }

    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> update(@PathVariable Long id, @RequestBody UmsAdmin admin) {
        int count = adminService.update(id, admin);
        if (count > 0) {
            return new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"修改成功",count);
        }
        return new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }


    @ApiOperation("修改帐号状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        int count = adminService.update(id,umsAdmin);
        if (count > 0) {
            return new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"修改成功",count);
        }
        return new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> delete(@PathVariable Long id) {
        int delete = adminService.delete(id);
        return delete > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"修改成功",delete) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("给用户分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int update = adminService.updateRole(adminId, roleIds);
        return new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"修改成功",update);
    }

    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return new ResponseResult<List<UmsRole>>(ResponseResult.CodeStatus.OK,"请求成功",roleList);
    }
}
