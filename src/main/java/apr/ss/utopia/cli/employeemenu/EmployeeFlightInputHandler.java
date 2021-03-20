package apr.ss.utopia.cli.employeemenu;

import apr.ss.utopia.inputhandler.IntInputHandler;

public class EmployeeFlightInputHandler extends IntInputHandler {

    public EmployeeFlightInputHandler(Integer max) {
        this.max = max;
    }

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
        return max;
    }
}
