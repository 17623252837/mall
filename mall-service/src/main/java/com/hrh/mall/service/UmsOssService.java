package com.hrh.mall.service;

import com.hrh.mall.domain.UmsOss;
import com.hrh.mall.dto.FileInfo;
import com.hrh.mall.dto.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.service
 * @ClassName: UmsOssService
 * @Author: HuRonghua
 * @Description: ${description}
 * @Date: 2020/5/8 14:57
 * @Version: 1.0
 */
public interface UmsOssService {
    /**
     * 更新 新增
     * @param  umsOss {@link UmsOss}
     * @return
     */
    ResponseResult<Integer> save(UmsOss umsOss);

    /**
     * 删除
     * @param UmsOssId {@link Long}
     * @return
     */
    ResponseResult<Integer> delete(Long UmsOssId);

    /**
     * 分页
     * @param keyword {@link String}
     * @param pageSize {@link String}
     * @param pageNum {@link String}
     * @return
     */
    List<UmsOss> page(String keyword, Integer pageSize, Integer pageNum);

    /**
     * oss 云存储 上传
     * @param multipartFile
     * @return
     */
    ResponseResult<FileInfo> upload(MultipartFile multipartFile);
}


