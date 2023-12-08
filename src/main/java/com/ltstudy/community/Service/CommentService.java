package com.ltstudy.community.Service;

import com.ltstudy.community.DTO.CommentDTO;
import com.ltstudy.community.DTO.QuestionDTO;
import com.ltstudy.community.Mapper.CommentMapper;
import com.ltstudy.community.Mapper.QuestionMapper;
import com.ltstudy.community.Mapper.UserMapper;
import com.ltstudy.community.Model.Comment;
import com.ltstudy.community.Model.Question;
import com.ltstudy.community.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    public void createAndUpdate(Comment comment) {

        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        commentMapper.insertComment(comment);
    }

    public List<Comment> CommentList(Integer questionId){
        List<Comment> comments=commentMapper.listByQuestionId(questionId);
        List<Comment> commentList=new ArrayList<>();
        for (Comment comment:comments){
            QuestionDTO questionDTO=questionMapper.getQuestionById(comment.getQuestionId());
            User user=userMapper.findById(questionDTO.getCreator());
            questionDTO.setUser(user);
            comment.setQuestionDTO(questionDTO);
            commentList.add(comment);
        }
        return  commentList;
    }
    public List<Comment> CommentListAll(Integer commentator){
        List<Comment> comments=commentMapper.listByCommentator(commentator);
        List<Comment> commentList=new ArrayList<>();
        for (Comment comment:comments){
            QuestionDTO questionDTO=questionMapper.getQuestionById(comment.getQuestionId());
            User user=userMapper.findById(questionDTO.getCreator());
            questionDTO.setUser(user);
            comment.setQuestionDTO(questionDTO);
            commentList.add(comment);
        }
        return  commentList;
    }
}
