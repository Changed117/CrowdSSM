package crowd.service.api;

import com.atguigu.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {

    public Admin queryAdminById(int id);

    public int deleteAdminById(int id);

    public List<Admin> queryAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void remove(Integer adminId);

    void saveAdmin(Admin admin);

    void update(Admin admin);
}
