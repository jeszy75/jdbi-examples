package ex6;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import org.tinylog.Logger;

public class Main {

    public static void main(String[] args) {
        var jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (var handle = jdbi.open()) {
            var dao = handle.attach(LegoSetDao.class);
            dao.createTable();
            dao.insertLegoSet(new LegoSet("60073", 2015, 233));
            dao.insertLegoSet(new LegoSet("75211", 2018, 519));
            dao.insertLegoSet(new LegoSet("21034", 2017, 468));
            dao.getLegoSet("75211").ifPresent(Logger::info);
            dao.listLegoSets().forEach(Logger::info);
        }
    }

}
