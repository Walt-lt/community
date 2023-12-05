package com.ltstudy.community.Controller;

import com.ltstudy.community.DTO.PageDTO;
import com.ltstudy.community.Mapper.QuestionMapper;
import com.ltstudy.community.Mapper.UserMapper;
import com.ltstudy.community.Model.User;
import com.ltstudy.community.Service.QuestionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size){
        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            return  "redirect:/";
        }

        if("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的发布");
        } else if ("replies".equals(action)) {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }


        PageDTO pageDTO=questionService.list1(user.getId(),page,size);
        model.addAttribute("pagination",pageDTO);
        return "profile";
    }
}
