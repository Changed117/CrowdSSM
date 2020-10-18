package crowd.service.api.Impl;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import crowd.mapper.AdminMapper;
import crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRES_NEW,readOnly = true)
    public Admin queryAdminById(int id){
        return adminMapper.selectByPrimaryKey(id);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRES_NEW,readOnly = false,rollbackFor = java.lang.Exception.class)
    public int deleteAdminById(int id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRES_NEW,readOnly = true)
    public List<Admin> queryAll() {
        return adminMapper.selectByExample(new AdminExample());
    }


}
