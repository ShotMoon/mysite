package com.shotmoon.mysite.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shotmoon
 */
@Getter
@AllArgsConstructor
public enum SortEnum {
    ASC(1, "升序"),
    DESC(-1, "降序"),
    ;

    private Integer code;
    private String message;

}
