package Client.I18n;

import java.util.ListResourceBundle;

public class Resources_es_GT extends ListResourceBundle {
    public String[][] contents = {


            //общие
            {"Lang", "Idioma"}, {"forTable", "Tabla:"}, {"LogOut", "Salir"},
            {"TableScene", "Representación tabular"}, {"CommandScene", "Ejecutar comandos"},
            {"DiagramScene", "Representación gráfica"}, {"diagramName", "Gráfica:"}, {"add", "Agregar"},
            {"commandPrompt", "Escriba el comando"}, {"edit", "Corregir"}, {"delete", "Eliminar"}, {"confirm","Confirmar"},
            {"login", "Inicio de sesión"}, {"pwd", "Contraseña"}, {"signIn", "Entrada"}, {"signUp", "Registro"}, {"wrongLogPwd", "Nombre de usuario o contraseña incorrectos"},
            {"enterProblem", "Error al introducir datos"},

            //поля LabWork
            {"name", "Nombre"}, {"coordinate", "Coordenadas"}, {"Xcoordinate", "Coordinar X"}, {"Ycoordinate", "Coordinar Y"}, {"time", "Tiempo de creación"},
            {"minimalPoint", "Puntaje mínimo"},  {"turnedInWorks", "El número de obras emitidas."}, { "averagePoint", "Promedio de calificaciones"},
            {"difficulty", "Complejidad"}, {"author", "Autor"}, { "authorName", "Nombre del autor"}, { "authorHeight", "Crecimiento del autor"}, {"location", "Ubicación"},
            {"authorX", "Ubicación del autor, X"}, { "authorY", "Ubicación del autor, Y"},  { "authorZ", "Ubicación del autor, Z"},

            //Требования к полям
            {"nameHelp", "No puede estar vacío"}, {"XcoordinateHelp", "Máximo - 226"}, {"YcoordinateHelp", "Máximo - 668"},
            {"minimalPointHelp", "Por encima de cero"},  {"turnedInWorksHelp", ""},
            { "averagePointHelp", "Por encima de cero"}, {"difficultyHelp", ""},
            { "authorNameHelp", "No puede estar vacío"}, { "authorHeightHelp", "Por encima de cero"}, { "authorXHelp", ""},
            { "authorYHelp", ""},  { "authorZHelp", ""},


    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }

}
