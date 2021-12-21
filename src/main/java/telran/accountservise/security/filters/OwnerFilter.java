package telran.accountservise.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.model.User;
import telran.accountservise.security.context.SecurityContext;
import telran.accountservise.security.context.UserProfile;
import telran.forumservice.dao.ForumMongoRepository;
import telran.forumservice.model.Post;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;


@Service
@Order(20)
public class OwnerFilter implements Filter {
    SecurityContext securityContext;
    ForumMongoRepository forumRepository;

    @Autowired
    public OwnerFilter(SecurityContext securityContext, ForumMongoRepository forumRepository) {
        this.securityContext = securityContext;
        this.forumRepository = forumRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Principal principal = request.getUserPrincipal();
        UserProfile user = securityContext.getUser(principal.getName());
        if (checkEndPoints(request.getServletPath(), request.getMethod())) {
            String[] arrStr = request.getServletPath().split("/");

            if (!user.getLogin().equals(arrStr[arrStr.length - 1])) {
                response.sendError(403);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkEndPoints(String path, String method) {
        return ("POST".equalsIgnoreCase(method) && path.matches("[/]forum[/]post[/]\\w+[/]?") || path.matches("[/]account[/]user[/]\\w+[/]?"));
    }
}
