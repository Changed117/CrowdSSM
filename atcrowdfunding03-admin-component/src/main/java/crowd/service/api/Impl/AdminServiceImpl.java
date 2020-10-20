package crowd.service.api.Impl;

import com.Changed.crowd.constat.CrowdConstant;
import com.Changed.crowd.exception.LoginFailedException;
import com.Changed.crowd.util.CrowdUitl;
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
import java.util.Objects;

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

    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据账号查询Admin
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        // 2.如果为null抛异常
        if (admins == null || admins.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        if (admins.size() > 1){
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        // 3.如果查到对象，就把密码取出来和明文加密后进行对比
        Admin admin = admins.get(0);

        if (admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        String userPswdForm = CrowdUitl.md5(userPswd);
        String userPswdDB = admin.getUserPswd();

        // 4.如果一致返回admin对象，不一致抛异常
        if (Objects.equals(userPswdForm,userPswdDB)){
            return admin;
        }else {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
    }
}
