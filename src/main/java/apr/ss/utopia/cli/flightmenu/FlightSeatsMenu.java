package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Seats;

public class FlightSeatsMenu implements Menu<Integer> {
    private Flight flight;

    public FlightSeatsMenu(Flight flight) {
        this.flight = flight;
        while (true) {
            display();
            Seats s = null;
            switch (getMenuSelection()) {
                case 1:
                    s = flight.getSeats().stream().filter(o -> o.getType().getName().equalsIgnoreCase("first")).findFirst().orElse(null);
                    break;
                case 2:
                    s = flight.getSeats().stream().filter(o -> o.getType().getName().equalsIgnoreCase("business")).findFirst().orElse(null);
                    break;
                case 3:
                    s = flight.getSeats().stream().filter(o -> o.getType().getName().equalsIgnoreCase("economy")).findFirst().orElse(null);
                    break;
                case 4:
                    return;
                default:
            }
            if (null == s) {
                System.out.println("Seat not found, try again");
                continue;
            }
            System.out.println("Existing number of seats: " + s.getCapacity());
            System.out.println("Enter new number of seats: ");

            FlightSeatsInputHandler ih = new FlightSeatsInputHandler(0, 999);
            // TODO: update number of seats
            // TODO: display updated results
        }
    }

    @Override
    public void display() {
        System.out.println("Pick the Seat Class you want to add seats of, to your flight:\n" +
                "[1] First\n" +
                "[2] Business\n" +
                "[3] Economy\n" +
                "[4] Quit to cancel operation");
    }

    @Override
    public Integer getMenuSelection() {
        FlightSeatsInputHandler ih = new FlightSeatsInputHandler(1,4);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
