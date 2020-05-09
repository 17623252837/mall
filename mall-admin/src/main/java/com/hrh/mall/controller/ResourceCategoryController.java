package com.hrh.mall.controller;

import com.hrh.mall.domain.UmsResourceCategory;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.controller
 * @ClassName: ResourceCategoryController
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/4/27 9:11
 * @Version: 1.0
 */
@Api("资源")
@RestController
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {

    @Resource
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<UmsResourceCategory>> listAll() {
        List<UmsResourceCategory> umsResourceCategories = resourceCategoryService.listAll();
        return new ResponseResult<List<UmsResourceCategory>>(ResponseResult.CodeStatus.OK,"请求成功",umsResourceCategories);
    }


    @ApiOperation("添加后台资源分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> create(@RequestBody UmsResourceCategory umsResourceCategory) {
        int insert = resourceCategoryService.create(umsResourceCategory);
        return insert > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"请求成功") : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }


    @ApiOperation("修改后台资源分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> update(@PathVariable Long id,
                               @RequestBody UmsResourceCategory umsResourceCategory) {
        int update = resourceCategoryService.update(id, umsResourceCategory);
        return update > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"修改成功") : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }

    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> delete(@PathVariable Long id) {
        int delete = resourceCategoryService.delete(id);
        return delete > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"删除成功",delete) : new ResponseResult<Integer>(ResponseResult.CodeStatus.FAIL);
    }
}
