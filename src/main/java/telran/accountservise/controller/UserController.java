package telran.accountservise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.accountservise.dto.LoginDto;
import telran.accountservise.dto.RolesDto;
import telran.accountservise.dto.UpdateUserDto;
import telran.accountservise.dto.UserDto;
import telran.accountservise.model.User;
import telran.accountservise.service.UserService;

import java.security.Principal;
import java.util.Base64;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/account/register")
    public UserDto addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    //    @PostMapping("/account/login")
//    public UserDto loginDto(@RequestHeader("Authorization") String token){
//        token = token.split(" ")[1];
//        byte[] bytesDecode = Base64.getDecoder().decode(token);
//        token = new String(bytesDecode);
//        System.out.println(token);
//        String[] credentials = token.split(":");
//        return userService.login(credentials[0]);
//    }
    @PostMapping("/account/login")
    public UserDto loginDto(Principal principal) {
        return userService.login(principal.getName());
    }

    @DeleteMapping("/account/user/{login}")
    public UserDto removeUser(@PathVariable String login) {
        return userService.deleteUser(login);
    }

    @PutMapping("/account/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(login, updateUserDto);
    }

    @PutMapping("/account/user/{user}/role/{role}")
    public RolesDto addRole(@PathVariable String user, @PathVariable String role) {
        return userService.addRoles(user, role);
    }

    @DeleteMapping("/account/user/{user}/role/{role}")
    public RolesDto deleteRole(@PathVariable String user, @PathVariable String role) {
        return userService.deleteRole(user, role);
    }

    @PutMapping("/account/user/password")
    public boolean changePassword(@RequestBody LoginDto loginDto) {
        return userService.changePassword(loginDto);
    }
}
