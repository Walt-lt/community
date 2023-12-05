package com.ltstudy.community.Controller;


import com.ltstudy.community.DTO.PageDTO;
import com.ltstudy.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){

        PageDTO pageDTO=questionService.list(page, size);
        model.addAttribute("pagination",pageDTO);
        return "index";
    }

}
