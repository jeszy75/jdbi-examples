package ex10;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterArgumentFactory(YearArgumentFactory.class)
@RegisterColumnMapper(YearColumnMapper.class)
@RegisterBeanMapper(LegoSet.class)
public interface LegoSetDao {

    @SqlUpdate("""
        CREATE TABLE legoset (
            number VARCHAR PRIMARY KEY,
            "year" INTEGER NOT NULL,
            pieces INTEGER NOT NULL
        )
        """
    )
    void createTable();

    @SqlUpdate("INSERT INTO legoset VALUES (:number, :year, :pieces)")
    void insertLegoSet(@Bind("number") String number, @Bind("year") Year year, @Bind("pieces") int pieces);

    @SqlUpdate("INSERT INTO legoset VALUES (:number, :year, :pieces)")
    void insertLegoSet(@BindBean LegoSet legoSet);

    @SqlQuery("SELECT * FROM legoset WHERE number = :number")
    Optional<LegoSet> getLegoSet(@Bind("number") String number);

    @SqlQuery("SELECT * FROM legoset ORDER BY number")
    List<LegoSet> listLegoSets();

}
