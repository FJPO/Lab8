package Client.GuiClient;

import Client.I18n.Locales;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DiagramScene extends TabMaker{
    private static SimpleStringProperty answerProperty = new SimpleStringProperty();

    public static void setAnswerProperty(String text){answerProperty.set(text);}

        @Override
    protected Parent getLayout() {
        VBox lout = new VBox();
        lout.setSpacing(5);
        lout.setPadding(new Insets(10, 10, 10, 10));

        Label answerIfNeeded = new Label("");
        answerIfNeeded.setWrapText(true);
        answerIfNeeded.textProperty().bind(answerProperty);

        final ScatterChart<Number,Number> diagram = new ScatterChart<>(new NumberAxis("X", 0, 226, 20),
                new NumberAxis("Y", -0, 668, 60)
        );

        diagram.titleProperty().bind(Locales.createStringBinding("diagramName"));
        diagram.setAnimated(true);


        //HashMap<String, XYChart.Series> allSeries = NetInteractor.getSeries();



//        NetInteractor.getLabWorks().stream().forEach(l -> {
//            XYChart.Data tempoPoint = new XYChart.Data<>(l.getXCoordinate(), l.getYCoordinate());
//            Button button = new Button();
//            button.setOnAction(e->
//            {
//                try {
//                    answerIfNeeded.setText(NetInteractor.performCommand(CommandKeeper.getCommandFromScript("UPDATE", l.getId() + " "+ new AddingLabBox(l).display(), NetInteractor.getUser())));
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            });
//            button.setPrefSize(20, 20);
//            tempoPoint.setNode(button);
//            Tooltip.install(tempoPoint.getNode(), new Tooltip(l.toString()));
//            allSeries.get(l.getCreatorName()).getData().add(tempoPoint);
//        });


        //diagram.dataProperty().setValue(NetInteractor.getSeries());
        //diagram.setData(NetInteractor.getSeries());
        for (Object series: NetInteractor.getSeries().toArray()) diagram.getData().add((XYChart.Series)series);
        lout.getChildren().addAll(diagram, answerIfNeeded);


        return lout;
    }

    @Override
    public String getName() {
        return "DiagramScene";
    }

}
