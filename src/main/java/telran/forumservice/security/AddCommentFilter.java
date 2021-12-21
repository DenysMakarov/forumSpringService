package telran.forumservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class AddCommentFilter implements Filter{
    ForumMongoRepository repository;
    SecurityContext securityContext;

    @Autowired
    public AddCommentFilter(ForumMongoRepository repository, SecurityContext securityContext) {
        this.repository = repository;
        this.securityContext = securityContext;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Principal principal = request.getUserPrincipal();

        if (checkEndPoints(request.getServletPath(), request.getMethod())) {
            String[] arrStr = request.getServletPath().split("/");
            String author = arrStr[arrStr.length - 1];
            String postId = arrStr[arrStr.length - 3];

            Post post = repository.findById(postId).orElse(null);
            UserProfile user = securityContext.getUser(principal.getName());


            if (!author.equals(principal.getName()) || user == null || post == null) {
                response.sendError(403);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkEndPoints(String path, String method) {
        return "PUT".equalsIgnoreCase(method) && path.matches("[/]forum[/]post[/]\\w+[/]comment[/]\\w+[/]?");
    }
}
