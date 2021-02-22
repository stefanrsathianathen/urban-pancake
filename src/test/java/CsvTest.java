import com.stefan.trip.CostCalculator;
import com.stefan.trip.Csv.CsvReader;
import com.stefan.trip.objects.Tap;
import com.stefan.trip.objects.Trip;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvTest {

  @Test
  public void testCsvRead()
  {
    List<Tap> actual = CsvReader.parse("csvReadTest.csv");
    int expected = 2;

    assertEquals(actual.size(), expected);

    assertEquals("5500005555555559", actual.get(0).pan);
    assertEquals("5500005555555560", actual.get(1).pan);
  }

  @Test
  public void testCsvDataToWrite()
  {
    List<Tap> actual = CsvReader.parse("csvReadTest.csv");
    CostCalculator calculator = new CostCalculator();

    List<Trip> trips = calculator.calculateCosts(actual);
    int expected = 2;
    assertEquals(expected, trips.size());
  }
}
