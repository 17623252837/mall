package com.hrh.mall.controller;

import com.hrh.mall.common.api.CommonPage;
import com.hrh.mall.domain.UmsResource;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.service.UmsResourceService;
import com.hrh.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @ClassName: ResourceController
 * @Author: HuRonghua
 * @Description: 资源管理Api
 * @Date: 2020/4/26 18:31
 * @Version: 1.0
 */
@Api("资源管理Api")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Resource
    private UmsResourceService resourceService;

    @ApiOperation("分页模糊查询后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
                                                        @RequestParam(required = false) String nameKeyword,
                                                        @RequestParam(required = false) String urlKeyword,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsResource> list = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return new ResponseResult<CommonPage<UmsResource>>(ResponseResult.CodeStatus.OK,"请求成功",CommonPage.restPage(list));
    }


    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> create(@RequestBody UmsResource umsResource) {
        int insert = resourceService.create(umsResource);
        return insert > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"请求成功",insert) : new ResponseResult<>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("修改后台资源")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> update(@PathVariable Long id,
                               @RequestBody UmsResource umsResource) {
        int update = resourceService.update(id, umsResource);
        return update > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"修改成功",update) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> delete(@PathVariable Long id) {
        int delete = resourceService.delete(id);
        return delete > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"删除成功",delete) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<UmsResource>> listAll() {
       return new ResponseResult<List<UmsResource>>(ResponseResult.CodeStatus.OK,"请求成功",resourceService.listAll());
    }
}
