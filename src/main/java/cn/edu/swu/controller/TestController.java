package cn.edu.swu.controller;

import cn.edu.swu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v0.01/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/AddQuestionFromExcelToMySql")
    @ResponseBody
    public String AddQuestionFromExcelToMySql(){
        testService.AddQuestionFromExcelToMySql();
        return "ok";
    }
}
