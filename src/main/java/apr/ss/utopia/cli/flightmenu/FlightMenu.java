package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;

import java.io.IOException;

public class FlightMenu implements Menu<Integer> {

    public FlightMenu(Flight flight) throws IOException {
        while(true){
            System.out.flush();
            display();

            switch(getMenuSelection()){
                case 1:
                    new FlightViewMenu(flight);
                    break;
                case 2:
                    new FlightUpdateMenu(flight);
                    break;
                case 3:
                    // TODO Add Seats
                    new FlightSeatsMenu(flight);
                case 4:
                    return;
                default:
                    break;
            }
        }
    }

    @Override
    public void display() {
        System.out.println("[1] View more details about the flight.");
        System.out.println("[2] Update details of the flight");
        System.out.println("[3] Add Seats to Flight");
        System.out.println("[4] Quit to previous menu");
    }

    @Override
    public Integer getMenuSelection() {
        FlightMenuInputHandler ih = new FlightMenuInputHandler();
        ih.handler();
        return ih.getVerifiedInput();
    }
}
