import java.util.Arrays;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.format.DateTimeFormatter;

public class ReviewTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Review_correctlyInstantiates_true() {
    Review testReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", 1);
    assertTrue(testReview instanceof Review);
  }

  // @Test
  // public void getTime_ReturnReviewTime_True() {
  //   Review testReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", 1);
  //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
  //   String now = LocalDateTime.now().format(formatter);
  //   String other = testReview.getTime();
  //   assertEquals(now.equals(other));
  // }
}
