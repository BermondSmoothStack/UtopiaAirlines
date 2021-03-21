package apr.ss.utopia.cli.adminmenu.create;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airplane;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Route;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.inputhandler.StringInputHandler;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.stream.Collectors;

public class FlightCreate extends AbsCRUD {


    @Override
    protected void display() throws SQLException {

        Flight flight = new Flight();
        System.out.println("Creating new Flights.\n");

        System.out.println("Select your departure/origin port:\n");
        List<Airport> airportList = null; // TODO: Fetch airports
        int origCount = 1;
        for (Airport airport : airportList) {
            System.out.println("\n[" + origCount + "] " + airport.getAirportCode() + " " + airport.getCity());
            origCount++;
        }

        System.out.println("[" + origCount++ + "] Create New Origin");
        System.out.println("[" + origCount + "] Quit");
        IntInputHandler ihO = new IntInputHandler(1, origCount);
        ihO.handler();
        if (ihO.getVerifiedInput() == origCount) return;


        Airport origin = ihO.getVerifiedInput() == origCount - 1 ? new AirportCreate().getAirport() : airportList.get(ihO.getVerifiedInput());
        airportList = airportList.parallelStream().filter(o -> !o.equals(origin)).collect(Collectors.toList());

        System.out.println("Select your destination/arrival port:\n");
        int destCount = 1;
        for (Airport airport : airportList) {
//            if (destCount == ihO.getVerifiedInput()) {
//                destCount++;
//                continue;
//            }
            System.out.println("\n[" + destCount + "] " + airport.getAirportCode() + " " + airport.getCity());
            destCount++;
        }
        System.out.println("[" + destCount++ + "] Create New Destination");
        System.out.println("[" + destCount + "] Quit");
        IntInputHandler ihD = new IntInputHandler(1, destCount);
        ihD.handler();
        if (ihD.getVerifiedInput() == destCount) return;
        Airport destination = ihO.getVerifiedInput() == origCount - 1 ? new AirportCreate().getAirport() : airportList.get(ihO.getVerifiedInput());

        Route route = new Route();
        route.setDestinationAirport(destination);
        route.setOriginAirport(origin);
        // TODO Create route

        System.out.println("Select a plane.");
        // TODO: fetch all planes
        List<Airplane> airplanes = null;
        int planeCount = 1;
        for (Airplane a : airplanes) {
            System.out.println("[" + planeCount + "] ID: " + a.getId() + " Type: " + a.getType());
            planeCount++;
        }
        IntInputHandler ihA = new IntInputHandler(1, planeCount);
        ihA.handler();
        Airplane a = airplanes.get(ihA.getVerifiedInput());


        while (true) {
            System.out.println("Enter departure date (MM-dd-yyyy):");
            String dateStr = new StringInputHandler().getVerifiedInput();
            System.out.println("Enter departure time:");
            String timeStr = new StringInputHandler().getVerifiedInput();
            String dateTimeStr = dateStr + " " + timeStr;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy").withResolverStyle(ResolverStyle.STRICT);
            try {
                LocalDateTime departureTime = LocalDateTime.from(dtf.parse(dateTimeStr));
                flight.setDepartureTime(departureTime);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Date wasn't properly formatted, try again.");
            }
        }
        System.out.println("Enter flight duration in minutes: ");
        IntInputHandler durIh = new IntInputHandler(1, 9999);
        durIh.handler();
        Integer duration = durIh.getVerifiedInput();

        while (true) {
            System.out.println("Enter seat prices");
            try {
                Float price = Float.parseFloat(new StringInputHandler().getVerifiedInput());
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                flight.setSeatPrice(Float.parseFloat(df.format(price)));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input a valid price.");
            }
        }

        System.out.println("Enter reserved seats [0 - " + flight.getAirplane().getType().getCapacity() + "]");
        IntInputHandler ihSeats = new IntInputHandler(0, flight.getAirplane().getType().getCapacity());
        ihSeats.handler();
        flight.setReservedSeats(ihSeats.getVerifiedInput());
        flight.setAirplane(a);
        flight.setDuration(duration);
        flight.setRoute(route);
        // TODO: Create Flight
    }
}
