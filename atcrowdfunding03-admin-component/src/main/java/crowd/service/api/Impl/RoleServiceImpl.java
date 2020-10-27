package crowd.service.api.Impl;

import com.atguigu.crowd.entity.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import crowd.mapper.RoleMapper;
import crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public PageInfo<Role> getRolePageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 1.调用PageHelper开启分页功能
        PageHelper.startPage(pageNum,pageSize);

        // 2.调用方法查询数据
        List<Role> list = roleMapper.selectRoleByKeyword(keyword);

        // 3.数据封装至PageInfo
        return new PageInfo<>(list);
    }
}
