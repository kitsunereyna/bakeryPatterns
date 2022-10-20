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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HomeController {
    @FXML
    private GridPane productGridPane;

    @FXML
    public void initialize() throws FileNotFoundException {
        productGridPane.getChildren().clear();
        productGridPane.setHgap(25);
        productGridPane.setVgap(10);
        productGridPane.setPadding(new Insets(15, 15, 15, 15));

        //Label homeTitle = new Label("SWEET BAKERY");
        //homeTitle.setStyle("-fx-alignment: TOP-CENTER; -fx-font-size: 25;");
        //productGridPane.getChildren().add(homeTitle);

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

        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        Label productName = new Label(product.getDescription());
        productName.setStyle("-fx-font-size:20; -fx-text-fill: #f8f2dc; -fx-font-weight: 800;");
        Label price = new Label(product.getPrice()+"KZT");
        price.setStyle("-fx-font-size:15; -fx-text-fill: #9E6240; -fx-font-weight: 700;");

        Button addButton = new Button("Add");

        addButton.setStyle("-fx-background-color:  #CD4631; -fx-text-fill: #f8f2dc; -fx-font-size:15; -fx-font-weight: 800");
        addButton.setPadding(new Insets(3, 18, 3, 18));

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
