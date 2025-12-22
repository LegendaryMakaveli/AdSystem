package com.adSystems.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class Token {

    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
