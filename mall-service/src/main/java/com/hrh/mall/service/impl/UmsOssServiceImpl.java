package com.hrh.mall.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.github.pagehelper.PageHelper;
import com.hrh.mall.domain.UmsOss;
import com.hrh.mall.dto.FileInfo;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.mapper.UmsOssMapper;
import com.hrh.mall.service.UmsOssService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.service.impl
 * @ClassName: UmsOssServiceImpl
 * @Author: HuRonghua
 * @Description: ${description}
 * @Date: 2020/5/8 14:57
 * @Version: 1.0
 */
@Service
public class UmsOssServiceImpl implements UmsOssService {

    @Resource
    private UmsOssMapper umsOssMapper;

    @Override
    public ResponseResult<Integer> save(UmsOss umsOss) {
        int save = 0;
        // 新增
        if(umsOss.getId() == null){
            save = umsOssMapper.insert(umsOss);
        }
        // 更新
        else{
            save = umsOssMapper.updateByPrimaryKey(umsOss);
        }
        return save > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"操作成功",save) : new ResponseResult<>(ResponseResult.CodeStatus.FAIL,"操作失败");
    }

    @Override
    public ResponseResult<Integer> delete(Long UmsOssId) {
        if(UmsOssId == null){
            return new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"id不能为空");
        }
        Example example = new Example(UmsOss.class);
        example.createCriteria().andEqualTo("id",UmsOssId);
        int delete = umsOssMapper.deleteByExample(example);
        return delete > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK,"删除成功",delete) : new ResponseResult<>(ResponseResult.CodeStatus.FAIL,"删除失败");
    }

    @Override
    public List<UmsOss> page(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(UmsOss.class);
        if(!StringUtils.isEmpty(keyword)){
            example.createCriteria().andLike("bucketName",'%' + keyword +'%');
        }
        return umsOssMapper.selectByExample(example);
    }


    @Override
    public ResponseResult<FileInfo> upload(MultipartFile multipartFile) {
        Example example = new Example(UmsOss.class);
        example.createCriteria().andEqualTo(1l);
        UmsOss umsOss = umsOssMapper.selectOneByExample(example);
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + suffix;
        OSS client = new OSSClientBuilder().build(umsOss.getEndpoint(), umsOss.getAccessKeyId(), umsOss.getAccessKeySecret());
        try {
            client.putObject(new PutObjectRequest(umsOss.getBucketName(), newName, new ByteArrayInputStream(multipartFile.getBytes())));
            // 上传文件路径 = http://BUCKET_NAME.ENDPOINT/自定义路径/fileName
            return new ResponseResult<FileInfo>(ResponseResult.CodeStatus.FAIL, "文件上传成功", new FileInfo("http://" + umsOss.getBucketName() + "." + umsOss.getEndpoint() + "/" + newName));
        } catch (IOException e) {
            return new ResponseResult<FileInfo>(ResponseResult.CodeStatus.FAIL, "文件上传失败，请重试");
        } finally {
            client.shutdown();
        }
    }
}


