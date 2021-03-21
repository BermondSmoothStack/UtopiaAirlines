package apr.ss.utopia.cli.adminmenu;

import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;

public abstract class AbsCRUD {

    public AbsCRUD() {
        process();
    }

    protected void process() {
        while (true) {
            try {
                display();
            } catch (SQLException e) {
                System.out.println("Process failed. Try again? [Y/N]");
                StringInputHandler sih = new StringInputHandler();
                if (sih.getInput().equalsIgnoreCase("y"))
                    return;
            }
        }
    }

    protected abstract void display() throws SQLException;
}
