package telran.accauntservise.controller;

import org.springframework.web.bind.annotation.*;
import telran.accauntservise.dto.LoginDto;
import telran.accauntservise.dto.RolesDto;
import telran.accauntservise.dto.UpdateUserDto;
import telran.accauntservise.dto.UserDto;
import telran.accauntservise.model.User;
import telran.accauntservise.service.UserService;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/account/register")
    public UserDto addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/account/login")
    public UserDto loginDto(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

    @DeleteMapping("/account/user/@{login}")
    public UserDto deleteUser(@PathVariable String login){
        return userService.deleteUser(login);
    }

    @PutMapping ("/account/user/@{login}")
    public UserDto updateUser(@RequestBody UpdateUserDto updateUserDto){
        return userService.updateUser(updateUserDto);
    }

    @PutMapping("/account/@{user}/role/@{role}")
    public RolesDto addRole(@PathVariable String user, @PathVariable String role){
        return userService.addRoles(user, role);
    }

    @DeleteMapping("/account/@{user}/role/@{role}")
    public RolesDto deleteRole(@PathVariable String user, @PathVariable String role){
        return userService.deleteRole(user, role);
    }

    @PutMapping("/account/user/password")
    public boolean changePassword(@RequestBody LoginDto loginDto){
        return userService.changePassword(loginDto);
    }
}
