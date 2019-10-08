package com.xielei.springshiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xielei.springshiro.entity.User;

/**
 * 描述： MyBatis Mapper层
 *
 * @author xielei
 * @date 2019/10/07
 */
@Repository
public interface UserMapper {
    
    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    User findByUserName(@Param("userName") String userName);
}
