package apr.ss.utopia.cli.flightmenu;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Route;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.AirportService;
import apr.ss.utopia.service.FlightService;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;

public class FlightUpdateMenu implements Menu<String> {

    private final String QUIT = "quit";
    private final String NA = "n/a";
    private Flight flight;

    public FlightUpdateMenu(Flight flight) {
        this.flight = flight;
        String input = "";
        Boolean proceed = false;
        while (true) {
            display();

            AirportService as = new AirportService();
            while (!proceed) {
                System.out.println("Please enter a new Origin Airport or enter " + NA + " for no change.");
                input = getMenuSelection();
                if (input.equalsIgnoreCase(QUIT)) return;
                if (!NA.equalsIgnoreCase(input)) {
                    Route or = flight.getRoute();

                    Airport oa = new Airport();
                    oa.setAirportCode(input);
                    oa = as.fetchAirportByIATA(oa);
                    if (null == oa) {
                        System.out.println("Can't find Airport!");
                    } else {
                        or.setOriginAirport(oa);
                        flight.setRoute(or);
                        proceed = true;
                    }
                } else proceed = true;
            }

            proceed = false;
            while (!proceed) {
                System.out.println("Please enter a new Destination Airport or enter " + NA + " for no change.");
                input = getMenuSelection();
                if (input.equalsIgnoreCase(QUIT)) return;
                if (!NA.equalsIgnoreCase(input)) {
                    Route dr = flight.getRoute();
                    Airport da = new Airport();
                    da.setAirportCode(input);
                    da = as.fetchAirportByIATA(da);
                    if (null == da) {
                        System.out.println("Can't find Airport");
                    } else {
                        dr.setDestinationAirport(da);
                        flight.setRoute(dr);
                        proceed = true;
                    }
                } else proceed = true;
            }
            proceed = false;
            while (!proceed) {
                System.out.println("Please enter a new Departure Date (mm-dd-yyyy Format) or enter " + NA + " for no change.");
                String dateStr = getMenuSelection();
                if (NA.equalsIgnoreCase(dateStr)) break;
                if (QUIT.equalsIgnoreCase(dateStr)) return;

                System.out.println("Please enter a new Departure Time (24h Format) or enter " + NA + " for no change.");
                String timeStr = getMenuSelection();
                if (NA.equalsIgnoreCase(timeStr)) break;
                if (QUIT.equalsIgnoreCase(timeStr)) return;

                String dateTimeStr = dateStr + " " + timeStr;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);
                try {
                    LocalDateTime date = LocalDateTime.parse(dateTimeStr, dtf);
                    flight.setDepartureTime(date);
                    proceed = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Date and time given was invalid, try again.");
                }
            }

            proceed = false;
            while (!proceed) {
                System.out.println("Please enter a new Arrival Date (mm-dd-yyyy Format) or enter " + NA + " for no change.");
                String dateStr = getMenuSelection();
                if (NA.equalsIgnoreCase(dateStr)) break;
                if (QUIT.equalsIgnoreCase(dateStr)) return;

                System.out.println("Please enter a new Arrival Time (24h Format) or enter " + NA + " for no change.");
                String timeStr = getMenuSelection();
                if (NA.equalsIgnoreCase(timeStr)) break;
                if (QUIT.equalsIgnoreCase(timeStr)) return;

                String dateTimeStr = dateStr + " " + timeStr;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);
                try {
                    LocalDateTime date = LocalDateTime.parse(dateTimeStr, dtf);
                    LocalDateTime fromDate = flight.getDepartureTime();
                    Integer duration = Math.toIntExact(ChronoUnit.MINUTES.between(fromDate, date));
                    flight.setDuration(duration);
                    proceed = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Date and time given was invalid, try again.");
                }
            }
            FlightService fs = new FlightService();
            fs.updateFlight(flight.getRoute(), flight);

        }
    }

    @Override
    public void display() {
        System.out.println("You have chosen to update the Flight with" +
                "\nFlight ID: " + flight.getId() + " and " +
                "\nFlight Origin: " + flight.getRoute().getOriginAirport().getCity() + " and " +
                "\nFlight Destination: " + flight.getRoute().getDestinationAirport().getCity() + ". \n" +
                "\nEnter ‘quit’ at any prompt to cancel operation.");
    }

    @Override
    public String getMenuSelection() {
        return new StringInputHandler().getInput();
    }
}
