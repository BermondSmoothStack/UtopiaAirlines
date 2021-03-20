package apr.ss.utopia.cli.employeemenu;

import apr.ss.utopia.cli.Menu;

public class EmployeeMainMenu implements Menu {

    public EmployeeMainMenu() {
        while (true) {
            display();
            switch (getMenuSelection()) {
                case 1:
                    // TODO: Employee Flight path
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
        EmployeeMainInputHandler ih = new EmployeeMainInputHandler();
        ih.handler();
        return ih.getVerifiedInput();
    }
}
