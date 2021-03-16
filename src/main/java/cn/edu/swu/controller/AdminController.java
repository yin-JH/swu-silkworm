package cn.edu.swu.controller;

import cn.edu.swu.mapper.QuestionMapper;
import cn.edu.swu.service.AdminService;
import cn.edu.swu.service.AskQuestionsService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    public String retrieve() {
        String  res = adminService.itemsRetrieve();
        return res;
    }

    /*@RequestMapping("/updateData")
    @ResponseBody
    public String updateDate(String data){
        System.out.println(data);
        return adminService.itemsRetrieve();
    }*/

    @RequestMapping("/editData")
    @ResponseBody
    public String editData(@RequestParam("id") String strId,
                           @RequestParam("problem") String problem,
                           @RequestParam("type") String type,
                           @RequestParam("media_type") String media_type,
                           @RequestParam("answer") String answer, HttpServletRequest request) {
        problem = problem.replace("\"","\\\"");
        System.out.println(problem);
        answer = answer.replace("\"","\\\"");
        System.out.println(answer);

        Long id = Long.parseLong(strId);
        if (id == -1L) {
            adminService.itemAdd(problem,type,media_type,answer);
        } else {
            Long newId = Long.parseLong(strId);
            adminService.itemUpdateAll(newId,problem,type,media_type,answer);
        }
        return "success";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") String id,HttpServletRequest request){
        Long newId = Long.parseLong(id);
        adminService.itemsDelete(newId);
        return "success";
    }

    @RequestMapping("/userQuestionsRetrieve")
    @ResponseBody
    public String userQuestionsRetrieve() {
        String  res = adminService.getUserQuestions();
        return res;
    }

    @RequestMapping("/saveNewQuestion")
    @ResponseBody
    public String saveNewQuestion(@RequestParam("problem") String problem,
                           @RequestParam("type") String type,
                           @RequestParam("media_type") String media_type,
                           @RequestParam("answer") String answer, HttpServletRequest request) {
        adminService.itemAdd(problem,type,media_type,answer);
        return "success";
    }

}
