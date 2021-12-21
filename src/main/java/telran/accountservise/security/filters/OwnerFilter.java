package telran.accountservise.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.model.User;
import telran.accountservise.security.context.SecurityContext;
import telran.accountservise.security.context.UserProfile;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.LogRecord;

@Service
@Order(30)
public class OwnerFilter implements Filter{
    SecurityContext securityContext;

    @Autowired
    public OwnerFilter(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        if (checkEndPoints(request.getServletPath(), request.getServletPath())){
            Principal principal = request.getUserPrincipal();
            UserProfile user = securityContext.getUser(principal.getName());
            String[] str = request.getServletPath().split("/");

            if (!user.getLogin().equals(str[str.length-1])){
                response.sendError(403);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkEndPoints(String path, String method) {
        return (
                path.matches("[/]account[/]user[/]\\w+[/]?")
                || "POST".equalsIgnoreCase(method) && path.matches("[/]forum[/]post[/]\\w+[/]?")
        );
    }
}
