import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {
  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/game_review_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteGameQuery = "DELETE FROM games *;";
      String deletePlatfromQuery = "DELETE FROM platforms *;";
      String deleteReviewQuery = "DELETE FROM reviews *;";
      con.createQuery(deleteGameQuery).executeUpdate();
      con.createQuery(deletePlatfromQuery).executeUpdate();
      con.createQuery(deleteReviewQuery).executeUpdate();
    }
  }
}
