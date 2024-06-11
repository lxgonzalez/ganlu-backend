package com.lgonzalez.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private long idMember;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photoUrl;
}
