package com.ltstudy.community.DTO;


import com.ltstudy.community.Model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentDTO {
    private List<Comment> comments;
}
