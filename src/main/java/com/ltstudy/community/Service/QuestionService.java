package com.ltstudy.community.Service;

import com.ltstudy.community.DTO.PageDTO;
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


    public PageDTO list(Integer page, Integer size) {


        PageDTO pageDTO=new PageDTO();
        Integer totalPage;
        Integer totalCount=questionMapper.Count();


        if (totalCount%size == 0) {
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        //page超限处理
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        pageDTO.setPagination(totalPage,page);
        Integer offset=size*(page-1);

        List<Question> questions=questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        //page包含QuestionDTOList;
        for (Question question:questions){

            User user= userMapper.findById(question.getCreator());

            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);

            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);


        }
        pageDTO.setQuestions(questionDTOList);
        return  pageDTO;
    }

    public PageDTO list1(Integer userId, Integer page, Integer size) {
        PageDTO pageDTO=new PageDTO();
            Integer totalPage;
            Integer totalCount=questionMapper.CountByUserId(userId);


            if (totalCount%size == 0) {
                totalPage=totalCount/size;
            }else{
                totalPage=totalCount/size+1;
            }
            //page超限处理
            if(page<1){
                page=1;
            }
            if(page>totalPage){
                page=totalPage;
            }


        pageDTO.setPagination(totalPage,page);
        Integer offset=size*(page-1);

        List<Question> questions=questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        //page包含QuestionDTOList;
        for (Question question:questions){

            User user= userMapper.findById(question.getCreator());

            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);

            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);


        }
        pageDTO.setQuestions(questionDTOList);
        return  pageDTO;
    }

    public QuestionDTO GetById(Integer id) {
        Question question=questionMapper.getById(id);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createAndUpdate(Question question) {
        if (question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertQuestion(question);
        }else {
            //update
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateQuestion(question);
        }
    }


    //浏览数
    public void countView(Integer id) {
        Question question=questionMapper.getById(id);
        question.setCountRead(question.getCountRead()+1);
        questionMapper.updateQuestion(question);
    }

    public void countComment(Integer id) {
        Question question=questionMapper.getById(id);
        question.setCountRead(question.getCountComment()+1);
        questionMapper.updateQuestion(question);
    }



}
