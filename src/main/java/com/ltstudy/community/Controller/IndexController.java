package com.ltstudy.community.Controller;


import com.ltstudy.community.DTO.PageDTO;
import com.ltstudy.community.DTO.QuestionDTO;
import com.ltstudy.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        @RequestParam(name = "search",required = false) String search) {

        PageDTO pageDTO=questionService.list(page, size);
        //PageDTO pageDTO1=questionService.list2(search,page,size);
        model.addAttribute("search",search);
        model.addAttribute("pagination",pageDTO);
        //model.addAttribute("pagination1",pageDTO1);
        System.out.println(search);
        List<QuestionDTO> questionDTOList=questionService.list2(search);
        model.addAttribute("questionList",questionDTOList);
        //System.out.println(questionDTOList.get(1));
        return "index";
    }

}
