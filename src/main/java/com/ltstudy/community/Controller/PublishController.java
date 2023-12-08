package com.ltstudy.community.Controller;

import com.ltstudy.community.DTO.QuestionDTO;
import com.ltstudy.community.Model.Question;
import com.ltstudy.community.Model.User;
import com.ltstudy.community.Service.QuestionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;


    //编辑页面
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Integer id,
                       Model model){

        QuestionDTO question=questionService.GetById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }




    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }




    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request,
            Model model
    ){




        //获取发布的问题的信息
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        //写入cookie
        Cookie[] cookies=request.getCookies();
        //获取token
        User user=(User) request.getSession().getAttribute("user");
        //验证用户是否登录
        if(user==null){
            model.addAttribute("error","用户未登录");
            return  "publish";
        }

        //判断是否发布成功
        if(title==null || title == ""){
            model.addAttribute("error","标题不为空");
            return "publish";
        }
        if(description==null || description == ""){
            model.addAttribute("error","问题内容不为空");
            return "publish";
        }
        if(tag==null || tag == ""){
            model.addAttribute("error","标签不为空");
            return "publish";
        }
        if(id==null){
            Question question=new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            questionService.createAndUpdate(question);
        }else {
            QuestionDTO questionDTO=questionService.GetById(id);


            //将得到的信息传输到表中
            Question question=new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setId(id);
            question.setCountComment(questionDTO.getCountComment());
            question.setCountRead(questionDTO.getCountRead());
            question.setCountLike(question.getCountLike());

            questionService.createAndUpdate(question);
        }
        return "redirect:/";
    }
}
