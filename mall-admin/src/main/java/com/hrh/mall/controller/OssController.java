package com.hrh.mall.controller;

import com.hrh.mall.common.api.CommonPage;
import com.hrh.mall.domain.UmsOss;
import com.hrh.mall.dto.FileInfo;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.service.UmsOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 云存储接口
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.controller
 * @ClassName: OssController
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/5/8 15:34
 * @Version: 1.0
 */
@Api("阿里OSS 云存储")
@RestController
@RequestMapping("/oss")
public class OssController {

    @Resource
    private UmsOssService ossService;

    @ApiOperation("oss 管理服务 分页接口")
    @GetMapping("/page")
    public ResponseResult<CommonPage<UmsOss>> page(@RequestParam (required = false)String bucketName,
                                                   @RequestParam int pageNum,
                                                   @RequestParam int pageSize){
        List<UmsOss> page = ossService.page(bucketName, pageSize, pageNum);
        return new ResponseResult<CommonPage<UmsOss>>(ResponseResult.CodeStatus.OK,"获取成功",CommonPage.restPage(page));
    }

    /**
     * 更新 新增
     * @param umsOss {@link UmsOss}
     * @return
     */
    @ApiOperation("oos 管理服务 更新接口")
    @PostMapping("/saadve")
    public ResponseResult<Integer> save(@RequestBody UmsOss umsOss){
        return ossService.save(umsOss);
    }

    /**
     * oss 管理服务 删除
     * @param id {@link Long}
     * @return
     */
    @ApiOperation("oss 管理服务")
    @PostMapping("/delete/{id}")
    public ResponseResult<Integer> delete(@PathVariable Long id){
        return ossService.delete(id);
    }


    /**
     * oss 管理服务 文件上传
     * @param multipartFile {@link MultipartFile}
     * @return
     */
    @ApiOperation("oss 文件上传")
    @PostMapping("/upload")
    public ResponseResult<FileInfo> upload(MultipartFile multipartFile){
        return ossService.upload(multipartFile);
    }
}
