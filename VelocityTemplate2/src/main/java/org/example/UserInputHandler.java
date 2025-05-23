package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, String> answers = new HashMap<>();

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void storeAnswer(String fieldName, String value) {
        answers.put(fieldName, value);
    }

    public Map<String, String> getAnswers() {
        return answers;
    }
}

