package com.ltstudy.community.Mapper;

import com.ltstudy.community.Model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into liu.question (title, description, gmtCreate, gmtModified, creator,  tag) values" +
            " (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag} )")
    void insertQuestion(Question question);


    @Select("select * from liu.question limit #{offset}, #{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("select * from liu.question where creator=#{userId} limit #{offset},#{size} ")
    List<Question> listByUserId(@Param(value = "userId") Integer userId,@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);



    @Select("select count(1) from liu.question")
    Integer Count();

    @Select("select count(1) from liu.question where creator=#{userId}")
    Integer CountByUserId(@Param(value = "userId") Integer userId);

    @Select("select * from liu.question where id=#{id}")
    Question getById(@Param("id") Integer id);

    @Update("update liu.question set title=#{title},description=#{description},gmtModified=#{gmtModified},tag=#{tag},countRead=#{countRead} where id=#{id}")
    void updateQuestion(Question question);

}
