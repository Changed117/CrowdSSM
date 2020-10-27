package crowd.service.api;

import com.atguigu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    PageInfo<Role> getRolePageInfo(Integer pageNum,Integer pageSize,String keyword);


}
