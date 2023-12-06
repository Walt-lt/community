package com.ltstudy.community.Mapper;

import com.ltstudy.community.Model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {
    @Insert("insert into liu.comment (parentId, type, commentator, gmtCreate, gmtModified, content,commentCount,likeCount)" +
            " values ( #{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{content},#{commentCount},#{likeCount} )")
    void insertComment(Comment comment);

    @Select("select * from liu.comment where id=#{parentId}")
    Comment getById(int parentId);
}
