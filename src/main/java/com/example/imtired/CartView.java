package com.example.imtired;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CartView {
    private Parent view;

    public CartView() throws IOException {
        URL uiFile = new File("/Users/User/IdeaProjects/imtired/src/main/java/com/example/imtired/cart.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(uiFile);
        this.view = root;
    }

    public Parent getView(){
        return this.view;
    }
}
