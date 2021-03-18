package apr.ss.utopia.entity;

import java.time.LocalDateTime;

public class Flight {

    public final static String NAME = "flight";
    public final static String ID = "id";
    public final static String ROUTE = "route_id";
    public final static String AIRPLANE = "airplane_id";
    public final static String DEPARTURE = "departure_time";
    public final static String RESERVED_SEATS = "reserved_seats";
    public final static String SEAT_PRICE = "seat_price";

    private Integer id;
    private Route route;
    private Airplane airplane;
    private LocalDateTime departureTime;
    private Integer reservedSeats;
    private Float seatPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Integer reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Float getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(Float seatPrice) {
        this.seatPrice = seatPrice;
    }
}
