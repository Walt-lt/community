package com.ltstudy.community.Controller;


import com.ltstudy.community.DTO.QuestionDTO;
import com.ltstudy.community.Mapper.QuestionMapper;
import com.ltstudy.community.Mapper.UserMapper;
import com.ltstudy.community.Model.Question;
import com.ltstudy.community.Model.User;
import com.ltstudy.community.Service.QuestionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
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



        List<QuestionDTO> questionList=questionService.list();
        model.addAttribute("questions",questionList);
        return "index";
    }

}
