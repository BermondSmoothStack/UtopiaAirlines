package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Seats;
import apr.ss.utopia.entity.Airplane;
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

    public void addSeat(Seats seats) throws SQLException {
        save("insert into (" +
                        Seats.TYPE + ", " +
                        Seats.AIRPLANE + ", " +
                        Seats.CAPACITY + ", " +
                        Seats.RESERVED +
                        ") " +
                        Seats.NAME + " values (?,?,?,?)",
                new Object[]{
                        seats.getType().getId(),
                        seats.getAirplane().getId(),
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
                        seats.getAirplane().getId(),
                        seats.getCapacity(),
                        seats.getReserved(),
                        seats.getId()
                });
    }

    public void deleteSeat(Seats seats) throws SQLException {
        save("delete from " + Seats.NAME + " where " + Seats.ID + " = ?", new Object[]{seats.getId()});
    }

    public List<Seats> readAllSeat() throws ClassNotFoundException, SQLException {
        return read("select * from " + Seats.NAME, new Object[]{});
    }

    public List<Seats> readSeatsByCode(Seats seats) throws ClassNotFoundException, SQLException {
        return read("select * from " + Seats.NAME + " where " + Seats.ID + " = ?", new Object[]{seats.getId()});
    }

    @Override
    public List<Seats> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Seats> seats = new ArrayList<>();
        while (rs.next()) {
            Seats s = new Seats();
            Airplane a = new Airplane();
            SeatType st = new SeatType();

            a.setId(rs.getInt(Seats.AIRPLANE));
            st.setId(rs.getInt(Seats.TYPE));

            s.setId(rs.getInt(Seats.ID));
            s.setAirplane(a);
            s.setCapacity(rs.getInt(Seats.CAPACITY));
            s.setReserved(rs.getInt(Seats.RESERVED));
            s.setType(st);

            seats.add(s);
        }
        return seats;
    }
}
