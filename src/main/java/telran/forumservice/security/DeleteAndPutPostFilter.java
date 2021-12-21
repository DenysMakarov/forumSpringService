package telran.forumservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.security.context.SecurityContext;
import telran.accountservise.security.context.UserProfile;
import telran.forumservice.dao.ForumMongoRepository;
import telran.forumservice.model.Post;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Component
public class DeleteAndPutPostFilter implements Filter {
    ForumMongoRepository repository;
    SecurityContext securityContext;

    @Autowired
    public DeleteAndPutPostFilter(ForumMongoRepository repository, SecurityContext securityContext) {
        this.repository = repository;
        this.securityContext = securityContext;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Principal principal = request.getUserPrincipal();

        if (checkEndPoints(request.getServletPath(), request.getMethod())){
            String[] arrStr = request.getServletPath().split("/");
            String str = arrStr[arrStr.length-1];
            Post post = repository.findById(str).orElse(null);
            UserProfile user = securityContext.getUser(principal.getName());
//            System.out.println(user.getRoles());

//            !(user.getRoles().contains("MODERATOR".toUpperCase())) ||
            if ( post == null || !post.getAuthor().equals(principal.getName())){
                response.sendError(403);
                return;
            }
        }

//        if (!user.getRoles().contains("ADMINISTRATOR".toUpperCase())){
//            response.sendError(403);
//            return;
//        }

        filterChain.doFilter(request, response);
    }

    private boolean checkEndPoints(String path, String method) {
        return ("DELETE".equalsIgnoreCase(method) && path.matches("[/]forum[/]post[/]\\w+[/]?"));
//        return ("DELETE".equalsIgnoreCase(method) && path.matches("[/]forum[/]post[/]\\w+[/]?") || "PUT".equalsIgnoreCase(method) && path.matches("[/]forum[/]post[/]\\w+[/]comment[/]\\w+[/]?"));
    }
}
