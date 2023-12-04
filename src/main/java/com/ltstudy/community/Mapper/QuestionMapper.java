package com.ltstudy.community.Mapper;

import com.ltstudy.community.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into liu.question (title, description, gmt_create, gmt_modified, cteator,  tag) values" +
            " (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag} )")
    void insertQuestion(Question question);
}
