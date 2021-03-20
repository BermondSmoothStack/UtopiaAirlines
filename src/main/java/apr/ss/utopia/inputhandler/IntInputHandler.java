package apr.ss.utopia.inputhandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class IntInputHandler  implements  IInputHandler{

    protected Integer verifiedInput;
    protected Integer min = 0;
    protected Integer max = 100;

    public Integer scanInput(){
        Scanner input = new Scanner(System.in);
        Integer intInput;

        try {
            intInput = input.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Please enter an integer");
            intInput = scanInput();
        }

        return intInput;
    }

     public Integer getInput(){
        int unverifiedIntInput = scanInput();

        while (!verifyInputRange(unverifiedIntInput)) {
            StringBuilder message = new StringBuilder("Please enter an integer between[");
            message.append(getMin()).append(", ").append(getMax()).append("]");
            System.out.println(message.toString());
            unverifiedIntInput = scanInput();
        }

        return unverifiedIntInput;
    }

    protected boolean verifyInputRange(Integer input){
        return ((input >= getMin()) && (input <= getMax()));
    }

    protected abstract Integer getMin();
    protected abstract Integer getMax();
    public Integer getVerifiedInput(){ return verifiedInput;}


}
