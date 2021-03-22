package apr.ss.utopia.dao;

import apr.ss.utopia.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO extends BaseDAO<Flight> {

    public FlightDAO(Connection conn) {
        super(conn);
    }

    public Integer addFlight(Flight flight) throws SQLException {
        return save("insert into " + Flight.NAME + " (" +
                        Flight.ROUTE + ", " +
                        Flight.AIRPLANE + ", " +
                        Flight.DEPARTURE + ", " +
                        Flight.RESERVED_SEATS + "," +
                        Flight.DURATION + "," +
                        Flight.SEAT_PRICE + ")" +
                        " values (?,?,?,?,?,?)",
                new Object[]{
                        flight.getRoute().getId(),
                        flight.getAirplane().getId(),
                        flight.getDepartureTime(),
                        0,
                        flight.getDuration(),
                        flight.getSeatPrice()
                });
    }

    public void updateFlight(Flight flight) throws SQLException {
        save("update " + Flight.NAME + " set " +
                        Flight.ROUTE + " = ?, " +
                        Flight.AIRPLANE + " = ?, " +
                        Flight.DEPARTURE + " = ?, " +
                        Flight.DURATION + " = ?, " +
                        Flight.RESERVED_SEATS + " = ?, " +
                        Flight.SEAT_PRICE + " = ? " +
                        "where " + Flight.ID + " = ?",
                new Object[]{
                        flight.getRoute().getId(),
                        flight.getAirplane().getId(),
                        flight.getDepartureTime(),
                        flight.getDuration(),
                        0,
                        flight.getSeatPrice(),
                        flight.getId()
                });
    }

    public boolean deleteFlight(Flight flight) throws SQLException {
        return delete("delete from " + Flight.NAME + " where " + Flight.ID + " = ?", new Object[]{flight.getId()});
    }

    public List<Flight> readAllFlight() throws ClassNotFoundException, SQLException {
        return read("select * from " + Flight.NAME, new Object[]{});
    }

    public List<Flight> readFlightsByCode(Flight flight) throws ClassNotFoundException, SQLException {
        return readByCode(
                "select " +
                        "flight.id as flight_id, " +
                        "origin.iata_id as origin_id, " +
                        "origin.city as origin_city, " +
                        "destination.iata_id as destination_id, " +
                        "destination.city as destination_city, " +
                        "departure_time, " +
                        "seat_price, " +
                        "duration_mins, " +
                        "airplane.type_id as airplane_type " +
                        "from " +
                            "(select iata_id, " +
                            "city, route.id as route_id, " +
                            "flight.id as flight_id " +
                            "from flight " +
                            "join route on flight.route_id = route.id " +
                            "join airport on route.origin_id = airport.iata_id) as origin " +
                        "join " +
                            "(select iata_id, " +
                            "city, route.id as route_id " +
                            "from " +
                            "flight " +
                            "join route on flight.route_id = route.id " +
                            "join airport on route.destination_id = airport.iata_id) as destination " +
                        "on origin.route_id = destination.route_id " +
                        "join flight " +
                        "on flight.id = origin.flight_id " +
                        "join airplane " +
                        "on airplane.id = flight.airplane_id " +
                        "where flight.id = ?",
                flight.getId()
                );
    }

    public List<Flight> readByCode(String sql, Integer id)  throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();
        List<Flight> flights = new ArrayList<>();
        while(rs.next()){
            Flight f = new Flight();
            Airplane a = new Airplane();
            AirplaneType at = new AirplaneType();
            Route r = new Route();
            Airport o = new Airport();
            Airport d = new Airport();

            o.setAirportCode(rs.getString("origin_id"));
            o.setCity(rs.getString("origin_city"));

            d.setAirportCode(rs.getString("destination_id"));
            d.setCity(rs.getString("destination_city"));

            r.setOriginAirport(o);
            r.setDestinationAirport(d);

            at.setId(rs.getInt("airplane_type"));
            a.setType(at);

            f.setRoute(r);
            f.setAirplane(a);
            f.setId(rs.getInt("flight_id"));
            f.setDuration(rs.getInt(Flight.DURATION));
            f.setDepartureTime(rs.getTimestamp(Flight.DEPARTURE).toLocalDateTime());
            f.setSeatPrice(rs.getFloat(Flight.SEAT_PRICE));

            flights.add(f);
        }
        return flights;
    }

    @Override
    public List<Flight> extractData(ResultSet rs) throws SQLException {
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {
            Flight f = new Flight();
            Airplane a = new Airplane();
            Route r = new Route();

            a.setId(rs.getInt(Flight.AIRPLANE));
            r.setId(rs.getInt(Flight.ROUTE));

            f.setId(rs.getInt(Flight.ID));
            f.setAirplane(a);
            f.setRoute(r);
            f.setDuration(rs.getInt(Flight.DURATION));
            f.setDepartureTime(rs.getTimestamp(Flight.DEPARTURE).toLocalDateTime());
            f.setSeatPrice(rs.getFloat(Flight.SEAT_PRICE));

            flights.add(f);
        }

        return flights;
    }
}
