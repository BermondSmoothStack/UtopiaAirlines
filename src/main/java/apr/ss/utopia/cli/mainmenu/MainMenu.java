package apr.ss.utopia.cli.mainmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.cli.employeemenu.EmployeeMainMenu;

public class MainMenu implements Menu {

    public MainMenu() {
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
        MainMenuInputHandler ih = new MainMenuInputHandler();
        ih.handler();
        return ih.getVerifiedInput();
    }
}
