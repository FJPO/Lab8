package Common.CommandsM;

import Common.CommandsM.General.Executable;

import Common.Source.LabWork;

import java.util.List;

/**
 * Класс, осуществляющий завршение работы клиента
 */
public class Exit implements Executable {
    @Override
    public String getName() {
        return "EXIT";
    }

    @Override
    public void execute(List<LabWork> list) {
        System.out.println("Клиент отключился");
    }

    @Override
    public void printAnswer() {
        System.out.println("Команда выполняется");
        System.exit(0);
    }
    @Override
    public String getAnswer() {
        System.exit(0);
        return "";
    }
}
