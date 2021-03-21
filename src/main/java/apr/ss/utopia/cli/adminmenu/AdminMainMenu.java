package apr.ss.utopia.cli.adminmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.cli.adminmenu.create.*;
import apr.ss.utopia.cli.adminmenu.delete.*;
import apr.ss.utopia.cli.adminmenu.read.*;
import apr.ss.utopia.cli.adminmenu.update.*;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.lang.reflect.InvocationTargetException;

public class AdminMainMenu implements Menu<Integer> {

    public AdminMainMenu() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        while(true){
            display();
            switch(getMenuSelection()){
                case 1:
                    new AdminCrudMenu(new Class[]{FlightCreate.class, FlightView.class, FlightUpdate.class, FlightDelete.class});
                    break;
                case 2:
                    new AdminCrudMenu(new Class[]{SeatsCreate.class, SeatsView.class, SeatsUpdate.class, SeatsDelete.class});
                    break;
                case 3:
                    new AdminCrudMenu(new Class[]{TicketsCreate.class, TicketsView.class, TicketsUpdate.class, TicketsDelete.class});
                    break;
                case 4:
                    new AdminCrudMenu(new Class[]{AirportCreate.class, AirportView.class, AirportUpdate.class, AirportDelete.class});
                    break;
                case 5:
                    new AdminCrudMenu(new Class[]{UserCreate.class, UserView.class, UserUpdate.class, UserDelete.class});
                    break;
                case 6:
                    new AdminOverrideCancellation();
                    break;
                case 7:
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
                "[5] Manage Users\n" +
                "[6] Over-ride Trip Cancellation for a ticket.\n" +
                "[7] Go Back");
    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1, 7);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
