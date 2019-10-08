package com.xielei.springshiro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.xielei.springshiro.entity.User;

/**
 * 描述： MyBatis Mapper层
 *
 * @author xielei
 * @date 2019/10/07
 */
public interface UserMapper {
    
    int deleteByPrimaryKey(Integer uid);
    
    int insert(User record);
    
    int insertSelective(User record);
    
    User selectByPrimaryKey(Integer uid);
    
    int updateByPrimaryKeySelective(User record);
    
    int updateByPrimaryKey(User record);
    
    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    User findByUserName(@Param("userName") String userName);
}
