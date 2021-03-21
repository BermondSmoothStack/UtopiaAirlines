package apr.ss.utopia.cli.adminmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.inputhandler.IntInputHandler;

public class AdminMainMenu implements Menu<Integer> {

    public AdminMainMenu() {
        while(true){
            display();
            switch(getMenuSelection()){
                case 1:
                    new AdminFlightMenu();
                    break;
                case 2:
                    new AdminSeatMenu();
                    break;
                case 3:
                    new AdminPassengerMenu();
                    break;
                case 4:
                    new AdminAirportMenu();
                    break;
                case 5:
                    new AdminTravelersMenu();
                    break;
                case 6:
                    new AdminEmployeesMenu();
                    break;
                case 7:
                    new AdminOverrideCancellation();
                    break;
                case 8:
                    return;
                default:
            }
        }
    }

    @Override
    public void display() {
        System.out.println("Administrator Menu\n" +
                "[1] Manage Flights\n" +
                "[2] Manage Seats\n" +
                "[3] Manage Tickets and Passengers\n" +
                "[4] Manage Airports\n" +
                "[5] Manage Travelers\n" +
                "[6] Manage Employees\n" +
                "[7] Over-ride Trip Cancellation for a ticket.");
    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1, 7);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
