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

@Service
@Order(20)
public class AdminFilter  implements Filter {
    UserMongoRepository userRepository;
    SecurityContext securityContext;

    @Autowired
    public AdminFilter(UserMongoRepository userRepository, SecurityContext securityContext) {
        this.userRepository = userRepository;
        this.securityContext = securityContext;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (checkEndPoints(request.getServletPath())){
            Principal principal = request.getUserPrincipal();
            UserProfile user = securityContext.getUser(principal.getName());
            if (!user.getRoles().contains("ADMINISTRATOR".toUpperCase())){
                response.sendError(403);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkEndPoints(String path) {
        return (path.matches("[/]account[/]user[/]\\w+[/]role[/]\\w+[/]?")); // + -> сколько угодно раз
    }
}
