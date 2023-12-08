package com.ltstudy.community.Controller;

import com.ltstudy.community.DTO.CommentDTO;
import com.ltstudy.community.DTO.QuestionDTO;
import com.ltstudy.community.Mapper.CommentMapper;
import com.ltstudy.community.Mapper.QuestionMapper;
import com.ltstudy.community.Model.Comment;
import com.ltstudy.community.Model.Question;
import com.ltstudy.community.Model.User;
import com.ltstudy.community.Service.QuestionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

//    @GetMapping("/comment")
//    public String postComment(@RequestParam(name = "content") String content,
//                            @RequestParam(name = "id") Integer id,
//                            Model model){
//        model.addAttribute("content",content);
//        model.addAttribute("id" ,id);
//        return "comment";
//    }



//    @ResponseBody
//    @RequestMapping(value = "/comment",method = RequestMethod.POST)
//    public Object post(@RequestBody CommentDTO commentDTO){
//        Comment comment=new Comment();
//        comment.setParentId(commentDTO.getParentId());
//        comment.setContent(commentDTO.getContent());
//        comment.setType(commentDTO.getType());
//        comment.setGmtModified(System.currentTimeMillis());
//        comment.setGmtCreate(System.currentTimeMillis());
//        comment.setCommentator(1);
//        commentMapper.insertComment(comment);
//        return  null;
//    }
//@PostMapping("/comment")
//public String doComment(
//        @RequestParam("content") String content,
//        HttpServletRequest request,
//        Model model
//){
//
//
//
//
//    //获取发布的问题的信息
//    model.addAttribute("content",content);
//    //写入cookie
//    //获取token
//    //将得到的信息传输到表中
//    Comment comment=new Comment();
//        comment.setParentId(0);
//        comment.setContent(content);
//        comment.setType(1);
//        comment.setGmtModified(System.currentTimeMillis());
//        comment.setGmtCreate(System.currentTimeMillis());
//        comment.setCommentator(1);
//        commentMapper.insertComment(comment);
//
//    return "redirect:/";
//}
}
