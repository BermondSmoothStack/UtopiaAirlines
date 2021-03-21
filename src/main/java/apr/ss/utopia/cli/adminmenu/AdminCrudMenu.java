package apr.ss.utopia.cli.adminmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.lang.reflect.InvocationTargetException;

public class AdminCrudMenu implements Menu<Integer> {

    public AdminCrudMenu(Class<AbsCRUD>[] c) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        while (true) {
            display();
            switch (getMenuSelection()) {
                case 1:
                    c[0].getConstructor().newInstance();
                    break;
                case 2:
                    c[1].getConstructor().newInstance();
                    break;
                case 3:
                    c[2].getConstructor().newInstance();
                    break;
                case 4:
                    c[3].getConstructor().newInstance();
                    break;
                case 5:
                    return;
            }
        }
    }

    @Override
    public void display() {

        System.out.println("Menu. Please enter a procedure.\n" +
                "[1] Create\n" +
                "[2] View\n" +
                "[3] Update\n" +
                "[4] Delete\n" +
                "[5] Back to previous menu"
        );

    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1, 5);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
