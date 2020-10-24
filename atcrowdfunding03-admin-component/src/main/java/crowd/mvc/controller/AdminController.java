package crowd.mvc.controller;


import com.Changed.crowd.constat.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;
import crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/update.html")
    public String update(
            Admin admin,
            @RequestParam("pageNum")Integer pageNum,
            @RequestParam("keyword")String keyword
    ){
        adminService.update(admin);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("/admin/to/edit/page.html")
    public String toEditPage(
            @RequestParam("adminId")Integer adminId,
            Model model
    ){
        // 获取Admin并回显
        Admin admin = adminService.queryAdminById(adminId);
        model.addAttribute("admin", admin);

        return "admin-edit";
    }

    @RequestMapping("/admin/save.html")
    public String saveAdmin(Admin admin){

        adminService.saveAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }

    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String removeAdmin(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword
    ){
        // 执行删除
        adminService.remove(adminId);

        // 跳转页面,重定向避免刷新执行多次删除操作
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "")String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            Model model
            ){
        // 1.获取数据
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);

        // 2.放进modle
        model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        // 3.返回页面
        return "admin-page";
    }

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
