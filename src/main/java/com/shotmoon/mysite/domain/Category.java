package com.shotmoon.mysite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate  //动态修改更新时间
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer userId;
}