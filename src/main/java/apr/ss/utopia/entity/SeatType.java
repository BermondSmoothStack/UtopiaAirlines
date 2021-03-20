package apr.ss.utopia.entity;

import java.util.Objects;

public class SeatType {

    public static final String NAME = "seat_type";
    public static final String TYPE = "name";
    public static final String ID = "id";

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatType seatType = (SeatType) o;
        return id.equals(seatType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
