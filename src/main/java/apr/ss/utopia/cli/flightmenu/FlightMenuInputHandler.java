package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.inputhandler.IntInputHandler;

public class FlightMenuInputHandler extends IntInputHandler {

    @Override
    public void handler() {
        verifiedInput = getInput();
    }

    @Override
    protected Integer getMin() {
        return 1;
    }

    @Override
    protected Integer getMax() {
        return 4;
    }
}
