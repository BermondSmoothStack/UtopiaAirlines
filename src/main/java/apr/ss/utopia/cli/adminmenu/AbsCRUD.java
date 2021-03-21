package apr.ss.utopia.cli.adminmenu;

import apr.ss.utopia.inputhandler.StringInputHandler;

import java.io.IOException;
import java.sql.SQLException;

public abstract class AbsCRUD {

    public AbsCRUD() {
        process();
    }

    protected void process() {
        while (true) {
            try {
                display();
                System.in.read();
                return;
            } catch (SQLException e) {
                System.out.println("Process failed. Try again? [Y/N]");
                StringInputHandler sih = new StringInputHandler();
                if (sih.getInput().equalsIgnoreCase("y"))
                    return;
            } catch (IOException e){
                System.out.println("Process failed unexpectedly, IOException");
                return;
            }

        }
    }

    protected abstract void display() throws SQLException;
}
