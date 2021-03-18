package apr.ss.utopia.entity;

public class Route {

    public static final String NAME = "route";
    public static final String ID = "id";
    public static final String ORIGIN_CODE = "origin_id";
    public static final String DEST_CODE = "destination_id";

    private Integer id;
    private Airport destinationAirport;
    private Airport originAirport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }
}
