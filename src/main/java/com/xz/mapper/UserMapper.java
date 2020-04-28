package com.xz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xz.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author xz
 * @date 2020/4/22 15:32
 **/

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from t_user")
    IPage<User> selectPageVo(Page<?> page);
}
