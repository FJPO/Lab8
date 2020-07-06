package Common.CommandsM;

import Common.CommandsM.General.Executable;
import Common.Source.LabWork;

import java.util.Collections;
import java.util.List;

/**
 * Класс, осуществляющий перемещивание элементов в случайном порядке
 */
public class Shuffle implements Executable {
    @Override
    public String getName() {
        return "SHUFFLE";
    }

    @Override
    public void execute(List<LabWork> list) {
        Collections.shuffle(list);
    }

    @Override
    public void printAnswer() {
        System.out.println("Элементы коллекции перемешаны");
    }
    @Override
    public String getAnswer() {
        return "Элементы коллекции перемешаны";
    }
}
