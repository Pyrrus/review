import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class PlatformTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Platform_correctlyInstantiates_true() {
    Platform testPlatform = new Platform("PS4");
    assertTrue(testPlatform instanceof Platform);
  }

  @Test
  public void getName_returnPlatformName_true() {
    Platform testPlatform = new Platform("PS4");
    assertEquals("PS4", testPlatform.getName());
  }

  @Test
  public void equals_FirstPlatformAndSecondPlatform_true() {
    Platform firstPlatform = new Platform("PS4");
    Platform secondPlatform = new Platform("PS4");
    assertTrue(firstPlatform.equals(secondPlatform));
  }

  @Test
  public void save_platformSavesToDatabase_true() {
    Platform testPlatform = new Platform("PS4");
    testPlatform.save();
    assertTrue(testPlatform.getId() > 0);
  }

  @Test
  public void all_returnsAllInstancesOfPlatform_true() {
    Platform testPlatform = new Platform("PS4");
    testPlatform.save();
    assertTrue(testPlatform.equals(Platform.all().get(0)));
  }

  @Test
  public void find_returnsPlatformWithSameId_true() {
    Platform testPlatform = new Platform("PS4");
    testPlatform.save();
    Platform test2Platform = new Platform("Xbox");
    test2Platform.save();
    Platform foundPlatform = Platform.find(test2Platform.getId());
    assertTrue(test2Platform.equals(foundPlatform));
  }

  @Test
  public void getGame_returnInstantancesOfGameforPlatform_true() {
    Platform testPlatform = new Platform("PS4");
    testPlatform.save();
    Game testGame = new Game("Bloodborne", "Horror 3rd person", "very hard and you will die alot!", "12-3-2014", testPlatform.getId());
    testGame.save();
    Game secondGame = new Game("F.E.A.R.", "Horror first person", "very hard and you will die alot!", "12-3-2014", testPlatform.getId());
    secondGame.save();

    Game[] games = new Game[] { testGame, secondGame };
    assertTrue(testPlatform.getGames().containsAll(Arrays.asList(games)));

  }
}
