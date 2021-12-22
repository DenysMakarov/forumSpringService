package telran.accountservise.service;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.dto.*;
import telran.accountservise.dto.exceptions.UserAlreadyExistException;
import telran.accountservise.dto.exceptions.UserNotFondException;
import telran.accountservise.model.UserAccount;

@Service
public class UserServiceImpl implements UserService {
    UserMongoRepository userMongoRepository;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMongoRepository userMongoRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userMongoRepository = userMongoRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto addUser(UserAccount user) {
        if (userMongoRepository.existsById(user.getLogin())) throw new UserAlreadyExistException(user.getLogin());
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userMongoRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

//    @Override
//    public UserDto login(LoginDto loginDto) {
//        User user = userMongoRepository.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
//        if (user == null) throw new UserNotFondException(loginDto.getLogin());
//        return modelMapper.map(user, UserDto.class);
//    }

    @Override
    public UserDto login(String str) {
        UserAccount user = userMongoRepository.findById(str).orElseThrow(() -> new UserNotFondException(str));
        if (user == null) throw new UserNotFondException(str);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String login) {
        UserAccount user = userMongoRepository.findById(login).orElseThrow(() -> new UserNotFondException(login));
        userMongoRepository.deleteById(login);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
        UserAccount user = userMongoRepository.findById(login).orElseThrow(() -> new UserNotFondException(login));
        if (updateUserDto.getFirstName() != null) user.setFirstName(updateUserDto.getFirstName());
        if (updateUserDto.getFirstName() != null) user.setLastName(updateUserDto.getLastName());
        userMongoRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public RolesDto addRoles(String login, String role) {
        UserAccount user = userMongoRepository.findById(login).orElseThrow(() -> new UserNotFondException(login));
        user.getRoles().add(role.toUpperCase());
        userMongoRepository.save(user);
        return modelMapper.map(user, RolesDto.class);
    }

    @Override
    public RolesDto deleteRole(String login, String role) {
        UserAccount user = userMongoRepository.findById(login).orElseThrow(() -> new UserNotFondException(login));
        user.getRoles().remove(role.toUpperCase());
        userMongoRepository.save(user);
        return modelMapper.map(user, RolesDto.class);
    }

    @Override
    public void changePassword(String login, String password) {
        UserAccount userAccount = userMongoRepository.findById(login).orElseThrow(() -> new UserNotFondException(login));
        userAccount.setPassword(passwordEncoder.encode(password));
        userMongoRepository.save(userAccount);
    }
}
