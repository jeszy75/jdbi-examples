package ex8;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import org.tinylog.Logger;

public class Main {

    public static void main(String[] args) {
        var jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.useExtension(LegoSetDao.class, dao -> {
            dao.createTables();
            dao.insertLegoSetWithTags(new LegoSet("60073", 2015, 233).addTag("trailer").addTag("truck").addTag("transport vehicle"));
            dao.insertLegoSetWithTags(new LegoSet("75211", 2018, 519));
            dao.insertLegoSetWithTags(new LegoSet("21034", 2017, 468).addTag("microscale"));
            dao.getLegoSetWithTags("60073").ifPresent(Logger::info);
            dao.listLegoSetsWithTags().forEach(Logger::info);
        });
    }

}
