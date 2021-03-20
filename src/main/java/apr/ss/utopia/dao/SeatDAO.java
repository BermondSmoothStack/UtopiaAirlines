package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Seat;
import apr.ss.utopia.entity.Airplane;
import apr.ss.utopia.entity.SeatType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO extends BaseDAO<Seat> {

    public SeatDAO(Connection conn) {
        super(conn);
    }

    public void addSeat(Seat seat) throws SQLException {
        save("insert into (" +
                        Seat.TYPE + ", " +
                        Seat.AIRPLANE + ", " +
                        Seat.CAPACITY + ", " +
                        Seat.RESERVED +
                        ") " +
                        Seat.NAME + " values (?,?,?,?)",
                new Object[]{
                        seat.getType().getId(),
                        seat.getAirplane().getId(),
                        seat.getCapacity(),
                        seat.getReserved(),
                });
    }

    public void updateSeat(Seat seat) throws SQLException {
        save("update " + Seat.NAME + " set " +
                        Seat.TYPE + " = ?," +
                        Seat.AIRPLANE + " = ?, " +
                        Seat.CAPACITY + " = ?, " +
                        Seat.RESERVED + " = ? " +
                        "where " + Seat.ID + " = ?",
                new Object[]{
                        seat.getType().getId(),
                        seat.getAirplane().getId(),
                        seat.getCapacity(),
                        seat.getReserved(),
                        seat.getId()
                });
    }

    public void deleteSeat(Seat seat) throws SQLException {
        save("delete from " + Seat.NAME + " where " + Seat.ID + " = ?", new Object[]{seat.getId()});
    }

    public List<Seat> readAllSeat() throws ClassNotFoundException, SQLException {
        return read("select * from " + Seat.NAME, new Object[]{});
    }

    public List<Seat> readSeatsByCode(Seat seat) throws ClassNotFoundException, SQLException {
        return read("select * from " + Seat.NAME + " where " + Seat.ID + " = ?", new Object[]{seat.getId()});
    }

    @Override
    public List<Seat> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Seat> seats = new ArrayList<>();
        while (rs.next()) {
            Seat s = new Seat();
            Airplane a = new Airplane();
            SeatType st = new SeatType();

            a.setId(rs.getInt(Seat.AIRPLANE));
            st.setId(rs.getInt(Seat.TYPE));

            s.setId(rs.getInt(Seat.ID));
            s.setAirplane(a);
            s.setCapacity(rs.getInt(Seat.CAPACITY));
            s.setReserved(rs.getInt(Seat.RESERVED));
            s.setType(st);

            seats.add(s);
        }
        return seats;
    }
}
