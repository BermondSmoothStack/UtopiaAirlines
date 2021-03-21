package apr.ss.utopia.cli.travelermenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.cli.flightmenu.SelectFlightMenu;
import apr.ss.utopia.entity.User;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.io.IOException;

public class TravelerMainMenu implements Menu<Integer> {

    public TravelerMainMenu() throws IOException {
        while (true) {
            if (null == handleMembership()) return;
            display();
            switch(getMenuSelection()){
                case 1:
                    new SelectFlightMenu(SelectFlightMenu.BOOK_METHOD);
                    break;
                case 2:
                    // TODO: Cancel Trip
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

    private User handleMembership() {
        User u = null;
        while (true) {
            TravelerNumberInputHandler tih = new TravelerNumberInputHandler();
            System.out.println("Enter your Membership Number or leave it empty to go back to the previous menu");
            Integer input = tih.getInput();
            // TODO: Fetch user using membership number
            if (input < 0)
                return null;
            if (true) {
                return u;
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
