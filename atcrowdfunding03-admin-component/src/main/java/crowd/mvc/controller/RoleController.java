package crowd.mvc.controller;

import com.Changed.crowd.constat.CrowdConstant;
import com.Changed.crowd.util.ResultEntity;
import com.atguigu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;
import crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/roles/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        PageInfo<Role> rolePageInfo = roleService.getRolePageInfo(pageNum, pageSize, keyword);


        return ResultEntity.successWithData(rolePageInfo);
    }

}
