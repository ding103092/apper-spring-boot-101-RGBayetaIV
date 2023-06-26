package com.apper;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
public class IdGeneratorService {
    private static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Set<String> generatedCodes = new HashSet<>();
    public String generateRandomCharacters(int MAX_VERIFICATION_CODE_LENGTH) {
        String randomCharacters;
        Random random = new Random();

        // Generate random characters until it's unique
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < MAX_VERIFICATION_CODE_LENGTH; i++) {
                int randomIndex = random.nextInt(ALPHANUMERIC.length());
                sb.append(ALPHANUMERIC.charAt(randomIndex));
            }
            randomCharacters = sb.toString();
        } while (generatedCodes.contains(randomCharacters));

        generatedCodes.add(randomCharacters);

        return randomCharacters;
    }

    public String getNextId() {
        return UUID.randomUUID().toString();
    }
}
