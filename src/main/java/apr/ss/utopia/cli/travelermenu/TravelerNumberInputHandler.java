package apr.ss.utopia.cli.travelermenu;

import apr.ss.utopia.inputhandler.AbsIntInputHandler;

import java.util.Scanner;

public class TravelerNumberInputHandler extends AbsIntInputHandler {

    public TravelerNumberInputHandler() {
    }

    @Override
    public Integer scanInput() {
        Scanner input = new Scanner(System.in);
        Integer intInput;

        try {

            String line = input.nextLine();
            intInput = line.isEmpty() ? -1 : Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer");
            intInput = scanInput();
        }

        return intInput;
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
        return 999;
    }
}
