package Client.GuiClient;

import Client.I18n.Locales;
import Common.Security.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginBox {
    private boolean success = false;

    public boolean getSuccess() {
        return success;
    }

    public void display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Log in");
        window.setMinWidth(300);
        window.setMinHeight(270);

        GridPane lout = new GridPane();
        lout.setHgap(10); lout.setVgap(15);
        lout.setPadding(new Insets(30, 10, 30, 10));

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
        menu.getMenus().addAll(langMenu);
        lout.add(menu, 0, 0);



        ToggleGroup in_up = new ToggleGroup();
        ToggleButton in = new ToggleButton(), up = new ToggleButton();
        in.textProperty().bind(Locales.createStringBinding("signIn"));
        up.textProperty().bind(Locales.createStringBinding("signUp"));
        in.setSelected(true);
        in.setToggleGroup(in_up); up.setToggleGroup(in_up);
        lout.add(in, 0, 1); lout.add(up, 1, 1);

        Label logL = new Label(), pwdL = new Label(), message = new Label();
        logL.textProperty().bind(Locales.createStringBinding("login"));
        pwdL.textProperty().bind(Locales.createStringBinding("pwd"));
        lout.add(logL, 0, 2);
        lout.add(pwdL, 0, 3);
        lout.add(message, 1, 4);
        TextField log = new TextField(), pwd = new PasswordField();

        lout.add(log, 1, 2);
        lout.add(pwd, 1, 3);
        Button confirm = new Button();
        confirm.textProperty().bind(Locales.createStringBinding("confirm"));
        confirm.setOnAction(e -> {
            User user = new User(log.getText(), User.getPasswordHash(pwd.getText()));
            if (up.isSelected()) {
                user.setRegisterMode("signup");
            }
            if (in.isSelected()) {
                user.setRegisterMode("signin");
            }
            try {
                if(NetInteractor.authorize(user)){
                    success = true;
                    window.close();
                }
                else message.textProperty().bind(Locales.createStringBinding("wrongLogPwd"));

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        lout.add(confirm, 2, 4);
        window.setScene(new Scene(lout, 400, 200));

        window.showAndWait();
    }


}
