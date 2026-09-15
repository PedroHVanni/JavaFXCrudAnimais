package com.template;

import com.template.controller.MainController;
import com.template.validator.AnimalValidador;
import com.template.validator.IAnimalValidador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        IAnimalValidador userValidador = new AnimalValidador();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main.fxml")
        );

        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == MainController.class) {
                return new MainController(userValidador);
            }

            try {
                return controllerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Não foi possível criar o controller: " + controllerClass.getName(), e);
            }
        });

        Scene scene = new Scene(loader.load());

        stage.setTitle("CRUD de Animais");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
