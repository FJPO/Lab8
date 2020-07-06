package Common.CommandsM;

import Common.CommandsM.General.Executable;
import Common.Source.LabWork;

import java.util.List;

/**
 * Класс, осуществлящий вывод всех элементов коллекции
 */
public class Show implements Executable {

    private StringBuilder answer;

    @Override
    public String getName() {
        return "SHOW";
    }

    @Override
    public void execute(List<LabWork> list) {
        answer = new StringBuilder();

        answer.append("Коллекция представлена ниже:\n");
        for(int i = 0; i<list.size(); i++){
            answer.append("\nЭлемент № ").append(i + 1).append("\n").append(list.get(i).toString()).append("\n\n");
        }


        if(list.isEmpty())answer.append("Коолекция пуста\n");
    }

    @Override
    public void printAnswer() {
        System.out.println(answer);
    }
    @Override
    public String getAnswer() {
        return answer.toString();
    }
}
