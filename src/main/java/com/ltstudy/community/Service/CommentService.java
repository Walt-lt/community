package com.ltstudy.community.Service;

import com.ltstudy.community.Enums.CommentEnums;
import com.ltstudy.community.Mapper.CommentMapper;
import com.ltstudy.community.Mapper.QuestionMapper;
import com.ltstudy.community.Model.Comment;
import com.ltstudy.community.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public void insertComment(Comment comment) {


        if(comment.getParentId()==0){

        }
        if(comment.getType()==0){

        }
        if(comment.getType()== CommentEnums.COMMENT.getType()){
            //
            Comment dbComment=commentMapper.getById(comment.getParentId());
            if(dbComment==null){

            }
            commentMapper.insertComment(comment);
        }else{
            Question question=questionMapper.getById(comment.getParentId());
            if(question==null){

            }
            commentMapper.insertComment(comment);
            question.setCountComment(question.getCountComment()+1);
            questionMapper.updateQuestion(question);
        }
    }
}
