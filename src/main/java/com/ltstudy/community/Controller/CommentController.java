package com.ltstudy.community.Controller;

import com.ltstudy.community.DTO.CommentDTO;
import com.ltstudy.community.DTO.ResultDTO;
import com.ltstudy.community.Model.Comment;
import com.ltstudy.community.Model.User;
import com.ltstudy.community.Service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object postComment(@RequestBody CommentDTO commentDTO,
                              HttpServletRequest request){


        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(6262,"未登录不能评论");
        }

        Comment comment=new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(commentDTO.getGmtCreate());

        comment.setCommentator(user.getId());
        commentService.insertComment(comment);

        return ResultDTO.okOf();
    }
}
