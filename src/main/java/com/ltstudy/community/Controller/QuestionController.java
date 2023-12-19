package com.ltstudy.community.Controller;

import com.ltstudy.community.DTO.CommentDTO;
import com.ltstudy.community.DTO.QuestionDTO;
import com.ltstudy.community.Mapper.CommentMapper;
import com.ltstudy.community.Mapper.QuestionMapper;
import com.ltstudy.community.Model.Comment;
import com.ltstudy.community.Model.Question;
import com.ltstudy.community.Model.User;
import com.ltstudy.community.Service.CommentService;
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

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private QuestionMapper questionMapper;

    //进入问题详情界面
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        QuestionDTO questionDTO=questionService.GetById(id);
        //Question question=questionMapper.getById(id);
        //计算阅读数
        questionService.countView(id);
        List<Comment> commentList=commentService.CommentList(questionDTO.getId());
        // Comment comment=commentMapper.getById(questionDTO.getId());
        //CommentDTO commentDTO=commentService.CommentList(questionDTO.getId());
        model.addAttribute("question",questionDTO);
        model.addAttribute("comment",commentList);
        model.addAttribute("questions",questionDTO);
        return "question";
    }


    //发表评论并展示
    @PostMapping("/question/{id}")
    public String doComment(
            @PathVariable(name = "id") Integer id,
            @RequestParam("content") String content,
            HttpServletRequest request,
            Model model
            ){
        QuestionDTO questionDTO=questionService.GetById(id);
        //计算阅读数
        questionService.countComment(id);
        model.addAttribute("question",questionDTO);
        //写入cookie
        Cookie[] cookies=request.getCookies();
        //获取token
        User user=(User) request.getSession().getAttribute("user");
        //验证用户是否登录
        if(user==null){
            model.addAttribute("error","用户未登录");
            return  "redirect:/";
        }

        model.addAttribute("content",content);
        if(content==null || content == ""){
            model.addAttribute("error","内容不为空");
            return "question";
        }
        Comment comment=new Comment();
        comment.setParentId(0);
        comment.setContent(content);
        comment.setQuestionId(id);
        comment.setType(1);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        commentMapper.insertComment(comment);
        return "redirect:/question/{id}";
    }

}
