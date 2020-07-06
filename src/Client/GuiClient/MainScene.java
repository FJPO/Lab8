package Client.GuiClient;

import Client.I18n.Locales;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainScene {

    public Scene getScene(){
        return new Scene(this.getLayout());
    }

    protected BorderPane getLayout(){
        BorderPane lout = new BorderPane();
        lout.setPadding(new Insets(30, 10, 30, 10));

        //Создание меню
        MenuBar menu = new MenuBar();

        Menu langMenu = new Menu();
        langMenu.textProperty().bind(Locales.createStringBinding("Lang"));
        RadioMenuItem[] langOptions = new RadioMenuItem[Locales.values().length];
        ToggleGroup langToggle = new ToggleGroup();

        for (int i = 0; i<Locales.values().length; i++){
            Locales tempo = Locales.values()[i];
            langOptions[i] = new RadioMenuItem(tempo.toString());
            langOptions[i].setOnAction(e -> {
                Locales.setLocale(tempo);
            });
            if(tempo==Locales.currentLocale())langOptions[i].setSelected(true);
            langMenu.getItems().add(langOptions[i]);
            langOptions[i].setToggleGroup(langToggle);
        }


        Menu userMenu = new Menu();
        userMenu.setText(NetInteractor.getUserName());
        MenuItem logOut = new MenuItem();
        logOut.textProperty().bind(Locales.createStringBinding("LogOut"));
        logOut.setOnAction(e->{
            System.exit(0);
        });
        userMenu.getItems().add(logOut);

        menu.getMenus().addAll(langMenu, userMenu);
        lout.setTop(menu);

        VBox leftMenu = new VBox();
        leftMenu.setPrefSize(250, 70);
        leftMenu.setPadding(new Insets(0, 10, 10, 10));
        leftMenu.setSpacing(5);

        ToggleGroup tabs = new ToggleGroup();
        ToggleButton[] toggleButtons = new ToggleButton[3];
        for(int i = 0; i<toggleButtons.length; i++){
            TabMaker currentTab = TabMaker.getMakers()[i];
            ToggleButton currentButton = new ToggleButton();
            currentButton.textProperty().bind(Locales.createStringBinding(currentTab.getName()));
            currentButton.setPrefSize(leftMenu.getPrefWidth(), leftMenu.getPrefHeight());
            currentButton.setOnAction(e -> {
                lout.setCenter(currentTab.getLayout());
                currentButton.setSelected(true);
            });
            leftMenu.getChildren().add(currentButton);
            currentButton.setToggleGroup(tabs);
            toggleButtons[i] = currentButton;
        }

        lout.setLeft(leftMenu);
        BorderPane.setAlignment(leftMenu, Pos.CENTER_LEFT);

        return lout;
    }
}
