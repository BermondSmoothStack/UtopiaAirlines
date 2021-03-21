package apr.ss.utopia.cli.employeemenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.cli.flightmenu.FlightMenu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.io.IOException;

public class EmployeeMainMenu implements Menu<Integer> {

    public  EmployeeMainMenu() throws IOException {
        while (true) {
            display();
            switch (getMenuSelection()) {
                case 1:
                    // TODO: Employee Flight path
                    Flight f = new Flight();
                    new FlightMenu(f);
                    break;
                case 2:
                    return;
                default:
                    break;
            }
        }
    }

    @Override
    public void display() {
        System.out.println("Employee Menu");
        System.out.println("[1] Enter Flights You Manage");
        System.out.println("[2] Quit to previous");
    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1,2);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
