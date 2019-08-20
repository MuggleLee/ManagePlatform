package com.hao.userservice.Dao;

import com.hao.commonmodel.User.AppUser;
import com.hao.commonmodel.User.UserCredential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author MuggleLee
 * @date 2019/7/22
 */
@Mapper
public interface UserCredentialsDao {

    @Select("select * from user_credentials t where t.username = #{username}")
    UserCredential findByUsername(String username);

    @Insert("insert into user_credentials(username, type, userId) values(#{username}, #{type}, #{userId})")
    int save(UserCredential userCredential);

    @Select("select u.* from app_user u inner join user_credentials c on c.userId = u.id where c.username = #{username}")
    AppUser findUserByUsername(String username);
}
