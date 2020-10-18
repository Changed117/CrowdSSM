package crowd.mvc.controller;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Pream;
import crowd.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String testSsm(Model model){
        List<Admin> admins = adminService.queryAll();
        model.addAttribute("adminList", admins);
//        String a = null;
//        System.out.println(a.length());
        System.out.println(10/0);
        return "ssmTest";
    }

    @ResponseBody
    @RequestMapping("/send/array/one.html")
    public String testAjax1(@RequestParam("array[]") List<Integer> array){
        for (Integer number: array) {
            System.out.println(number);
        }

        String a = null;
        System.out.println(a.length());
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/two.html")
    public String testAjax2(Pream pream){
        for (Integer number: pream.getArray()) {
            System.out.println(number);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/three.html")
    public String testAjax3(@RequestBody List<Integer> array){
        Logger logger = LoggerFactory.getLogger(TestController.class);

        for (Integer number:array) {
            logger.info("number="+number);
        }
        return "success";
    }
}
