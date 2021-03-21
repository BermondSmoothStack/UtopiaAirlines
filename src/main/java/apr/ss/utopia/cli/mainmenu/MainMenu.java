package apr.ss.utopia.cli.mainmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.cli.employeemenu.EmployeeMainMenu;
import apr.ss.utopia.cli.travelermenu.TravelerMainMenu;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.io.IOException;

public class MainMenu implements Menu<Integer> {

    public MainMenu() throws IOException {
        while(true){
            display();
            switch(getMenuSelection()){
                case 1:
                    new EmployeeMainMenu();
                    // TODO: Employee path
                    break;
                case 2:

                    // TODO: Administrator path
                    break;
                case 3:
                    new TravelerMainMenu();
                    // TODO: Traveler path
                    break;
                default:
                    System.out.println("Input invalid, try again.");
                    break;
            }
        }

    }

    public void display() {
        System.out.println("Welcome to the Utopia Airlines Management System. Which category of a user are you");
        System.out.println("[1] Employee");
        System.out.println("[2] Administrator");
        System.out.println("[3] Traveler");
    }

    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1,3);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
