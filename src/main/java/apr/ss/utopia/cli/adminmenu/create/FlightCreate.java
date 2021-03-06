package apr.ss.utopia.cli.adminmenu.create;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airplane;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Route;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.AirplaneService;
import apr.ss.utopia.service.AirportService;
import apr.ss.utopia.service.FlightService;
import apr.ss.utopia.service.RouteService;

import java.sql.SQLException;
import java.text.DecimalFormat;
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
        List<Airport> airportList = new AirportService().fetchAllAirports();
        int origCount = 1;
        for (Airport airport : airportList) {
            System.out.println("[" + origCount + "] " + airport.toString());
            origCount++;
        }

        System.out.println("[" + origCount++ + "] Create New Origin");
        System.out.println("[" + origCount + "] Quit");
        IntInputHandler ihO = new IntInputHandler(1, origCount);
        ihO.handler();
        if (ihO.getVerifiedInput() == origCount) return;
        Airport origin = ihO.getVerifiedInput() == origCount - 1 ? new AirportCreate().getAirport() : airportList.get(ihO.getVerifiedInput() - 1);
        airportList = airportList.parallelStream().filter(o -> !o.equals(origin)).collect(Collectors.toList());

        System.out.println("Select your destination/arrival port:\n");
        int destCount = 1;
        for (Airport airport : airportList) {
//            if (destCount == ihO.getVerifiedInput()) {
//                destCount++;
//                continue;
//            }
            System.out.println("[" + destCount + "] " + airport.getAirportCode() + " " + airport.getCity());
            destCount++;
        }
        System.out.println("[" + destCount++ + "] Create New Destination");
        System.out.println("[" + destCount + "] Quit");
        IntInputHandler ihD = new IntInputHandler(1, destCount);
        ihD.handler();
        if (ihD.getVerifiedInput() == destCount) return;
        Airport destination = ihD.getVerifiedInput() == origCount - 1 ? new AirportCreate().getAirport() : airportList.get(ihD.getVerifiedInput() - 1);

        Route route = new Route();
        route.setDestinationAirport(destination);
        route.setOriginAirport(origin);

        route = new RouteService().createRoute(route);

        System.out.println("Select a plane.");
        List<Airplane> airplanes = new AirplaneService().fetchAllAirplane();
        int planeCount = 1;
        for (Airplane a : airplanes) {
            System.out.println("[" + planeCount + "] ID: " + a.getId() + " Capacity: " + a.getType().getCapacity());
            planeCount++;
        }
        System.out.println("[" + planeCount++ + "] Create New Plane");
        System.out.println("[" + planeCount + "] Quit");
        IntInputHandler ihA = new IntInputHandler(1, planeCount);
        ihA.handler();
        if (ihA.getVerifiedInput() == planeCount) return;
        Airplane a = ihA.getVerifiedInput() == planeCount - 1 ? new AirplaneCreate().getAirplane() : airplanes.get(ihA.getVerifiedInput() - 1);


        while (true) {
            System.out.println("Enter departure date (MM-dd-yyyy):");
            String dateStr = new StringInputHandler().getVerifiedInput();
            System.out.println("Enter departure time (24h Format):");
            String timeStr = new StringInputHandler().getVerifiedInput();
            String dateTimeStr = dateStr + " " + timeStr;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);
            try {
                LocalDateTime departureTime = LocalDateTime.from(dtf.parse(dateTimeStr));
                flight.setDepartureTime(departureTime);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Date wasn't properly formatted, try again. [Y/N]");
                if (!"Y".equalsIgnoreCase(new StringInputHandler().getVerifiedInput()))
                    return;
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

//        System.out.println("Enter reserved seats [0 - " + flight.getAirplane().getType().getCapacity() + "]");
//        IntInputHandler ihSeats = new IntInputHandler(0, flight.getAirplane().getType().getCapacity());
//        ihSeats.handler();
        flight.setReservedSeats(0);
        flight.setAirplane(a);
        flight.setDuration(duration);
        flight.setRoute(route);
        flight = new FlightService().createFlight(flight);
        if (!(null == flight || null == flight.getId())) {
            System.out.println("Flight Creation Successful.");
        }
    }
}
