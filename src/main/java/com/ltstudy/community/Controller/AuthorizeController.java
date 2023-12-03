package com.ltstudy.community.Controller;

import com.ltstudy.community.Provider.GitHubProvider;
import com.ltstudy.community.dto.AccessTokenDTO;
import com.ltstudy.community.dto.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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




    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken= gitHubProvider.getAccess_token(accessTokenDTO);
        GitHubUser user= gitHubProvider.getUser(accessToken);
        System.out.println(user.getBio());
        return "index";
    }

}
