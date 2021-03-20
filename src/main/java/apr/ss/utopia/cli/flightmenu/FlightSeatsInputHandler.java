package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.inputhandler.IntInputHandler;

public class FlightSeatsInputHandler extends IntInputHandler {

    public FlightSeatsInputHandler(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void handler() {
        verifiedInput = getInput();
    }

    @Override
    protected Integer getMin() {
        return min;
    }

    @Override
    protected Integer getMax() {
        return max;
    }
}
