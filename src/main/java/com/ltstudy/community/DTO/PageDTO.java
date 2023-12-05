package com.ltstudy.community.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showLastPage;
    private Integer totalPage;
    private Integer page;
    private List<Integer> pages=new ArrayList<>();

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage=totalPage;
        //容错处理
        this.page=page;

        pages.add(page);
        //逻辑算法
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }

        }




        //是否展示上一页
        if (page==1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }
        //是否展示下一页
        if (page.equals(totalPage) ){
            showNext=false;
        }else {
            showNext=true;
        }
        //第一页
        if(page == 1){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        //最后一页
        if(page.equals(totalPage)){
            showLastPage=false;
        }else {
            showLastPage=true;
        }



        //


    }
}
