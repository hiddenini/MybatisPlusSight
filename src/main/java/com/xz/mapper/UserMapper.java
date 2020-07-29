package com.xz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xz.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author xz
 * @date 2020/4/22 15:32
 **/

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from t_user")
    IPage<User> selectPageVo(Page<?> page);


    @Select("select * from t_user where id=#{id}")
    User selectByUserId(Long id);

    @Select("select * from t_user where name=#{name}")
    User selectByUserName(String name);

    @Select("select * from t_user where member_id=#{memberId}")
    User selectByUserMemberId(Long memberId);

    @Update("update t_user set member_id=member_id+#{num} where id=9548")
    Integer update(Integer num);
}
