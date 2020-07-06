package Client.GuiClient;

import Common.CommandsM.General.CommandKeeper;
import Client.I18n.Locales;
import Common.Source.*;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDateTime;

public class TableScene extends TabMaker{

    @Override
    protected Parent getLayout() {
        VBox lout = new VBox();
        lout.setSpacing(5);
        lout.setPadding(new Insets(10, 10, 10, 10));

        Label answerIfNeeded = new Label("");
        answerIfNeeded.setWrapText(true);

        TableView<LabWork> tableView = new TableView<>();
        TableColumn<LabWork, Integer> id = new TableColumn("Id");
        TableColumn<LabWork, String> name = new TableColumn();

        TableColumn<LabWork, Coordinates> coordinates = new TableColumn<>();
        TableColumn<LabWork, Float> xCoordinfte = new TableColumn<>("X");
        TableColumn<LabWork, Integer> yCoordinate = new TableColumn<>("Y");
        coordinates.getColumns().addAll(xCoordinfte, yCoordinate);

        TableColumn<LabWork, LocalDateTime> time = new TableColumn<>();
        TableColumn<LabWork, String>difficulty = new TableColumn();
        TableColumn<LabWork, Double>minimalPoint = new TableColumn();
        TableColumn<LabWork, Integer>tunedInWorks = new TableColumn<>();
        TableColumn<LabWork, Double>averagePoint = new TableColumn<>();

        TableColumn<LabWork, Person>author = new TableColumn<>();
        TableColumn<LabWork, String>authorName = new TableColumn<>();
        TableColumn<LabWork, Long>authorHeight = new TableColumn<>();
        TableColumn<LabWork, Location> authorLocation = new TableColumn<>();
        TableColumn<LabWork, Float> authorX = new TableColumn<>("X");
        TableColumn<LabWork, Integer> authorY = new TableColumn<>("Y");
        TableColumn<LabWork, Long> authorZ = new TableColumn<>("Z");
        authorLocation.getColumns().addAll(authorX, authorY, authorZ);
        author.getColumns().addAll(authorName, authorHeight, authorLocation);

        TableColumn<LabWork, String>creator = new TableColumn<>("Creator");

//        "name", "Xcoordinate", "Ycoordinate",  "minimalPoint",  "turnedInWorks",  "averagePoint",
//                "difficulty",  "authorName", "authorHeight", "authorX", "authorY", "authorZ"


        name.textProperty().bind(Locales.createStringBinding("name"));
        coordinates.textProperty().bind(Locales.createStringBinding("coordinate"));
        time.textProperty().bind(Locales.createStringBinding("time"));
        minimalPoint.textProperty().bind(Locales.createStringBinding("minimalPoint"));
        tunedInWorks.textProperty().bind(Locales.createStringBinding("turnedInWorks"));
        averagePoint.textProperty().bind(Locales.createStringBinding("averagePoint"));
        difficulty.textProperty().bind(Locales.createStringBinding("difficulty"));

        author.textProperty().bind(Locales.createStringBinding("author"));
        authorName.textProperty().bind(Locales.createStringBinding("authorName"));
        authorHeight.textProperty().bind(Locales.createStringBinding("authorHeight"));
        authorLocation.textProperty().bind(Locales.createStringBinding("location"));


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        xCoordinfte.setCellValueFactory(new PropertyValueFactory<>("xCoordinate"));
        yCoordinate.setCellValueFactory(new PropertyValueFactory<>("yCoordinate"));
        difficulty.setCellValueFactory(new PropertyValueFactory<>("stringDifficulty"));
        time.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        minimalPoint.setCellValueFactory(new PropertyValueFactory<>("minimalPoint"));
        tunedInWorks.setCellValueFactory(new PropertyValueFactory<>("tunedInWorks"));
        averagePoint.setCellValueFactory(new PropertyValueFactory<>("averagePoint"));
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        authorHeight.setCellValueFactory(new PropertyValueFactory<>("authorHeight"));
        authorX.setCellValueFactory(new PropertyValueFactory<>("authorX"));
        authorY.setCellValueFactory(new PropertyValueFactory<>("authorY"));
        authorZ.setCellValueFactory(new PropertyValueFactory<>("authorZ"));
        creator.setCellValueFactory(new PropertyValueFactory<>("creatorName"));

        tableView.getColumns().addAll(id, name, coordinates, time, minimalPoint, tunedInWorks, averagePoint, difficulty, author, creator);

        tableView.itemsProperty().setValue(NetInteractor.getLabWorks());
        Label text = new Label();
        text.textProperty().bind(Locales.createStringBinding("forTable"));

        Button add = new Button();
        add.textProperty().bind(Locales.createStringBinding("add"));
        add.setOnAction(e->
        {
            try {
                //answerIfNeeded.setText(
                        NetInteractor.performCommand(CommandKeeper.getCommandFromScript("ADD", new AddingLabBox().display(), NetInteractor.getUser()))
                //)
                ;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button edit = new Button();
        edit.textProperty().bind(Locales.createStringBinding("edit"));
        edit.setOnAction(e ->{
            try {
                if(tableView.getSelectionModel().getSelectedItem() != null)
                    //answerIfNeeded.setText(
                            NetInteractor.performCommand(CommandKeeper.getCommandFromScript("UPDATE",
                        tableView.getSelectionModel().getSelectedItem().getId() +" "+
                                new AddingLabBox(tableView.getSelectionModel().getSelectedItem()).display(),
                            NetInteractor.getUser()))
                    //)
                    ;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button delete = new Button();
        delete.textProperty().bind(Locales.createStringBinding("delete"));
        delete.setOnAction(e->{
            try{
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    //answerIfNeeded.setText(
                            NetInteractor.performCommand(CommandKeeper.getCommandFromScript(
                            "REMOVE_BY_ID", String.valueOf(tableView.getSelectionModel().getSelectedItem().getId()), NetInteractor.getUser()))
                    //)
                    ;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });



        lout.getChildren().addAll(text, tableView, add, edit, delete, answerIfNeeded);
        return lout;

    }

    @Override
    public String getName() {
        return "TableScene";
    }
}
