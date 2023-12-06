package com.ltstudy.community.DTO;


import lombok.Data;

@Data
public class CommentDTO {
    private int id;
    private int parentId;
    private int type;
    private int commentator;
    private long gmtCreate;
    private long gmtModified;
    private int likeCount;
    private int commentCount;
    private String content;
}
