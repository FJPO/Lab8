package Client.I18n;

import java.util.ListResourceBundle;

public class Resources_ru_RU extends ListResourceBundle {
    public String[][] contents = {


            //общие
            {"Lang", "Язык"}, {"forTable", "Таблица:"}, {"LogOut", "Выйти"},
            {"TableScene", "Табличное представление"}, {"CommandScene", "Выполнять команды"},
            {"DiagramScene", "Представление диаграммой"}, {"diagramName", "Диаграмма:"}, {"add", "Добавить"},
            {"commandPrompt", "Введите команду"}, {"edit", "Редактировать"}, {"delete", "Удалить"}, {"confirm","Подтвердить"},
            {"login", "Логин"}, {"pwd", "Пароль"}, {"signIn", "Вход"}, {"signUp", "Регистрация"}, {"wrongLogPwd", "Неверный логин или пароль"},
            {"enterProblem", "Ошибка при вводе данных"},

            //поля LabWork
            {"name", "Название"}, {"coordinate", "Координаты"}, {"Xcoordinate", "Координата X"}, {"Ycoordinate", "Координата Y"}, {"time", "Время создания"},
            {"minimalPoint", "Минимальный балл"},  {"turnedInWorks", "Количество выданных работ"}, { "averagePoint", "Средний балл"},
            {"difficulty", "Сложность"}, {"author", "Автор"}, { "authorName", "Имя автора"}, { "authorHeight", "Рост автора"}, {"location", "Местоположение"},
            {"authorX", "Местоположение автора, X"}, { "authorY", "Местоположение автора, Y"},  { "authorZ", "Местоположение автора, Z"},

            //Требования к полям
            {"nameHelp", "Не может быть пустым"}, {"XcoordinateHelp", "Максимум - 226"}, {"YcoordinateHelp", "Максимум - 668"},
            {"minimalPointHelp", "Больше нуля"},  {"turnedInWorksHelp", ""},
            { "averagePointHelp", "Больше нуля"}, {"difficultyHelp", ""},
            { "authorNameHelp", "Не может быть пустым"}, { "authorHeightHelp", "Больше нуля"}, { "authorXHelp", ""},
            { "authorYHelp", ""},  { "authorZHelp", ""},

    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }

}
