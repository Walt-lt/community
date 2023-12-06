package com.ltstudy.community.Mapper;

import com.ltstudy.community.Model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
   @Insert("insert into liu.user (accountId,name,token,gmtCreate,gmtModified,avatarUrl) VALUES ( #{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl} )")
    void insert(User user);

   @Select("select * from liu.user where token = #{token}")
   User findByToken(@Param("token") String token);

    @Select("select * from liu.user where  id= #{id}")
    User findById(@Param("id") Integer id);


    @Select("select * from liu.user where accountId=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update liu.user set name=#{name} ,token=#{token}, gmtModified=#{gmtModified},avatarUrl=#{avatarUrl}")
    void userUpdate(User user);
}
