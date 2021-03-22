package apr.ss.utopia.cli.adminmenu.read;

import apr.ss.utopia.cli.adminmenu.AbsCRUD;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.AirportService;

import java.sql.SQLException;
import java.util.List;

public class AirportView extends AbsCRUD {

    @Override
    protected void display() throws SQLException {

        AirportService as = new AirportService();
//        Airport a = new Airport();
//        a.setAirportCode(code);
//        a = as.fetchAirportByIATA(a);
//        if (null == a || null == a.getAirportCode()){
//            System.out.println("Failed to obtain information");
//        }
        List<Airport> airportList = as.fetchAllAirports();
        System.out.println("Airport Information");
        for (Airport a : airportList) {
            System.out.println(
                            "\nIATA:\t " + a.getAirportCode() + "\n" +
                            "City:\t " + a.getCity()
            );
        }
    }
}
