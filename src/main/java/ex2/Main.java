package ex2;

import org.jdbi.v3.core.Jdbi;

import org.tinylog.Logger;

public class Main {

    public static void main(String[] args) {
        var jdbi = Jdbi.create("jdbc:h2:mem:test");
        try (var handle = jdbi.open()) {
            handle.execute("""
                    CREATE TABLE legoset (
                        number VARCHAR PRIMARY KEY,
                        "year" INTEGER NOT NULL,
                        pieces INTEGER NOT NULL
                    )
                    """
            );
            var insert = handle.createUpdate("INSERT INTO legoset VALUES (?, ?, ?)");
            insert.bind(0, "60073")
                    .bind(1, 2015)
                    .bind(2, 233)
                    .execute();
            insert.bind(0, "75211")
                    .bind(1, 2018)
                    .bind(2, 519)
                    .execute();
            insert.bind(0, "21034")
                    .bind(1, 2017)
                    .bind(2, 468)
                    .execute();
            var totalPieces = handle.createQuery("SELECT SUM(pieces) FROM legoset").mapTo(Integer.class).one();
            Logger.info("Total number of LEGO pieces: {}", totalPieces);
        }
    }

}
