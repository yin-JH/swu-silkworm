package cn.edu.swu.controller;

import cn.edu.swu.service.AskQuestionsService;
import cn.edu.swu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    AskQuestionsService askQuestionsService;

    /*@RequestMapping("/AddQuestionFromExcelToMySql")
    @ResponseBody
    public String AddQuestionFromExcelToMySql(){
        testService.AddQuestionFromExcelToMySql();
        return "ok";
    }*/

    @RequestMapping("01")
    @ResponseBody
    public String test01(){
        askQuestionsService.askOneQ("蚕宝宝有多大");
        return "ok";
    }
}
