package Client.GuiClient;

import Client.I18n.Locales;
import Common.Source.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Arrays;


public class AddingLabBox {
    String[] fields = null;
    String userName = null;

    public AddingLabBox(){}
    public AddingLabBox(LabWork lab){
        if(lab==null) return;
        fields = new String[13];
        int i = 1;
        fields[i++] = lab.getName();
        fields[i++] = String.valueOf(lab.getXCoordinate());
        fields[i++] = String.valueOf(lab.getYCoordinate());
        fields[i++] = String.valueOf(lab.getMinimalPoint());
        fields[i++] = String.valueOf(lab.getTunedInWorks());
        fields[i++] = String.valueOf(lab.getAveragePoint());
        fields[i++] = (lab.getDifficulty()==null)?"":lab.getDifficulty().name();
        fields[i++] = lab.getAuthorName();
        fields[i++] = String.valueOf(lab.getAuthorHeight());
        fields[i++] = String.valueOf(lab.getAuthorX());
        fields[i++] = String.valueOf(lab.getAuthorY());
        fields[i++] = String.valueOf(lab.getAuthorZ());

        userName = lab.getCreatorName();

    }

    public String display(){
        if(userName!=null)
            if(!userName.equals(NetInteractor.getUserName())) return "";
        String[] answer = new String[1];
        answer[0] = null;
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add lab");

        GridPane lout = new GridPane();
        lout.setHgap(10); lout.setVgap(15);
        lout.setPadding(new Insets(30, 10, 30, 10));

        Control[] texts = new Control[13];
        int i = 1;
        for(String tag: new String[]{"name", "Xcoordinate", "Ycoordinate",  "minimalPoint",  "turnedInWorks",  "averagePoint",
                 "difficulty",  "authorName", "authorHeight", "authorX", "authorY", "authorZ"}){
            Label label = new Label();
            label.textProperty().bind(Locales.createStringBinding(tag));

            if(tag.equals("difficulty")){
                ChoiceBox<Difficulty> choice = new ChoiceBox(FXCollections.observableList((Arrays.asList(Difficulty.values()))));
                choice.setValue(Difficulty.VERY_EASY);
                texts[i] = choice;

                if(fields!=null && fields[i]!="") choice.setValue(Difficulty.valueOf(fields[i]));
                lout.add(choice, 1, i);
            } else{
                TextField text = new TextField();
                if(fields!=null) text.setText(fields[i]);
                texts[i] = text;
                lout.add(text, 1, i);

            }
            Label helpText = new Label();
            helpText.textProperty().bind(Locales.createStringBinding(tag + "Help"));
            lout.add(helpText, 2, i);
            lout.add(label, 0, i++);
        }

        Label warning = new Label();
        lout.add(warning, 0, 0);

        Button confirm = new Button();
        confirm.textProperty().bind(Locales.createStringBinding("confirm"));
        confirm.setOnAction(e -> {

            int index = 1;
            try {
                LabWork nw = new LabWork(((TextField) texts[index++]).getText(),
                        new Coordinates(Float.parseFloat(((TextField) texts[index++]).getText()), Integer.parseInt(((TextField) texts[index++]).getText())),
                        Double.parseDouble(((TextField) texts[index++]).getText()),
                        Integer.parseInt(((TextField) texts[index++]).getText()),
                        (((TextField) texts[index]).getText().equals("")) ? null : Double.parseDouble(((TextField) texts[index++]).getText()),
                        (Difficulty) ((ChoiceBox) texts[index++]).getValue(),
                        new Person(((TextField) texts[index++]).getText(), Long.parseLong(((TextField) texts[index++]).getText()),
                                new Location(Float.parseFloat(((TextField) texts[index++]).getText()),
                                        Integer.parseInt(((TextField) texts[index++]).getText()),
                                        Long.parseLong(((TextField) texts[index++]).getText())
                                )), null);
                answer[0] = (nw.getName() +" "+ nw.getXCoordinate() +" "+ nw.getYCoordinate() +" "+ nw.getMinimalPoint() +" "+
                        nw.getTunedInWorks() +" "+ nw.getAveragePoint() +" "+ nw.getDifficulty().name() +" "+ nw.getAuthorName() +" "+
                        nw.getAuthorHeight() +" "+ nw.getAuthorX() +" "+ nw.getAuthorY() +" "+ nw.getAuthorZ());
                window.close();

            }catch(Exception ex){
                warning.textProperty().bind(Locales.createStringBinding("enterProblem"));
            }
        });
        lout.add(confirm, 1, i);

        window.setScene(new Scene(lout));
        window.showAndWait();
        return answer[0];
    }

}
