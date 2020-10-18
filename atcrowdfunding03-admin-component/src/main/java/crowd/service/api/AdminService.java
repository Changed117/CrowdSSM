package crowd.service.api;

import com.atguigu.crowd.entity.Admin;

import java.util.List;

public interface AdminService {

    public Admin queryAdminById(int id);

    public int deleteAdminById(int id);

    public List<Admin> queryAll();
}
