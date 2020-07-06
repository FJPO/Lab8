package Client.GuiClient;

import Common.CommandsM.ExecuteScript;
import Common.CommandsM.General.CommandKeeper;
import Common.CommandsM.General.Executable;
import Common.CommandsM.General.ExecutableCommandMaker;
import Common.CommandsM.General.FileOpenedController;
import Common.Security.User;
import Common.Source.*;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class NetInteractor {
    private static volatile boolean established = false;
    private static InputStream input;
    private static OutputStream output;
    private static String address = "127.0.0.1";
    private static int port = 7788;

    private static User user = null;
    private volatile static Object waiting = null;
    private static ObservableList<LabWork> labWorks = FXCollections.observableList(new ArrayList<>());

    private static ObservableList<XYChart.Series<Number, Number>> allSeries = FXCollections.observableList(new ArrayList<>());

    //Нужны проверки
    private static Runnable receiver = () -> {
        try {
            for (;;) {
                Object received = receiveObject();
                if(received instanceof LabWork) {

                    LabWork tempo = (LabWork)received;
                    Object[] equal;

                    //Проверяем, есть ли цже такой элемент
                    if((equal = labWorks.stream().filter(l -> l.getId()==tempo.getId()).toArray()).length != 0){

                        //Если точно такой же есть, то удаляем его
                        if(tempo.equals(equal[0])){
                            Platform.runLater(()-> {
                                labWorks.remove(tempo);
                                XYChart.Series tempoSeries = (XYChart.Series)allSeries.stream().filter(series -> series.getName().equals(tempo.getCreatorName())).toArray()[0];
                                XYChart.Data tempoData = (XYChart.Data) tempoSeries.getData().stream().filter(data ->{
                                    XYChart.Data current = (XYChart.Data) data;
                                    return Float.parseFloat(String.valueOf(current.getXValue()))==((LabWork)equal[0]).getXCoordinate() &&
                                            Integer.parseInt(String.valueOf(current.getYValue()))==((LabWork)equal[0]).getYCoordinate();
                                }).toArray()[0];
                                tempoSeries.getData().remove(tempoData);
                            });

                            //если элемент был изменен, обновляем
                        }else{
                            Platform.runLater(()->{
                                int index = labWorks.indexOf(equal[0]);
                                labWorks.remove(index);
                                labWorks.add(index, tempo);
                                XYChart.Series tempoSeries = (XYChart.Series)allSeries.stream().filter(series -> series.getName().equals(tempo.getCreatorName())).toArray()[0];
                                XYChart.Data tempoData = (XYChart.Data) tempoSeries.getData().stream().filter(data ->{
                                    XYChart.Data current = (XYChart.Data) data;
                                    return Float.parseFloat(String.valueOf(current.getXValue()))==((LabWork)equal[0]).getXCoordinate() &&
                                            Integer.parseInt(String.valueOf(current.getYValue()))==((LabWork)equal[0]).getYCoordinate();
                                }).toArray()[0];

                                int index2 = tempoSeries.getData().indexOf(tempoData);
                                tempoSeries.getData().remove(index2);
                                XYChart.Data newData = new XYChart.Data<Number, Number>(tempo.getXCoordinate(), tempo.getYCoordinate());
                                Button button = new Button();
                                button.setOnAction(e ->
                                {
                                    try {
                                        DiagramScene.setAnswerProperty(NetInteractor.performCommand(
                                                CommandKeeper.getCommandFromScript("UPDATE", tempo.getId() + " " +
                                                        new AddingLabBox(tempo).display(), NetInteractor.getUser())));
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                });
                                button.setPrefSize(20, 20);
                                newData.setNode(button);
                                Tooltip.install(newData.getNode(), new Tooltip(tempo.toString()));
                                tempoSeries.getData().add(index2, newData);

                            });

                        }
                    //такого элемента не было в кол-и, добавляем его
                    } else {
                        labWorks.add(tempo);
                        Platform.runLater(()-> {
                            XYChart.Series series;

                            //создаем серию с новым пользователем

                            if (!(allSeries.stream().anyMatch(series1 -> series1.getName().equals(tempo.getCreatorName())))) {
                                series = new XYChart.Series();
                                series.setName(tempo.getCreatorName());
                                allSeries.add(series);

                                //находим серию такущего пользователя
                            } else {
                                series = (XYChart.Series) allSeries.stream().filter(s -> s.getName().equals(tempo.getCreatorName())).toArray()[0];
                            }

                            //Создаем точку для такущей лабы
                            XYChart.Data<Number, Number> tempoPoint = new XYChart.Data<>(tempo.getXCoordinate(), tempo.getYCoordinate());
                            Button button = new Button();
                            button.setOnAction(e ->
                            {
                                try {
                                    DiagramScene.setAnswerProperty(NetInteractor.performCommand(
                                            CommandKeeper.getCommandFromScript("UPDATE", tempo.getId() + " " +
                                                    new AddingLabBox(tempo).display(), NetInteractor.getUser())));
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                            button.setPrefSize(20, 20);
                            tempoPoint.setNode(button);
                            Tooltip.install(tempoPoint.getNode(), new Tooltip(tempo.toString()));
                            series.getData().add(tempoPoint);
                        });
                    }
                }
                else{
                    while(waiting != null);
                    waiting = received;
                }

            }
        }catch (IOException | ClassNotFoundException e){
            //e.printStackTrace();
        }
    };


    static {
        breakpoint:
        {
            Socket socket;
            try {
                int localPort = (int) (Math.random()*10000+5000);
                JSch jSch = new JSch();
                Session session = jSch.getSession("s285700", "se.ifmo.ru", 2222);
                session.setPassword("PASSWORD");
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();
                socket = new Socket(address,
                        session.setPortForwardingL(localPort, "localhost", port)
//                        port
                );
                input = socket.getInputStream();
                output = socket.getOutputStream();
                new Thread(receiver).start();
                established = true;
            } catch (
                    JSchException |
                            IOException e) {

                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {}
                break breakpoint;
            }
        }
    }

    public static void close(){
        try {
            input.close();
            output.close();
        }catch(IOException e){}
    }
    public static String getUserName(){return user.getLogin();}
    public static User getUser(){return user;}
    public static ObservableList<LabWork> getLabWorks(){return labWorks;}
    public static ObservableList<XYChart.Series<Number, Number>> getSeries(){return allSeries;}
    public static boolean isEstablished(){return established;}


    private static void sendObject(Object obj) throws IOException{
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objOutput = new ObjectOutputStream(byteStream);
        objOutput.writeObject(obj);
        output.write(byteStream.toByteArray());
    }
    private static Object receiveObject() throws IOException, ClassNotFoundException{
        ObjectInputStream objInput = new ObjectInputStream(input);
        return objInput.readObject();
    }
    protected static String performCommand(Executable command) throws IOException{
        if(command instanceof ExecutableCommandMaker) {
            StringBuilder answer = new StringBuilder();
            for (Executable tiny : ((ExecutableCommandMaker) command).getCommands()) answer.append(performCommand(tiny));
            FileOpenedController.removeFromOpened(((ExecuteScript) command).getFileReading().getName());
            return answer.toString();
        } else if(command == null) return "no such command";
        else{
            sendObject(command);
            while (waiting == null || !(waiting instanceof Executable));
            command = (Executable) waiting;
            waiting = null;
            return command.getAnswer();
        }
    }

    protected static boolean authorize(User user) throws IOException, ClassNotFoundException {
        sendObject(user);
        while (waiting == null || !(waiting instanceof Boolean));
        boolean answer = (Boolean)waiting;
        waiting = null;
        if(answer) NetInteractor.user = user;
        return answer;
    }
}
