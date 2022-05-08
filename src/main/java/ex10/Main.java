package ex10;

import java.time.Year;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Slf4JSqlLogger;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import org.tinylog.Logger;

public class Main {

    public static void main(String[] args) {
        var jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.setSqlLogger(new Slf4JSqlLogger());
        var legoSets = jdbi.withExtension(LegoSetDao.class, dao -> {
            dao.createTable();
            dao.insertLegoSet(new LegoSet("60073", Year.of(2015), 233));
            dao.insertLegoSet(new LegoSet("75211", Year.of(2018), 519));
            dao.insertLegoSet(new LegoSet("21034", Year.of(2017), 468));
            return dao.listLegoSets();
        });
        legoSets.forEach(Logger::info);
    }

}
