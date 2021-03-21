package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.io.IOException;

public class FlightBookingMenu implements Menu<Integer> {

    public static final String BOOK_METHOD = "book";
    public static final String CANCEL_METHOD = "cancel";
    private Flight flight;


    public FlightBookingMenu(Flight flight, String mode) throws IOException {
        this.flight = flight;
        while(true){
            display();
            switch (getMenuSelection()){
                case 1:
                    new FlightViewMenu(flight);
                    break;
                case 2:
                    // add
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
            }
        }
    }

    @Override
    public void display() {
        System.out.println("Pick a seat you want to book a ticket for.\n" +
                "[1] View Flight Details\n" +
                "[2] First\n" +
                "[3] Business\n" +
                "[4] Economy\n" +
                "[5] Quit to cancel operation ");
    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1, 5);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
