package com.example.imtired.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeView {
    private Parent view;

    public HomeView() throws IOException {
        URL url = new File("/Users/User/IdeaProjects/imtired/src/main/java/com/example/imtired/home/home.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(url);
        this.view = root;
    }

    public Parent getView(){
        return view;
    }

}
