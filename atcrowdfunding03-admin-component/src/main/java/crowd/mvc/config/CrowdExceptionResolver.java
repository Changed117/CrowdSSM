package crowd.mvc.config;


import com.Changed.crowd.constat.CrowdConstant;
import com.Changed.crowd.exception.LoginAcctAlreadyInUseException;
import com.Changed.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.Changed.crowd.exception.LoginFailedException;
import com.Changed.crowd.util.CrowdUitl;
import com.Changed.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CrowdExceptionResolver {

    @ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
    public ModelAndView resolverLoginAcctAlreadyInUseForUpdateException(LoginAcctAlreadyInUseForUpdateException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return commonResoolve(viewName, exception, request, response);
    }

    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolverLoginAcctAlreadyInUseException(LoginAcctAlreadyInUseException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-add";
        return commonResoolve(viewName, exception, request,response);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolverLoginFailedException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return commonResoolve(viewName, exception, request,response);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolverNullPointerException(NullPointerException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return commonResoolve(viewName, exception, request,response);
    }


    private ModelAndView commonResoolve(String viewName,Exception exception,HttpServletRequest request,HttpServletResponse response) throws IOException {
        boolean requestType = CrowdUitl.judgeRequestType(request);
        if (requestType){
            ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());

            Gson gson = new Gson();

            String json = gson.toJson(failed);

            response.getWriter().write(json);

            return null;
        }
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);

        modelAndView.setViewName(viewName);

        return modelAndView;

    }
}
