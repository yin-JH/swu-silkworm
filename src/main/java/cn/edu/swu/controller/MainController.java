package cn.edu.swu.controller;

import cn.edu.swu.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/questions")
    public String questions(){return "questions";}

    @RequestMapping("/admin")
    public String admin() {
        //@RequestParam(name = "pageNum",defaultValue = "1") int pageNum, @RequestParam(name = "pageSize",defaultValue = "10") int pageSize, Model model
        //System.err.println(pageNum + "  " + pageSize);

        /*PageInfo pageInfo = adminService.itemsRetrieve(pageNum, pageSize);
        model.addAttribute("questionPageInfo",pageInfo);*/
        return "admin";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}