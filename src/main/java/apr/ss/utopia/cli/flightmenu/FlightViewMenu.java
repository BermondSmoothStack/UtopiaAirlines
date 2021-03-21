package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Seats;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class FlightViewMenu implements Menu {

    private final Flight flight;

    public FlightViewMenu(Flight f) throws IOException {
        this.flight = f;
        display();
        System.in.read();
    }

    private String getDate(LocalDateTime datetime) {
        return datetime.getMonth().toString() + " " + datetime.getDayOfMonth() + ", " + datetime.getYear();
    }

    private String getTime(LocalDateTime dateTime) {
        return dateTime.getHour() + ":" + dateTime.getMinute();
    }

    private String getDate(LocalDateTime dateTime, Integer mins) {
        dateTime = dateTime.plusMinutes(mins);
        return dateTime.getMonth().toString() + " " + dateTime.getDayOfMonth() + ", " + dateTime.getYear();
    }

    private String getTime(LocalDateTime dateTime, Integer mins) {
        dateTime = dateTime.plusMinutes(mins);
        return dateTime.getHour() + ":" + dateTime.getMinute();
    }

    @Override
    public void display() {
        StringBuilder sb = new StringBuilder(
                "You have chosen to view the Flight with Flight ID: " +
                        flight.getId() +
                        " and Departure Airport " + flight.getRoute().getOriginAirport().getCity() +
                        " and Arrival Airport " + flight.getRoute().getDestinationAirport().getCity() +
                        ".\n\n" +
                        "Departure Airport: " + flight.getRoute().getOriginAirport().getCity() + "\t|\t" +
                        "Arrival Airport: " + flight.getRoute().getDestinationAirport().getCity() +
                        "|\n" +
                        "Departure Date: " + getDate(flight.getDepartureTime()) + "\t|\t" +
                        "Departure Time: " + getTime(flight.getDepartureTime()) +
                        "|\n" +
                        "Arrival Date: " + getDate(flight.getDepartureTime(), flight.getDuration()) + "\t|\t" +
                        "Arrival Time: " + getTime(flight.getDepartureTime(), flight.getDuration()) +
                        "\n\n" +
                        "Available Seats by Class:\n");

        for (Seats seats : flight.getSeats()) {
            sb.append(seats.getType().getName());
            sb.append(" → ");
            sb.append(seats.getAvailable());
        }
        sb.append("Press Enter to go back to the previous menu");
        System.out.println(sb.toString());
    }

    @Override
    public Integer getMenuSelection() {
        IntInputHandler ih = new IntInputHandler(0, 0);
        ih.handler();
        return ih.getVerifiedInput();
    }
}
