package com.example.imtired;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class CartController {
    @FXML
    private VBox cartPane;
    private Label totalPriceLabel;



    @FXML
    public void initialize() throws FileNotFoundException {
        List<CartEntry> entries = ShoppingCart.getInstance().getEntries();
        cartPane.getChildren().clear();

        if(entries.isEmpty()){
            Label emptyLabel = new Label("Empty cart");
            emptyLabel.setStyle("-fx-alignment: TOP-CENTER; -fx-font-size: 25; -fx-text-fill:  #9E6240; -fx-font-weight: 800; -fx-padding: 15");
            cartPane.getChildren().add(emptyLabel);
        }else {
            Label shoppingCartTitle = new Label("Shopping cart");
            shoppingCartTitle.setStyle("-fx-alignment: TOP-CENTER; -fx-font-size: 25; -fx-text-fill:  #9E6240; -fx-font-weight: 800; -fx-padding: 15");
            cartPane.getChildren().add(shoppingCartTitle);

            for(CartEntry cartEntry: entries){
                HBox productView = cartEntryView(cartEntry);
                cartPane.getChildren().add(productView);
            }

            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            cartPane.getChildren().add(separator);

            HBox totalView = totalView(ShoppingCart.getInstance().calculateCost());
            cartPane.getChildren().add(totalView);
        }

    }

    private HBox totalView(int totalPrice){
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);

        Label totalLabel = new Label("Total : ");
        totalLabel.setStyle("-fx-font-size: 20; -fx-text-fill:  #9E6240; -fx-font-weight: 800;");


        this.totalPriceLabel = new Label(String.valueOf(totalPrice));
        totalPriceLabel.setStyle(" -fx-font-size: 20; -fx-text-fill:  #9E6240; -fx-font-weight: 700;");

        layout.getChildren().addAll(totalLabel, this.totalPriceLabel);
        return layout;
    }


    private HBox cartEntryView(CartEntry cartEntry) throws FileNotFoundException {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(6, 0, 6, 0));

        FileInputStream input = new FileInputStream(
                "/Users/User/IdeaProjects/imtired/src/main/resources/com/example/imtired/tir.jpg");

        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Label productName = new Label(cartEntry.getProduct().getDescription());
        productName.setPrefWidth(100);
        productName.setStyle("-fx-font-size:20; -fx-padding: 5px; -fx-text-fill: #f8f2dc; -fx-font-weight: 800; -fx-pref-width: 150");


        Label quantity = new Label(String.valueOf(cartEntry.getQuantity()));
        quantity.setStyle("-fx-font-size: 20; -fx-text-fill: #9E6240; -fx-font-weight: 800");
        quantity.setPadding(new Insets(7, 10, 7, 10));

        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-background-color:  #CD4631; -fx-text-fill: #f8f2dc; -fx-font-size:15; -fx-font-weight: 700");
        plusButton.setPadding(new Insets(7, 10, 7, 10));
        plusButton.setUserData(cartEntry.getProduct().getDescription());
        plusButton.setOnAction( e -> {
            String name = (String) ((Node)e.getSource()).getUserData();
            ShoppingCart.getInstance().addProduct(name);
            quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(name)));
            this.totalPriceLabel.setText(String.valueOf(ShoppingCart.getInstance().calculateCost()));
        });

        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-background-color:  #CD4631; -fx-text-fill: #f8f2dc; -fx-font-size:15; -fx-font-weight: 700");
        minusButton.setPadding(new Insets(7, 10, 7, 10));
        minusButton.setUserData(cartEntry.getProduct().getDescription());
        minusButton.setOnAction( e -> {
            String name = (String) ((Node)e.getSource()).getUserData();
            ShoppingCart.getInstance().removeProduct(name);
            quantity.setText(ShoppingCart.getInstance().getQuantity(name));
            this.totalPriceLabel.setText(String.valueOf(ShoppingCart.getInstance().calculateCost()));
        });

        Label price = new Label(String.valueOf("KZT" + cartEntry.getProduct().getPrice()));
        price.setStyle("-fx-font-size:15; -fx-text-fill: #9E6240; -fx-font-weight: 700; -fx-padding: 5");


        layout.getChildren().addAll(imageView, productName, plusButton, quantity, minusButton, price);

        return layout;
    }
}
