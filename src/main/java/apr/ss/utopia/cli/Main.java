package apr.ss.utopia.cli;

import apr.ss.utopia.cli.mainmenu.MainMenu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        new MainMenu();
    }

}
