import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Platform {
  private int id;
  private String name;
  private List<Game> gameList;

  public Platform (String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherPlatform) {
    if (!(otherPlatform instanceof Platform)) {
      return false;
    } else {
      Platform newPlatform = (Platform) otherPlatform;
      return this.getName().equals(newPlatform.getName());
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO platforms (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Platform> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM platforms";
      return con.createQuery(sql).executeAndFetch(Platform.class);
    }
  }

  public static Platform find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM platforms WHERE id = :id";
      return con.createQuery(sql)
             .addParameter("id", id)
             .executeAndFetchFirst(Platform.class);
    }
  }

  public static Platform findName(String name) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM platforms WHERE name = :name";
      return con.createQuery(sql)
             .addParameter("name", name)
             .executeAndFetchFirst(Platform.class);
    }
  }

  public List<Game> getGames() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM games WHERE platform_id = :id";
      return con.createQuery(sql)
             .addParameter("id", id)
             .executeAndFetch(Game.class);
    }
  }
}
