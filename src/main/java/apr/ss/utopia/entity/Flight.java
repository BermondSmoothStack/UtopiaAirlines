package apr.ss.utopia.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Flight {

    public final static String NAME = "flight";
    public final static String ID = "id";
    public final static String ROUTE = "route_id";
    public final static String AIRPLANE = "airplane_id";
    public final static String DEPARTURE = "departure_time";
    public final static String DURATION = "duration_mins";
//    public final static String RESERVED_SEATS = "reserved_seats"; moved to Seats Table
    public final static String SEAT_PRICE = "seat_price";

    private Integer id;
    private Route route;
    private Airplane airplane;
    private LocalDateTime departureTime;
    private Integer duration;
    //    private Integer reservedSeats; Moved to Seats Table
    private Float seatPrice;
    private List<Seats> seats;

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

    public Float getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(Float seatPrice) {
        this.seatPrice = seatPrice;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Seats> getSeats() {
        return seats;
    }

    public void setSeats(List<Seats> seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id.equals(flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
