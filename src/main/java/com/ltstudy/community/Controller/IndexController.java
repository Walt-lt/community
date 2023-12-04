package com.ltstudy.community.Controller;


import com.ltstudy.community.Mapper.UserMapper;
import com.ltstudy.community.Model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        //获取token
        for(Cookie cookie:cookies){
           if(cookie.getName().equals("token")){
               String token=cookie.getValue();
               User user = userMapper.findByToken(token);

               //验证
               if (user !=null){
                   request.getSession().setAttribute("user" ,user);
               }
               break;
           }
        }

        return "index";
    }

}
