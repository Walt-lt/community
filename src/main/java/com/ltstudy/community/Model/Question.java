package com.ltstudy.community.Model;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer countRead;
    private Integer countComment;
    private Integer countLike;
}
