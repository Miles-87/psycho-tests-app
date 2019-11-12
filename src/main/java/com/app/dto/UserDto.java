package com.app.dto;

import com.app.model.enums.Education;
import com.app.model.enums.Gender;
import com.app.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private Education education;
    private Gender gender;
    private Role role;
}
