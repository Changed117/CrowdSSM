package crowd.mvc.interceptor;

import com.Changed.crowd.constat.CrowdConstant;
import com.Changed.crowd.exception.AccessForbiddenException;
import com.atguigu.crowd.entity.Admin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.channels.AcceptPendingException;
import java.security.AccessControlException;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 1.获取session对象
        HttpSession session = request.getSession();
        // 2.尝试获取Admin
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        // 3.判断是否为空
        if(admin == null){
            // 4.为空就是没登录
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }

        // 5.不为空允许通过
        return true;
    }
}
