package com.ltstudy.community.Controller;

import com.ltstudy.community.Mapper.QuestionMapper;
import com.ltstudy.community.Mapper.UserMapper;
import com.ltstudy.community.Model.Question;
import com.ltstudy.community.Model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ){
        //写入cookie
        User user=null;
        Cookie[] cookies=request.getCookies();
        //获取token
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                user = userMapper.findByToken(token);

                //验证
                if (user !=null){
                    request.getSession().setAttribute("user" ,user);
                }
                break;
            }
        }
        //验证用户是否登录
        if(user==null){
            model.addAttribute("error","用户未登录");
            return  "publish";
        }

        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());

        questionMapper.insertQuestion(question);
        return "redirect:/";
    }
}
