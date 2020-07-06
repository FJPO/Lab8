package Common.CommandsM;

import Common.CommandsM.General.Executable;
import Common.CommandsM.General.ExecutableWithRightsNeeded;
import Common.Security.User;
import Common.Source.LabWork;
import Server.DataBaseHandler;
import Server.UpdateSender;

import java.util.List;

/**
 * Класс, осуществляющий очищение коллекции
 */
public class Clear implements Executable, ExecutableWithRightsNeeded {
    private User user;
    private String answer;

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return "CLEAR";
    }

    @Override
    public void execute(List<LabWork> list) {
        if(!DataBaseHandler.checkLoginAccuracy(user)){
            answer = "Неверный логин и пароль";
            return;
        }
        for(int i = list.size()-1; i>=0; i--){
            if(list.get(i).getCreator().equals(user)){
                DataBaseHandler.removeLabWork(list.get(i));
                UpdateSender.update(list.get(i));
                list.remove(i);
            }
        }
        answer = "Ваши элементы удалены из данной коллекции";

    }

    @Override
    public void printAnswer() {
        System.out.println(answer);
    }
    @Override
    public String getAnswer() {
        return answer;
    }
}
