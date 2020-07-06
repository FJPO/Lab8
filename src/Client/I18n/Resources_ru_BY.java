package Client.I18n;

import java.util.ListResourceBundle;

public class Resources_ru_BY extends ListResourceBundle {
    public String[][] contents = {

            //общие
            {"Lang", "Мова"}, {"forTable", " Табліца:"}, {"LogOut", "Выйсці"},
            {"TableScene", "Таблічнае прадстаўленне"}, {"CommandScene", "Выконваць каманды"},
            {"DiagramScene", "Прадстаўленне дыяграмай"}, {"diagramName", "Дыяграма:"}, {"add", "Дадаць"},
            {"commandPrompt", "Увядзіце каманду"}, {"edit","Рэдагаваць"}, {"delete","Выдаліць"}, {"confirm", "Пацвердзіць"},
            {"login", "Лагін"}, {"pwd", "Пароль"}, {"signIn", "Уваход"}, {"signUp", "Рэгістрацыя"}, {"wrongLogPwd", " Няправільны лагін або пароль"},
            {"enterProblem", "Памылка пры ўводзе дадзеных"},

            //поля LabWork
            {"name", "Назва"}, {"coordinate","Каардынаты"}, {"Xcoordinate", "Каардыната X"}, {"Ycoordinate", "Каардыната Y"}, {"time", "Час стварэння"},
            {"minimalPoint", "Мінімальны бал"}, {"turnedInWorks", "Колькасць выдадзеных работ"}, {"averagePoint", "Сярэдні бал"},
            {"difficulty", "Складанасць"}, {"author", "Аўтар"}, {"authorName", "Iмя аўтара"}, {"authorHeight", "Рост аўтара"}, {"location", "Месцазнаходжанне"},
            {"authorX", "месцазнаходжанне аўтара, x"}, {"authorY", "месцазнаходжанне аўтара, Y"}, {"authorZ", " месцазнаходжанне аўтара, Z"},

            //Требования к полям
            {"nameHelp", "Не можа быць пустым"}, {"XcoordinateHelp", "Максімум - 226"}, {"YcoordinateHelp", "Максімум - 668"},
            {"minimalPointHelp", "Больш за нуль"}, {"turnedInWorksHelp", ""},
            {"averagePointHelp", "Больш за нуль"}, {"difficultyHelp", ""},
            { "authorNameHelp","Не можа быць пустым"}, {"authorHeightHelp", "Больш за нуль"}, {"authorXHelp", ""},
            { "authorYHelp", ""}, { "authorZHelp", ""},



    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
