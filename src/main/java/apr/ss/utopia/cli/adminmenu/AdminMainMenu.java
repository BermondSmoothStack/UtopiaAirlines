package apr.ss.utopia.cli.adminmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.cli.adminmenu.create.AirportCreate;
import apr.ss.utopia.cli.adminmenu.create.TicketsCreate;
import apr.ss.utopia.cli.adminmenu.delete.AirportDelete;
import apr.ss.utopia.cli.adminmenu.delete.TicketsDelete;
import apr.ss.utopia.cli.adminmenu.read.AirportView;
import apr.ss.utopia.cli.adminmenu.read.TicketsView;
import apr.ss.utopia.cli.adminmenu.update.AirportUpdate;
import apr.ss.utopia.cli.adminmenu.update.TicketsUpdate;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.lang.reflect.InvocationTargetException;

public class AdminMainMenu implements Menu<Integer> {

    public AdminMainMenu() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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
                    new AdminCrudMenu(new Class[]{TicketsCreate.class, TicketsView.class, TicketsUpdate.class, TicketsDelete.class});
                    break;
                case 4:
                    new AdminCrudMenu(new Class[]{AirportCreate.class, AirportView.class, AirportUpdate.class, AirportDelete.class});
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
