package com.themehub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    public Long id;

    public String username;

    public String email;

    public String password;

    public String first_name;

    public String last_name;

    public String company;

    public String state;

}
