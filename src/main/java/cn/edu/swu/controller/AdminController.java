package cn.edu.swu.controller;

import cn.edu.swu.mapper.QuestionMapper;
import cn.edu.swu.service.AdminService;
import cn.edu.swu.service.AskQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Mou
 * @Date: 2021/3/7 21:45
 * @Description: 后台管理相关界面
 * @Version: 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    QuestionMapper questionMap;

    @Autowired
    AskQuestionsService askQuestionsService;

    @Autowired
    AdminService adminService;

    /**
     * 写入功能
     */
    @RequestMapping("/retrieve")
    @ResponseBody
    public String progress(){
        return adminService.itemsRetrieve();
    }


}
