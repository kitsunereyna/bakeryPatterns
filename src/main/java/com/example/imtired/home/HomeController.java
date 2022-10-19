package com.example.imtired.home;

import com.example.imtired.Product;
import com.example.imtired.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HomeController {
    @FXML
    private GridPane productGridPane;

    @FXML
    public void initialize() throws FileNotFoundException {
        productGridPane.getChildren().clear();

        VBox productView1 = productView(Product.TIRAMISU);
        productGridPane.add(productView1, 0,0);

        VBox productView2 = productView(Product.NAPOLEON);
        productGridPane.add(productView2, 1,0);

        VBox productView3 = productView(Product.CHEESECAKE);
        productGridPane.add(productView3, 2,0);
    }

    private VBox productView(Product product) throws FileNotFoundException {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        String fileName = "/Users/User/IdeaProjects/imtired/src/main/resources/com/example/imtired/tir.jpg";

        FileInputStream input = new FileInputStream(fileName);

        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Label productName = new Label(product.getDescription());
        Label price = new Label(product.getPrice()+"KZT");

        Button addButton = new Button("Add");
        addButton.setUserData(product.getDescription());
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Node sourceComponent = (Node)actionEvent.getSource();
                String productName = (String)sourceComponent.getUserData();
                ShoppingCart shoppingCart = ShoppingCart.getInstance();
                shoppingCart.addProduct(productName);
            }
        });

        layout.getChildren().addAll(imageView, productName, price, addButton);
        return layout;
    }
}
