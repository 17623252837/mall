package com.hrh.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrh.mall.domain.UmsOss;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.mapper.UmsOssMapper;
import com.hrh.mall.service.UmsOssService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

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
        if (umsOss.getId() == null) {
            save = umsOssMapper.insert(umsOss);
        }
        // 更新
        else {
            save = umsOssMapper.updateByPrimaryKey(umsOss);
        }
        return save > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK, "操作成功", save) : new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "操作失败");
    }

    @Override
    public ResponseResult<Integer> delete(Long UmsOssId) {
        if (UmsOssId == null) {
            return new ResponseResult<Integer>(ResponseResult.CodeStatus.OK, "id不能为空");
        }
        Example example = new Example(UmsOss.class);
        example.createCriteria().andEqualTo("id", UmsOssId);
        int delete = umsOssMapper.deleteByExample(example);
        return delete > 0 ? new ResponseResult<Integer>(ResponseResult.CodeStatus.OK, "删除成功", delete) : new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "删除失败");
    }

    @Override
    public List<UmsOss> page(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UmsOss.class);
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andLike("bucketName", '%' + keyword + '%');
        }

        return umsOssMapper.selectByExample(example);
    }
}



