package Client.I18n;

import java.util.ListResourceBundle;

public class Resources_sq_AL extends ListResourceBundle {
    public String[][] contents = {
            //общие
            {"Lang", "Gjuha"}, {"forTable", "Tabela:"}, {"LogOut", "Dil jashtë"},
            {"TableScene", "Paraqitja e tabelës"}, {"CommandScene", "Komanda e ekzekutuar"},
            {"DiagramScene", "Diagramë përfaqësimi"}, {"diagramName", "Diagramë:"}, {"add", "Shto"},
            {"commandPrompt", "Shkruaj komandën"}, {"edit", "Ndrysho"}, {"delete", "Hiq"}, {"confirm","Konfermo"},
            {"login", "Futu"}, {"pwd", "Fjalëkalimi"}, {"signIn", "Hyrje"}, {"signUp", "Regjistrimi"}, {"wrongLogPwd", "Përdorues apo fjalëkalim i pavlefshëm"},
            {"enterProblem", "Gabim gjatë hyrjes së të dhënave"},

            //поля LabWork
            {"name", "emër"}, {"coordinate", "Koordinon"}, {"Xcoordinate", "Koordinativ X"}, {"Ycoordinate", "Koordinativ Y"}, {"time", "Koha e krijimit"},
            {"minimalPoint", "Nota minimale"},  {"turnedInWorks", "Numri i punimeve të lëshuara"}, { "averagePoint", "Mesatarja e notave"},
            {"difficulty", "Kompleksitet"}, {"author", "autor"}, { "authorName", "Emri i autorit"}, { "authorHeight", "Rritja e autorit"}, {"location", "vend"},
            {"authorX", "Vendndodhja e autorit, X"}, { "authorY", "Vendndodhja e autorit, Y"},  { "authorZ", "Vendndodhja e autorit, Z"},


            //Требования к полям
            {"nameHelp", "Nuk mund të jetë bosh"}, {"XcoordinateHelp", "maksimal - 226"}, {"YcoordinateHelp", "maksimal - 668"},
            {"minimalPointHelp", "Mbi zero"},  {"turnedInWorksHelp", ""},
            { "averagePointHelp", "Mbi zero"}, {"difficultyHelp", ""},
            { "authorNameHelp", "Nuk mund të jetë bosh"}, { "authorHeightHelp", "Mbi zero"}, { "authorXHelp", ""},
            { "authorYHelp", ""},  { "authorZHelp", ""},


    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }

}
