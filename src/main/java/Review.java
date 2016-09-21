import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review {
  private int id;
  private String name;
  private String content;
  private LocalDateTime created_at;
  private int game_id;

  public Review(String name, String content, int game_id) {
    this.name = name;
    this.content = content;
    this.created_at = LocalDateTime.now();
    this.game_id = game_id;
  }

  public String getTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return created_at.format(formatter);
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
}
