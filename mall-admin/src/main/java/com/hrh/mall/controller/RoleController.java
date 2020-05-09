package com.hrh.mall.controller;

import com.hrh.mall.common.api.CommonPage;
import com.hrh.mall.domain.UmsMenu;
import com.hrh.mall.domain.UmsResource;
import com.hrh.mall.domain.UmsRole;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @ClassName: RoleController
 * @Author: HuRonghua
 * @Description: 权限api
 * @Date: 2020/4/25 19:01
 * @Version: 1.0
 */
@Api("权限api")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private UmsRoleService umsRoleService;

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<UmsRole>> listAll() {
        List<UmsRole> list = umsRoleService.list();
        return new ResponseResult<>(ResponseResult.CodeStatus.OK,"获取成功",list);
    }


    @ApiOperation("根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsRole> list = umsRoleService.list(keyword, pageSize, pageNum);
        return new ResponseResult<CommonPage<UmsRole>>(ResponseResult.CodeStatus.OK,"获取成功",CommonPage.restPage(list));
    }

    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> create(@RequestBody UmsRole role) {
        int insert = umsRoleService.insert(role);
        return insert > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"增加成功",insert) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL,"增加失败");
    }

    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> delete(@RequestParam("ids") List<Long> ids) {
        int delete = umsRoleService.delete(ids);
        return delete > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"操作成功",delete) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> update(@PathVariable Long id, @RequestBody UmsRole role) {
        role.setId(id);
        int update = umsRoleService.update(role);
        return update > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"更新成功",update) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("获取角色相关菜单")
    @RequestMapping(value = "/listMenu/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<UmsMenu>> listMenu(@PathVariable Long roleId) {
        List<UmsMenu> umsMenus = umsRoleService.listMenu(roleId);
        return new ResponseResult<List<UmsMenu>>(ResponseResult.CodeStatus.OK,"获取成功",umsMenus);
    }

    @ApiOperation("给角色分配菜单")
    @RequestMapping(value = "/allocMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int i = umsRoleService.allocMenu(roleId, menuIds);
        return new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"分配成功",i);
    }

    @ApiOperation("获取角色相关资源")
    @RequestMapping(value = "/listResource/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<UmsResource>> listResource(@PathVariable Long roleId) {
        List<UmsResource> umsResources = umsRoleService.listResource(roleId);
        return new ResponseResult<List<UmsResource>>(ResponseResult.CodeStatus.OK,"请求成功",umsResources);
    }


    @ApiOperation("给角色分配资源")
    @RequestMapping(value = "/allocResource", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int i = umsRoleService.allocResource(roleId, resourceIds);
        return new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"获取成功",i) ;
    }
}
