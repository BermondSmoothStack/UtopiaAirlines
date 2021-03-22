package apr.ss.utopia.dao;

import java.sql.*;
import java.util.List;

public abstract class BaseDAO <T>{

    protected Connection conn = null;

    public BaseDAO(Connection conn) {
        this.conn = conn;
    }

    public Integer save(String sql, Object[] vals)  throws /* ClassNotFoundException, */ SQLException {
        PreparedStatement pstmt =
                conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int count = 1;
        for(Object o: vals) {
            pstmt.setObject(count, o);
            count++;
        }
        pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
        while(rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public boolean delete(String sql, Object[] vals) throws SQLException {
        PreparedStatement pstmt =
                conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int count = 1;
        for(Object o: vals) {
            pstmt.setObject(count, o);
            count++;
        }
        pstmt.executeUpdate();
        return pstmt.getUpdateCount() > 0;
    }

    public List<T> read(String sql, Object[] vals)  throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt =
                conn.prepareStatement(sql);
        int count = 1;
        for(Object o: vals) {
            pstmt.setObject(count, o);
            count++;
        }
        ResultSet rs = pstmt.executeQuery();
        return extractData(rs);
    }

    abstract public List<T> extractData(ResultSet rs) throws ClassNotFoundException, SQLException ;

}
