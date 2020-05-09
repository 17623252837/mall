package com.hrh.mall.controller;

import com.hrh.mall.common.api.CommonPage;
import com.hrh.mall.domain.UmsMenu;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.dto.UmsMenuNode;
import com.hrh.mall.service.UmsMenuService;
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
import java.util.Map;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.controller
 * @ClassName: MenuController
 * @Author: HuRonghua
 * @Description: 动态菜单
 * @Date: 2020/4/25 16:55
 * @Version: 1.0
 */
@Api("动态菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private UmsMenuService umsMenuService;

    @ApiOperation("树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public ResponseResult<List<UmsMenuNode>> treeList() {
        List<UmsMenuNode> list = umsMenuService.treeList();
        return new ResponseResult<List<UmsMenuNode>>(ResponseResult.CodeStatus.OK, "登录成功",list);
    }

    @ApiOperation("分页查询后台菜单")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsMenu> list = umsMenuService.list(parentId, pageSize, pageNum);
        return new ResponseResult<CommonPage<UmsMenu>>(ResponseResult.CodeStatus.OK,"获取成功",CommonPage.restPage(list));
    }

    @ApiOperation("添加后台菜单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> create(@RequestBody UmsMenu umsMenu) {
        int create = umsMenuService.create(umsMenu);
        return create > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"创建成功",create) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("修改后台菜单")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> update(@PathVariable Long id,
                               @RequestBody UmsMenu umsMenu) {
        int update = umsMenuService.update(id, umsMenu);
        return update > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"更新成功",update): new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("根据ID获取菜单详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<UmsMenu> getItem(@PathVariable Long id) {
        UmsMenu item = umsMenuService.getItem(id);
        return new ResponseResult<UmsMenu>(ResponseResult.CodeStatus.OK,"请求成功",item);
    }

    @ApiOperation("修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        int update = umsMenuService.updateHidden(id, hidden);
        return update > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"更新成功",update) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("根据ID删除后台菜单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> delete(@PathVariable Long id) {
        int delete = umsMenuService.delete(id);
        return delete > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"删除成功",delete) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }
}
