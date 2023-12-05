package com.ltstudy.community.DTO;

import com.ltstudy.community.Model.User;
import lombok.Data;

@Data
public class QuestionDTO {
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
    private User user;
}
