package telran.accauntservise.service;

import telran.accauntservise.dto.LoginDto;
import telran.accauntservise.dto.RolesDto;
import telran.accauntservise.dto.UpdateUserDto;
import telran.accauntservise.dto.UserDto;
import telran.accauntservise.model.User;

public interface UserService {
    UserDto addUser(User user);
    UserDto login(LoginDto loginDto);
    UserDto deleteUser(String login);
    UserDto updateUser(UpdateUserDto user);
    RolesDto addRoles(String login, String role);
    RolesDto deleteRole(String login, String role);
    boolean changePassword(LoginDto loginDto);
}
