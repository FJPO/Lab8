package Client.GuiClient;

import Common.CommandsM.General.CommandKeeper;
import Client.I18n.Locales;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class CommandScene extends TabMaker {
    @Override
    protected Parent getLayout() {
        GridPane lout = new GridPane();
        lout.setHgap(10); lout.setVgap(15);
        lout.setPadding(new Insets(30, 10, 30, 10));
        Label label = new Label();
        label.textProperty().bind(Locales.createStringBinding("commandPrompt"));
        lout.add(label, 0, 0);

        ChoiceBox<String> commandName = new ChoiceBox(FXCollections.observableList(new ArrayList<>(CommandKeeper.getAllCommandsNames())));
        lout.add(commandName, 0, 1);

        TextField arg = new TextField();
        lout.add(arg, 1, 1);

        TextArea answer = new TextArea();
        answer.setWrapText(true);
        answer.setEditable(false);

        lout.add(answer, 0, 2, 3, 2);

        Button confirm = new Button();
        confirm.textProperty().bind(Locales.createStringBinding("confirm"));
        confirm.setOnAction(e->{
            try {
                String arguments;
                if((commandName.getValue().equals("ADD")) || (commandName.getValue().equals("ADD_IF_MAX")) || (commandName.getValue().equals("UPDATE"))) {
                    arguments = new AddingLabBox().display();
                    if(commandName.getValue().equals("UPDATE")) arguments = arg.getText() + " " + arguments;
                }
                else arguments = arg.getText();
                if(arguments!=null) {
                    answer.setText(NetInteractor.performCommand(CommandKeeper.getCommandFromScript(commandName.getValue(), arguments, NetInteractor.getUser())));
                    arg.clear();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        lout.add(confirm, 2, 1);

        return lout;
    }

    @Override
    public String getName() {
        return "CommandScene";
    }
}
