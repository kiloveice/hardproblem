package live.hardproblem.config.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.hardproblem.beans.HttpResponseEntity;
import live.hardproblem.util.HttpResponseMessage;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JSONFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");

        PrintWriter out = httpServletResponse.getWriter();


        HttpResponseEntity r = new HttpResponseEntity();
        r.setCode(HttpResponseMessage.unauthenticatedCode);
        r.setMessage(HttpResponseMessage.unauthenticatedMessage);

        ObjectMapper mapper = new ObjectMapper();
        String res = mapper.writeValueAsString(r);

        out.println(res);
        out.flush();
        out.close();
        return false;
    }
}
