package telran.accountservise.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.accountservise.dao.UserMongoRepository;
import telran.accountservise.dto.*;
import telran.accountservise.dto.exceptions.UserAlreadyExistException;
import telran.accountservise.dto.exceptions.UserNotFondException;
import telran.accountservise.model.User;

@Service
public class UserServiceImpl implements UserService {
    UserMongoRepository userMongoRepository;
    ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserMongoRepository userMongoRepository, ModelMapper modelMapper) {
        this.userMongoRepository = userMongoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto addUser(User user) {
        if (userMongoRepository.existsById(user.getLogin())) throw new UserAlreadyExistException(user.getLogin());
        userMongoRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto login(LoginDto loginDto) {
        User user = userMongoRepository.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
        if (user == null) throw new UserNotFondException(loginDto.getLogin());
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String login) {
        User user = userMongoRepository.findById(login).orElseThrow(() -> new UserNotFondException(login));
        userMongoRepository.deleteById(login);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
        User user = userMongoRepository.findById(login).orElseThrow(() -> new UserNotFondException(login));
        if (updateUserDto.getFirstName() != null) user.setFirstName(updateUserDto.getFirstName());
        if (updateUserDto.getFirstName() != null) user.setLastName(updateUserDto.getLastName());
        userMongoRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public RolesDto addRoles(String login, String role) {
        User user = userMongoRepository.findById(login).orElseThrow(() -> new UserNotFondException(login));
        user.getRoles().add(role.toUpperCase());
        userMongoRepository.save(user);
        return modelMapper.map(user, RolesDto.class);
    }

    @Override
    public RolesDto deleteRole(String login, String role) {
        User user = userMongoRepository.findById(login).orElseThrow(() -> new UserNotFondException(login));
        user.getRoles().remove(role.toUpperCase());
        userMongoRepository.save(user);
        return modelMapper.map(user, RolesDto.class);
    }

    @Override
    public boolean changePassword(LoginDto loginDto) {
        User user = userMongoRepository.findById(loginDto.getLogin()).orElseThrow(() -> new UserNotFondException(loginDto.getLogin()));
        user.setPassword(loginDto.getPassword());
        userMongoRepository.save(user);
        return true;
    }
}
