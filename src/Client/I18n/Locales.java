package Client.I18n;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Locales {
    RUSSIAN("ru", "RU", "Русский"),
    BELO_RUSSIAN("ru", "BY", "Беларускі"),
    ALBANIAN("sq", "AL", "Shqiptar"),
    G_SPANISH("es", "GT", "Español");

    private final String name;
    private final Locale lcl;
    private final ResourceBundle resource;

    private static ObjectProperty<Locales> current = new SimpleObjectProperty<>();

    static {current.setValue(Locales.RUSSIAN);}


    public static void setLocale(Locales next){
        current.setValue(next);
    }

    public static Locales currentLocale(){return current.getValue();}

    public static StringBinding createStringBinding(String key) {
        return Bindings.createStringBinding(() -> current.getValue().resource.getString(key), current);
    }


    Locales(String l, String c, String name){
        lcl = new Locale(l, c);
        resource = ResourceBundle.getBundle("Client.I18n.Resources", lcl);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
