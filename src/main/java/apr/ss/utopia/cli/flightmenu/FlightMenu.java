package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;

public class FlightMenu implements Menu {

    public FlightMenu(Flight flight) {
        while(true){
            display();

            switch(getMenuSelection()){
                case 1:
                    // TODO: View Flight
                    new FlightViewMenu(flight);
                    break;
                case 2:
                    // TODO Update Flight
                    break;
                case 3:
                    // TODO Add Seats
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
