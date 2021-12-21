package telran.accountservise.filters;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

@Service
@Order(10)
public class AuthenticationFilter implements Filter {

    UserMongoRepository repository;

    @Autowired
    public AuthenticationFilter(UserMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (checkEndPoints(request.getServletPath(), request.getMethod())) {
            String token = request.getHeader("Authorization");
            if (token == null) {
                response.sendError(401, "Header Authorization not fond");
                return;
            }
            String[] credentials = getCredential(token).orElse(null);
            if (credentials == null || credentials.length < 2) {
                response.sendError(401, "Token not valid");
                return;
            }
            User user = repository.findById(credentials[0]).orElse(null);
            if (user == null) {
                response.sendError(401, "User not valid");
                return;
            }
            if (!BCrypt.checkpw(credentials[1], user.getPassword())) {
                response.sendError(401, "User or pass not a valid");
                return;
            }
            request = new WrappedRequest(request, credentials[0]);
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkEndPoints(String path, String method) {
        return !(("POST".equalsIgnoreCase(method) && path.matches("[/]account[/]register[/]?"))
                        || path.matches("[/]forum[/]posts([/]\\w+)+[/]?")); // + -> сколько угодно раз
    }

    private Optional<String[]> getCredential(String token) {
        String[] res = null;
        try {
            System.out.println("Token" + token);
            token = token.split(" ")[1]; // Basic || iWjNHBb873bGVgw7hBV
            byte[] bytesDecode = Base64.getDecoder().decode(token);
            token = new String(bytesDecode); // Декодируем
            res = token.split(":");
            System.out.println(res[0] + " : " + res[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(res);
    }

    private static class WrappedRequest extends HttpServletRequestWrapper{
        String login;

        public WrappedRequest(HttpServletRequest request, String login) {
            super(request);
            this.login = login;
        }

        @Override
        public Principal getUserPrincipal(){
//            return new Principal() {
//                @Override
//                public String getName() {
//                    return login;
//                }
//            };
           return  () -> login;
        }
    }
}
