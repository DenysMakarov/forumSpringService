package telran.accountservise.service;

import telran.accountservise.dto.LoginDto;
import telran.accountservise.dto.RolesDto;
import telran.accountservise.dto.UpdateUserDto;
import telran.accountservise.dto.UserDto;
import telran.accountservise.model.User;

public interface UserService {
    UserDto addUser(User user);
    UserDto login(String login);
    UserDto deleteUser(String login);
    UserDto updateUser(String login, UpdateUserDto user);
    RolesDto addRoles(String login, String role);
    RolesDto deleteRole(String login, String role);
    boolean changePassword(LoginDto loginDto);
}
