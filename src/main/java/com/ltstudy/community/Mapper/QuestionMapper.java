package com.ltstudy.community.Mapper;

import com.ltstudy.community.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into liu.question (title, description, gmtCreate, gmtModified, cteator,  tag) values" +
            " (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag} )")
    void insertQuestion(Question question);


    @Select("select * from liu.question")
    List<Question> list();
}
