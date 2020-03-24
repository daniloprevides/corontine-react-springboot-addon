package com.corontine.addon.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.corontine.addon.model.RecipeEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/recipes")
    public RecipeEntity greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new RecipeEntity("my","recipe");
    }
}