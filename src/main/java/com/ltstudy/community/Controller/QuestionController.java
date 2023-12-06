package com.ltstudy.community.Controller;

import com.ltstudy.community.DTO.QuestionDTO;
import com.ltstudy.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        QuestionDTO questionDTO=questionService.GetById(id);
        //计算阅读数
        questionService.countView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }

}
