package com.ltstudy.community.Service;

import com.ltstudy.community.Mapper.UserMapper;
import com.ltstudy.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createAndUpdate(User user){
        User DbUser=userMapper.findByAccountId(user.getAccountId());
        if (DbUser==null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtModified());
            userMapper.insert(user);
        }else {
            DbUser.setGmtModified(System.currentTimeMillis());
            DbUser.setAvatarUrl(user.getAvatarUrl());
            DbUser.setName(user.getName());
            DbUser.setToken(user.getToken());
            userMapper.userUpdate(DbUser);
        }
    }
}
