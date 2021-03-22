package apr.ss.utopia.cli.travelermenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.cli.flightmenu.FlightSelectMenu;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.service.PassengerService;

import java.io.IOException;

public class TravelerMainMenu implements Menu<Integer> {

    public TravelerMainMenu() throws IOException {
        while (true) {
            Passenger p = handleMembership();
            if (null == p) return;
            display();
            switch(getMenuSelection()){
                case 1:
                    new FlightSelectMenu(FlightSelectMenu.BOOK_METHOD);
                    break;
                case 2:
                    new FlightSelectMenu(FlightSelectMenu.CANCEL_METHOD, "", p);
                    break;
                case 3:
                    return;
                default:
            }

        }
    }

    @Override
    public void display() {
        System.out.println(
                        "[1]Book a Ticket\n" +
                        "[2]Cancel an Upcoming Trip\n" +
                        "[3]Quit to Previous "
        );

    }

    private Passenger handleMembership() {
        while (true) {
            TravelerNumberInputHandler tih = new TravelerNumberInputHandler();
            System.out.println("Enter your Membership Number or leave it empty to go back to the previous menu");
            Integer input = tih.getInput();
            PassengerService us = new PassengerService();
            Passenger p = us.getPassengerById(input);

            if (input < 0)
                return null;
            if ((null != p && null != p.getId())) {
                return p;
            }
        }
    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1,3);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
