package apr.ss.utopia.cli.bookingmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.inputhandler.IntInputHandler;

public class BookingMenu implements Menu<Integer> {

    public BookingMenu() {
    }

    @Override
    public void display() {

    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(1,4);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
