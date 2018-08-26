package com.shotmoon.mysite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate  //动态修改更新时间
public class User implements Serializable{


    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String avatar;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role = 1;

}