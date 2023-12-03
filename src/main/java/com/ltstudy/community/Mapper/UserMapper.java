package com.ltstudy.community.Mapper;

import com.ltstudy.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert  into  appuser (account_id, name, token, gmt_create, gmt_modified) values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);
}
