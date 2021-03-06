package apr.ss.utopia.inputhandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StringInputHandler implements IInputHandler<String>{
    protected String verifiedInput;

    public StringInputHandler(){
        handler();
    }

    public String scanInput(){
        Scanner input = new Scanner(System.in);
        String strInput;

        try {
            strInput = input.nextLine();
        } catch (InputMismatchException e){
            System.out.println("Please enter a String");
            strInput = scanInput();
        }

        return strInput;
    }

    @Override
    public String getInput() {
        return scanInput();
    }


    @Override
    public void handler() {
        verifiedInput = getInput();
    }


    public String getVerifiedInput(){ return verifiedInput;}
}
