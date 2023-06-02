package com.qstar.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfile {
    private String name;
    private String email;
    private String photo;
}
