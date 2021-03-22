package apr.ss.utopia.service;

import apr.ss.utopia.dao.AirportDAO;
import apr.ss.utopia.dao.SeatDAO;
import apr.ss.utopia.dao.SeatTypeDAO;
import apr.ss.utopia.entity.SeatType;
import apr.ss.utopia.entity.Seats;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatService {
    Util util = new Util();

    public Seats createSeat(Seats seat){
        Connection conn;
        try {
            conn = util.getConnection();
            SeatDAO seatDAO = new SeatDAO(conn);
            seat.setId(seatDAO.addSeat(seat));
            conn.commit();
            return seat;
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Seat Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return null;
    }

    public List<SeatType> fetchAllSeatTypes() {
        Connection conn = null;

        try {
            conn = util.getConnection();
            SeatTypeDAO seatDAO = new SeatTypeDAO(conn);
            return seatDAO.readAllSeatType();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return new ArrayList<>();
    }

    public SeatType fetchSeatTypeByName(String name) {
        Connection conn = null;

        try {
            conn = util.getConnection();
            SeatTypeDAO seatTypeDAO = new SeatTypeDAO(conn);
            SeatType s = new SeatType();
            s.setName(name);
            List<SeatType> sl = seatTypeDAO.readSeatTypesByType(s);
            s = sl.isEmpty() ? null : sl.get(0);
            return s;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean deleteSeat(Seats seat) {
        Connection conn;
        try {
            conn = util.getConnection();
            SeatDAO seatDAO = new SeatDAO(conn);
            boolean isSuccess = seatDAO.deleteSeat(seat);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public Seats fetchSeatById(Seats seats) {
        Connection conn = null;

        try {
            conn = util.getConnection();
            SeatDAO seatDAO = new SeatDAO(conn);
            Seats s = new Seats();
            s.setId(seats.getId());
            List<Seats> sl = seatDAO.readSeatsByCode(s);
            s = sl.isEmpty() ? null : sl.get(0);
            return s;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }
}
