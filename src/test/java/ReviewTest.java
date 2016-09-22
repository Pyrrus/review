import java.util.Arrays;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;
import java.sql.Timestamp;

public class ReviewTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Review_correctlyInstantiates_true() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    Review testReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, 1);
    assertTrue(testReview instanceof Review);
  }

  @Test
  public void getTime_ReturnReviewTime_True() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    Review testReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, 1);
    String other = testReview.getTime();
    assertEquals(now, other);
  }

  @Test
  public void equals_FirstReviewIsEqualToSecondReview_true() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    Review firstReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, 1);
    Review secondReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, 1);
    assertTrue(firstReview.equals(secondReview));
  }

  @Test
  public void save_savesTestReviewToDatabase_true() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    Review testReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, 1);
    testReview.save();
    assertTrue(testReview.getId() > 0);
  }

  @Test
  public void all_saveTestReviewToDatabase_true() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    Review firstReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, 1);
    firstReview.save();
    assertTrue(firstReview.equals(Review.all().get(0)));
  }

  @Test
  public void find_returnsSameReviewId_true() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    Review firstReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, 1);
    firstReview.save();
    Review secondReview = new Review("Adam", "Bloodborne is very challenging. But, very rewarding.", now, 1);
    secondReview.save();
    Review savedReview = Review.find(secondReview.getId());
    assertTrue(secondReview.equals(savedReview));
  }

  @Test
  public void update_updatesReviewWithSameContent_true() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();

    String first = "BOB THE GEART";
    Review firstReview = new Review("Adam", first, now, 67);
    firstReview.save();
    utilDate = new java.util.Date(System.currentTimeMillis());
    created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    now = new java.sql.Timestamp((created_at).getTime()).toString();
    first = "test";
    firstReview.update(first, now);
    System.out.println(firstReview.getContent());
    assertEquals(first, firstReview.getContent());
  }

  @Test
  public void delete_deletesReview_true() {
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
    java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
    String now = new java.sql.Timestamp((created_at).getTime()).toString();
    String first = "BOB THE GEART";
    Review firstReview = new Review("Adam", first, now, 67);
    firstReview.save();
    int id = firstReview.getId();
    firstReview.delete();
    assertEquals(null, Review.find(id));
  }
}
