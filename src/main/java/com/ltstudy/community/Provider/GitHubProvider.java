package com.ltstudy.community.Provider;

import com.alibaba.fastjson.JSON;
import com.ltstudy.community.DTO.AccessTokenDTO;
import com.ltstudy.community.DTO.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component//对象自动实例化
public class GitHubProvider {
    //获取Access_token
    public String getAccess_token(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO),mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = null;
            if (response.body() != null) {
                string = response.body().string();
                System.out.println(string);
            }
            if (string != null) {
                return string.split("&")[0].split("=")[1];
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //通过得到的Access——token获取用户信息
    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=").header("Authorization","token "+accessToken)
                .build();
        try {
            String string;
            try (Response response = client.newCall(request).execute()) {
                string = null;
                if (response.body() != null) {
                    string = response.body().string();
                }
            }
            return JSON.parseObject(string, GitHubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
