package com.ltstudy.community.Controller;

import com.ltstudy.community.Mapper.UserMapper;
import com.ltstudy.community.Model.User;
import com.ltstudy.community.Provider.GitHubProvider;
import com.ltstudy.community.DTO.AccessTokenDTO;
import com.ltstudy.community.DTO.GitHubUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired//自动将实例化好的对象放在githubProvider
    private GitHubProvider gitHubProvider;

    @Value("${github.client_id}")
    private String clientId;
    @Value("${github.client_secret}")
    private  String clientSecret;
    @Value("${github.redirect.uri}")
    private  String redirectUri;

    @Autowired//自动将实例化好的对象放在userMapper
    private UserMapper userMapper;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken= gitHubProvider.getAccess_token(accessTokenDTO);
        GitHubUser gitHubUser= gitHubProvider.getUser(accessToken);
        if (gitHubUser != null){
            //登录成功
            User user=new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccount_id(String.valueOf(gitHubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_modified());
            userMapper.insert(user);
            //cookie
            response.addCookie(new Cookie("token",token));
            //request.getSession().setAttribute("user",gitHubUser);
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }

}
