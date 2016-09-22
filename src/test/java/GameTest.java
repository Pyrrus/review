import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class GameTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void Game_instantiatesCorrectly_true() {
    Game testGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    assertTrue(testGame instanceof Game);
  }

  @Test
  public void getName_returnsGameName_Bloodborne() {
    Game testGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    assertEquals("Bloodborne", testGame.getName());
  }

  @Test
  public void getType_returnsGameType_Bloodborne() {
    Game testGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    assertEquals("Horror 3rd person", testGame.getType());
  }

  @Test
  public void getDescription_returnsGameDescription_Bloodborne() {
    Game testGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    assertEquals("very hard and you will die alot!", testGame.getDescription());
  }

  @Test
  public void getYear_returnsGameYear_Bloodborne() {
    Game testGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    assertEquals("12-3-2014", testGame.getYear());
  }

  @Test
  public void getPlatformId_returnsGamePlatformId_Bloodborne() {
    Game testGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    assertEquals(1, testGame.getPlatformId());
  }

  @Test
  public void equals_firstGameEqualsSecondGame_true() {
    Game firstGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    Game secondGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    assertTrue(firstGame.equals(secondGame));
  }

  @Test
  public void all_returnsAllInstancesOfGame_true() {
    Game testGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    testGame.save();
    Game savedGame = Game.all().get(0);
    assertTrue(testGame.getId() == savedGame.getId());
  }

  @Test
  public void save_gameSaveToDatabase_true() {
    Game testGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    testGame.save();
    assertTrue(testGame.getId() > 0);
  }

  @Test
  public void find_returnGameSameID_true() {
    Game firstGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    Game secondGame = new Game("F.E.A.R.", "Horror first person", "very hard and you will die alot!", "12-3-2014", 6);
    firstGame.save();
    secondGame.save();
    Game findGame = Game.find(secondGame.getId());
    assertTrue(findGame.getId() == secondGame.getId());
  }

  @Test
  public void getReviews_returnsAllReviewsWithSameGameId_true() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    Game firstGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", 1);
    firstGame.save();
    Review firstReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, firstGame.getId());
    firstReview.save();
    Review secondReview = new Review("bob", "Bloodborne is very easy.", now, firstGame.getId());
    secondReview.save();

    Review[] reviews = new Review[] { firstReview, secondReview };
    assertTrue(firstGame.getReviews().containsAll(Arrays.asList(reviews)));
  }

  @Test
  public void update_updatesGame_true() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    Game firstGame = new Game("Bloodborn", "Horror 3rd person", "very hard and you will die alot!", "12-4-2014", 1);
    firstGame.save();
    firstGame.update("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-4-2014");
    assertEquals("Bloodborne", firstGame.getName());
  }

  @Test
  public void delete_deletesGame_tre() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    Game firstGame = new Game("Bloodborn", "Horror 3rd person", "very hard and you will die alot!", "12-4-2014", 1);
    firstGame.save();
    int id = firstGame.getId();
    Review firstReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, firstGame.getId());
    firstReview.save();
    Review secondReview = new Review("bob", "Bloodborne is very easy.", now, firstGame.getId());
    secondReview.save();
    firstGame.delete();
    assertEquals(null, Game.find(id));
  }
}
