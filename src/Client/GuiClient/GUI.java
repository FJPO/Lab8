package Client.GuiClient;

import javafx.application.Application;
import javafx.stage.Stage;


public class GUI extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setOnCloseRequest(e->NetInteractor.close());
        while (!NetInteractor.isEstablished());
        LoginBox login = new LoginBox();
        login.display();
        if(!login.getSuccess()) System.exit(0);
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(1200);
        primaryStage.setScene(new MainScene().getScene());

        primaryStage.show();

    }

    public static void close(){
        stage.close();
    }

}