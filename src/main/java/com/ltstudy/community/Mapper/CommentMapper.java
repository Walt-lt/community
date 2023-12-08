package com.ltstudy.community.Mapper;

import com.ltstudy.community.Model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into liu.comment (parentId, type, commentator, gmtCreate, gmtModified, content,commentCount,likeCount,questionId)" +
            " values ( #{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{content},#{commentCount},#{likeCount} ,#{questionId})")
    void insertComment(Comment comment);

    @Select("select * from liu.comment where id=#{questionId}")
    Comment getById(int questionId);

    @Update("update liu.comment set parentId=#{parentId},type=#{type},commentator=#{commentator},gmtCreate=#{gmtCreate},gmtModified=#{gmtModified},content=#{content},commentCount=#{commentCount},likeCount=#{likeCount},questionId=#{questionId}")
    void updateComment(Comment comment);

    @Select("select * from liu.comment where questionId=#{questionId}")
    List<Comment> listByQuestionId(Integer questionId);

    @Select("select * from liu.comment where commentator=#{commentator}")
    List<Comment> listByCommentator(Integer commentator);
}
