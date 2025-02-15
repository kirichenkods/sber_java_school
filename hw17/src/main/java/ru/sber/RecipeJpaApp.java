package ru.sber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.sber.service.RecipeService;
import ru.sber.ui.UserInterface;

@SpringBootApplication
public class RecipeJpaApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RecipeJpaApp.class);

        UserInterface ui = context.getBean(UserInterface.class);
        RecipeService recipeService = context.getBean(RecipeService.class);

        ui.showMenu();
        String cmd = ui.getUserInput();

        while (!cmd.equals("0")) {
            switch (cmd) {
                case "1" -> recipeService.findRecipe();
                case "2" -> recipeService.deleteRecipe();
                case "3" -> recipeService.addOrUpdateRecipe();
                default -> ui.showMessage("Вводите только предложенные команды\n");
            }
            ui.showMenu();
            cmd = ui.getUserInput();
        }
    }
}
