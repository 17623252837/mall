package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @ProjectName: Microservice-2.0
 * @Package: tk.mybatis.mapper
 * @ClassName: MyMapper
 * @Author: HuRonghua
 * @Description: 通用Mapper父类接口
 * @Date: 2020/3/19 12:57
 * @Version: 1.0
 */
public interface MyMapper<T>  extends Mapper<T> , MySqlMapper<T> {

}
