package apr.ss.utopia.cli.adminmenu.update;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.cli.adminmenu.create.AirportCreate;
import apr.ss.utopia.entity.Airplane;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Route;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.AirplaneService;
import apr.ss.utopia.service.AirportService;
import apr.ss.utopia.service.FlightService;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.stream.Collectors;

public class FlightUpdate extends AbsCRUD {


    @Override
    protected void display() throws SQLException {

        System.out.println("Update a Flight.\n");
        System.out.println("Enter Flight ID:");
        String idInput = new StringInputHandler().getVerifiedInput();
        FlightService fs = new FlightService();
        Flight flight = new Flight();
        flight.setId(Integer.parseInt(idInput));
        flight = fs.fetchFlightById(flight);
        Route route = new Route();

        System.out.println("Current registered airport: " + flight.getRoute().getOriginAirport().toString());
        System.out.println("Select your departure/origin port\n");
        AirportService aps = new AirportService();
        List<Airport> airportList = aps.fetchAllAirports();
        int origCount = 1;
        for (Airport airport : airportList) {
            System.out.println("[" + origCount + "] " + airport.getAirportCode() + " " + airport.getCity());
            origCount++;
        }
        System.out.println("[" + origCount++ + "] Create New Origin");
        System.out.println("[" + origCount + "] Skip");
        IntInputHandler ihO = new IntInputHandler(1, origCount);
        ihO.handler();

        Airport origin = ihO.getVerifiedInput() == origCount - 1 ?
                new AirportCreate().getAirport() :
                ihO.getVerifiedInput() == origCount ?
                        flight.getRoute().getOriginAirport() :
                        airportList.get(ihO.getVerifiedInput() - 1);

        airportList = airportList.parallelStream().filter(o -> !o.equals(origin)).collect(Collectors.toList());
        System.out.println("Current registered airport: " + flight.getRoute().getDestinationAirport().toString());
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
        System.out.println("[" + destCount++ + "] Create New Origin");
        System.out.println("[" + destCount + "] Skip");
        IntInputHandler ihD = new IntInputHandler(1, destCount);
        ihD.handler();

        Airport destination = ihD.getVerifiedInput() == destCount - 1 ?
                new AirportCreate().getAirport() :
                ihD.getVerifiedInput() == destCount ?
                        flight.getRoute().getDestinationAirport() :
                        airportList.get(ihD.getVerifiedInput() - 1);

        route.setDestinationAirport(destination);
        route.setOriginAirport(origin);

        System.out.println("Current registered plane type: " + flight.getAirplane().getType().getId());
        System.out.println("\nSelect a plane.");
        AirplaneService as = new AirplaneService();
        List<Airplane> airplanes = as.fetchAllAirplane();
        int planeCount = 1;
        for (Airplane a : airplanes) {
            System.out.println("[" + planeCount + "] ID: " + a.getId() + " Type: " + a.getType().getId());
            planeCount++;
        }
        IntInputHandler ihA = new IntInputHandler(1, planeCount);
        ihA.handler();
        Airplane a = airplanes.get(ihA.getVerifiedInput());


        while (true) {
            System.out.println("Current schedule is " + flight.getDepartureTime().toString());
            System.out.println("Enter departure date (MM-dd-yyyy):");
            String dateStr = new StringInputHandler().getVerifiedInput();
            System.out.println("Enter departure time:");
            String timeStr = new StringInputHandler().getVerifiedInput();
            String dateTimeStr = dateStr + " " + timeStr;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);
            try {
                LocalDateTime departureTime = LocalDateTime.from(dtf.parse(dateTimeStr));
                flight.setDepartureTime(departureTime);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Date wasn't properly formatted, try again.");
            }
        }
        System.out.println("Current duration is " + flight.getDuration());
        System.out.println("Enter flight duration in minutes: ");
        IntInputHandler durIh = new IntInputHandler(1, 9999);
        durIh.handler();
        Integer duration = durIh.getVerifiedInput();

        while (true) {
            System.out.println("Current seat price is " + flight.getSeatPrice());
            System.out.println("Enter seat price:");
            try {
                String priceStr = new StringInputHandler().getVerifiedInput();
                Float price = Float.parseFloat(priceStr);
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                flight.setSeatPrice(Float.parseFloat(df.format(price)));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input a valid price.");
            }
        }

//        System.out.println("Current reserved seats is " + flight.getReservedSeats());
//        System.out.println("Enter reserved seats [0 - " + flight.getAirplane().getType().getCapacity() + "]");
//        IntInputHandler ihSeats = new IntInputHandler(0, flight.getAirplane().getType().getCapacity());
//        ihSeats.handler();
//        flight.setReservedSeats(ihSeats.getVerifiedInput());
        flight.setAirplane(a);
        flight.setDuration(duration);
        flight.setRoute(route);
        fs.updateFlight(route,flight);
    }
}
