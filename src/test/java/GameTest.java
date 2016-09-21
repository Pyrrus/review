import org.junit.*;
import static org.junit.Assert.*;

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
}
