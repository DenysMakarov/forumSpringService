package telran.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.dto.exceptions.UserNotFondException;
import telran.accountservise.model.UserAccount;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    UserMongoRepository userMongoRepository;

    @Autowired
    public UserDetailsServiceImpl(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    // создаем контекст (Authentication service)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userMongoRepository.findById(username).orElseThrow(()-> new UserNotFondException(username));

        String[] roles = userAccount.getRoles().stream()
                .map(r -> "ROLE_" + r.toUpperCase())
                .toArray(String[]::new);

        return new User(username, userAccount.getPassword(), AuthorityUtils.createAuthorityList(roles));
    }
}
