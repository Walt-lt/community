package com.ltstudy.community.Service;

import com.ltstudy.community.DTO.QuestionDTO;
import com.ltstudy.community.Mapper.QuestionMapper;
import com.ltstudy.community.Mapper.UserMapper;
import com.ltstudy.community.Model.Question;
import com.ltstudy.community.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        List<Question> questions=questionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question:questions){
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return  questionDTOList;
    }
}
