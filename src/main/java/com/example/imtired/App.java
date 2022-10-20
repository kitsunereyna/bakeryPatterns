package com.example.imtired;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        URL file = new File("/Users/User/IdeaProjects/imtired/src/main/java/com/example/imtired/cartui.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(file);
        stage.setTitle("Bakery");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getWindow(){
        return window;
    }

}