package com.ltstudy.community.Enums;




public enum CommentEnums {
    QUESTION(1),
    COMMENT(2);

    private Integer type;
    CommentEnums(Integer type){
        this.type=type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public static boolean isExit(Integer type){
        for (CommentEnums commentEnums : CommentEnums.values()) {
            if(commentEnums.getType()==type){
                return  true;
            }
        }
        return false;
    }
}
