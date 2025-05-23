package org.example;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            XmlFormLoader loader = new XmlFormLoader();
            List<Map<String, Object>> fields = loader.loadFields("/application.xml");

            FormStepRenderer renderer = new FormStepRenderer();
            UserInputHandler inputHandler = new UserInputHandler();

            for (int step = 0; step < fields.size(); step++) {
                Map<String, Object> field = fields.get(step);
                String html = renderer.render(field);
                System.out.println(html);

                try (FileWriter fileWriter = new FileWriter("output_page_" + (step + 1) + ".html")) {
                    fileWriter.write(html);
                }

                if ("message".equals(field.get("type"))) {
                    break;
                }

                String input = inputHandler.getInput("Enter your response and press submit: ");
                inputHandler.storeAnswer((String) field.get("name"), input);
            }

            System.out.println("Form submitted. Thank you!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

