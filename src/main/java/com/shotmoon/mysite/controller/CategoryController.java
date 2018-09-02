package com.shotmoon.mysite.controller;

import com.shotmoon.mysite.common.ServerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shotmoon
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @PostMapping
    public ServerResponse addCategory() {
        return null;
    }
}
