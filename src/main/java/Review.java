import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

import java.util.Date;
import java.sql.Timestamp;

public class Review {
  private int id;
  private String name;
  private String content;
  private String created_at;
  private int game_id;

  public Review(String name, String content, String created_at, int game_id) {
    this.name = name;
    this.content = content;
    this.game_id = game_id;
    this.created_at = created_at;
  }

  public String getTime() {

    return created_at;
  }

  public String getName() {
    return name;
  }

  public String getContent() {
    return content;
  }

  public int getGameId() {
    return game_id;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherReview) {
    if (!(otherReview instanceof Review)) {
      return false;
    } else {
      Review review = (Review) otherReview;
      return this.getName().equals(review.getName()) &&
             this.getTime().equals(review.getTime()) &&
             this.getContent().equals(review.getContent()) &&
             this.getGameId() == review.getGameId();
            // && this.getId() == otherReview.getId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews (name, content, created_at, game_id) VALUES (:name, :content, :created_at, :game_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("content", this.content)
      .addParameter("created_at", this.created_at)
      .addParameter("game_id", this.game_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Review> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews";
      return con.createQuery(sql)
             .executeAndFetch(Review.class);
    }
  }

  public static Review find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews WHERE id = :id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Review.class);
    }
  }
}
