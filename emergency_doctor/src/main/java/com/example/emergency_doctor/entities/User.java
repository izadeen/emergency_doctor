package com.example.emergency_doctor.entities;

import com.example.emergency_doctor.enums.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //@NotBlank(message = "Username is mandatory")
    private String username;
    //@NotBlank(message = "Password is mandatory")
    private String password;
    //@NotBlank(message = "Email is mandatory")
    private String email;

    private Role role;

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
