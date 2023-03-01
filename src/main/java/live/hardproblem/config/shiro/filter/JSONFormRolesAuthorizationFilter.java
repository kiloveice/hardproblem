package live.hardproblem.config.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.hardproblem.beans.HttpResponseEntity;
import live.hardproblem.util.HttpResponseMessage;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JSONFormRolesAuthorizationFilter extends RolesAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");

        PrintWriter out = httpServletResponse.getWriter();


        HttpResponseEntity r = new HttpResponseEntity();
        r.setCode(HttpResponseMessage.unauthorizedCode);
        r.setMessage(HttpResponseMessage.unauthorizedMessage);

        ObjectMapper mapper = new ObjectMapper();
        String res = mapper.writeValueAsString(r);

        out.println(res);
        out.flush();
        out.close();
        return false;
    }
}
