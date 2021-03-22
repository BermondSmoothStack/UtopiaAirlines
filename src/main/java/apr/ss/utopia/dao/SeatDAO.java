package apr.ss.utopia.dao;

import apr.ss.utopia.entity.AirplaneType;
import apr.ss.utopia.entity.Seats;
import apr.ss.utopia.entity.SeatType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO extends BaseDAO<Seats> {

    public SeatDAO(Connection conn) {
        super(conn);
    }

    public Integer addSeat(Seats seats) throws SQLException {
        return save("insert into " +
                        Seats.NAME + " (" +
                        Seats.TYPE + ", " +
                        Seats.AIRPLANE + ", " +
                        Seats.CAPACITY + ", " +
                        Seats.RESERVED +
                        ") " + " values (?,?,?,?)",
                new Object[]{
                        seats.getType().getId(),
                        seats.getAirplaneType().getId(),
                        seats.getCapacity(),
                        seats.getReserved(),
                });
    }

    public void updateSeat(Seats seats) throws SQLException {
        save("update " + Seats.NAME + " set " +
                        Seats.TYPE + " = ?," +
                        Seats.AIRPLANE + " = ?, " +
                        Seats.CAPACITY + " = ?, " +
                        Seats.RESERVED + " = ? " +
                        "where " + Seats.ID + " = ?",
                new Object[]{
                        seats.getType().getId(),
                        seats.getAirplaneType().getId(),
                        seats.getCapacity(),
                        seats.getReserved(),
                        seats.getId()
                });
    }

    public boolean deleteSeat(Seats seats) throws SQLException {
        return delete("delete from " + Seats.NAME + " where " + Seats.ID + " = ?", new Object[]{seats.getId()});
    }

    public List<Seats> readAllSeat() throws ClassNotFoundException, SQLException {
        return read("select * from " + Seats.NAME, new Object[]{});
    }

    public List<Seats> readSeatsByCode(Seats seats) throws ClassNotFoundException, SQLException {
        return read("select " +
                Seats.NAME + "." + Seats.ID + ", " +
                Seats.CAPACITY + ", " +
                Seats.RESERVED + ", " +
                Seats.AIRPLANE + ", " +
                SeatType.TYPE_NAME + ", " +
                Seats.TYPE +
                " from " + Seats.NAME +
                " join seat_type on seats.seat_type = seat_type.id"+
                " where "+ Seats.NAME +"." + Seats.ID + " = ?", new Object[]{seats.getId()});
    }

    @Override
    public List<Seats> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Seats> seats = new ArrayList<>();
        while (rs.next()) {
            Seats s = new Seats();
            SeatType st = new SeatType();

            st.setName(rs.getString(SeatType.TYPE_NAME));

            s.setId(rs.getInt(Seats.ID));
            s.setCapacity(rs.getInt(Seats.CAPACITY));
            s.setReserved(rs.getInt(Seats.RESERVED));
            s.setType(st);

            seats.add(s);
        }
        return seats;
    }
}
