package apr.ss.utopia.entity;

import java.util.Objects;

public class Seats {

    public static final String NAME = "seats";
    public static final String ID = "id";
    public static final String TYPE = "seat_type";
    public static final String AIRPLANE = "airplane_id";
    public static final String CAPACITY = "capacity";
    public static final String RESERVED = "reserved";

    private Integer id;
    private SeatType type;
    private Airplane airplane;
    private Integer capacity;
    private Integer reserved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getReserved() {
        return reserved;
    }

    public Integer getAvailable(){
        return capacity - reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seats seats = (Seats) o;
        return id.equals(seats.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
