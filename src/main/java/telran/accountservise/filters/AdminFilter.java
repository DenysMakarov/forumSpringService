package telran.accountservise.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Service
@Order(20)
public class AdminFilter  implements Filter {
    UserMongoRepository userRepository;

    @Autowired
    public AdminFilter(UserMongoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Principal principal = request.getUserPrincipal();
        if (checkEndPoints(request.getServletPath(), request.getMethod())){
            User user = userRepository.findById(principal.getName()).get();
            if (!user.getRoles().contains("ADMINISTRATOR".toUpperCase())){
                response.sendError(403);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkEndPoints(String path, String method) {
        return (path.matches("[/]account[/]user[/]\\w+[/]role[/]\\w+[/]?")); // + -> сколько угодно раз
    }
}
