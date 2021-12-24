package telran.security;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.model.UserAccount;
import telran.forumservice.dao.ForumMongoRepository;
import telran.forumservice.model.Post;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;

@Service("customWebSecurity") // можем поменять название бина
public class CustomSecurity {

    ForumMongoRepository forumMongoRepository;
    UserMongoRepository userMongoRepository;

    @Autowired
    public CustomSecurity(ForumMongoRepository forumMongoRepository, UserMongoRepository userMongoRepository) {
        this.forumMongoRepository = forumMongoRepository;
        this.userMongoRepository = userMongoRepository;
    }

    public boolean checkPostAuthority(String postId, String userName) {
        Post post = forumMongoRepository.findById(postId).orElse(null);
        return post != null && userName.equals(post.getAuthor());
    }
}
