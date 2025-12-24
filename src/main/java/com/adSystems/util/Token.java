package com.adSystems.util;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class Token {

    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
