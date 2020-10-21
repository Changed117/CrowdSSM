package crowd.mvc.controller;


import com.Changed.crowd.constat.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/to/logout/page.html")
    public String doLogout(HttpSession session){
        //退出登录，强制清空session
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    @RequestMapping("/admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPswd") String userPswd,
            HttpSession session
            ){

        //service获取Admin对象
        Admin admin =  adminService.getAdminByLoginAcct(loginAcct ,userPswd);

        //把Admin对象放进session域
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);

        //如果账号密码正确返回到登陆成功页面
        return "redirect:/admin/to/main/page.html";
    }
}
