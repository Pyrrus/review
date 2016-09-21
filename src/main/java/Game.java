import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Game {
  private int id;
  private String name;
  private String game_type;
  private String description;
  private String year;
  private int review_id;
  private int platform_id;

  public Game(String name, String game_type, String description, String year, int platform_id) {
    this.name = name;
    this.game_type = game_type;
    this.description = description;
    this.year = year;
    this.platform_id = platform_id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return game_type;
  }

  public String getDescription() {
    return description;
  }

  public String getYear() {
    return year;
  }

  public int getPlatformId() {
    return platform_id;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherGame) {
    if (!(otherGame instanceof Game)) {
      return false;
    } else {
      Game newGame = (Game) otherGame;
      return this.getName().equals(newGame.getName()) &&
            this.getType().equals(newGame.getType()) &&
            this.getDescription().equals(newGame.getDescription()) &&
            this.getYear().equals(newGame.getYear()) &&
            this.getPlatformId() == newGame.getPlatformId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO game (name, game_type, description, year, platform_id) VALUES (:name, :game_type, :description, :year, :platform_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("game_type", this.game_type)
      .addParameter("description", this.description)
      .addParameter("year", this.year)
      .addParameter("platform_id", this.platform_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Game> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM game";
      return con.createQuery(sql).executeAndFetch(Game.class);
    }
  }

  public static Game find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM game WHERE id=:id";
      Game game = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Game.class);
      return game;
    }
  }
}
