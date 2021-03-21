package apr.ss.utopia.cli.adminmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.cli.adminmenu.create.AirportCreate;
import apr.ss.utopia.cli.adminmenu.delete.AirportDelete;
import apr.ss.utopia.cli.adminmenu.read.AirportView;
import apr.ss.utopia.cli.adminmenu.update.AirportUpdate;
import apr.ss.utopia.inputhandler.IntInputHandler;

public class AdminAirportMenu implements Menu<Integer> {

    public AdminAirportMenu() {

        while (true) {
            display();
            switch (getMenuSelection()) {
                case 1:
                    new AirportCreate();
                    break;
                case 2:
                    new AirportView();
                    break;
                case 3:
                    new AirportUpdate();
                    break;
                case 4:
                    new AirportDelete();
                    break;
                case 5:
                    return;
            }
        }
    }

    @Override
    public void display() {

        System.out.println("Airport Menu. Please enter a procedure.\n" +
                "[1] Create" +
                "[2] View" +
                "[3] Update" +
                "[4] Delete" +
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
