package com.lgonzalez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long idMember;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photoUrl;
}
