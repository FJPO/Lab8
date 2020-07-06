package Common.CommandsM;

import Common.CommandsM.General.ExecutableWithInput;
import Common.CommandsM.General.ExecutableWithRightsNeeded;

import Common.Security.User;
import Common.Source.LabWork;
import Server.DataBaseHandler;
import Server.UpdateSender;

import java.util.List;
import java.util.Scanner;

/**
 * Класс, осуществлящий удаление элемента по его индексу
 */

public class RemoveAtIndex implements ExecutableWithInput, ExecutableWithRightsNeeded {

    private int index = -1;
    private String answer;
    private User user;


    @Override
    public String getName() {
        return "REMOVE_AT";
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void execute(List<LabWork> list) {
        if(!DataBaseHandler.checkLoginAccuracy(user)){
            answer = "Неверный логин и пароль";
            return;
        }
        try {
            if(!list.get(index-1).getCreator().equals(user)){
                answer = "Недостаточно прав для удаления этого элемента";
                return;
            }
            DataBaseHandler.removeLabWork(list.get(index-1));
            UpdateSender.update((list.get(index-1)));
            list.remove(index-1);
            answer = String.format("Элемент под индексом %d удален\n", index);
        }catch (Exception e){
            answer = "Элемент с таким индексом не найден";
        }
    }

    @Override
    public void printAnswer() {
        System.out.println(answer);
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public void collectData(String arg) {
        recordData(arg);
        while(!isDataCorrect()){
            System.out.println("Введите индекс(считая с 1):");
            collectData(new Scanner(System.in).nextLine());
        }
    }

    @Override
    public void recordData(String arg) {
        try{
            index = Integer.parseInt(arg);
        }catch(Exception ignored){}
    }

    @Override
    public boolean isDataCorrect() {
        return index > 0;
    }
}
