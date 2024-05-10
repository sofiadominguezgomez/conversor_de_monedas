package com.alura.challenge;

public record Rate(
        String base_code,
        String target_code,
        double conversion_rate
) {
}
