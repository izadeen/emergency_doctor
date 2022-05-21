package com.example.emergency_doctor.conrollers;

import com.example.emergency_doctor.entities.User;
import com.example.emergency_doctor.enums.Role;
import com.example.emergency_doctor.models.UserFromClint;
import com.example.emergency_doctor.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("new/user")
    public User addUser(@RequestBody UserFromClint user){
        User user1=User.builder()
                .username(user.getUsername())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(Role.DOCTOR)
                .build();
        return userService.save(user1);
    }

    @GetMapping("get-all")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }
}
