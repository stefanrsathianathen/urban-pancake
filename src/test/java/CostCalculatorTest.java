import com.stefan.trip.CostCalculator;

import com.stefan.trip.Csv.CsvReader;
import com.stefan.trip.objects.Tap;
import com.stefan.trip.objects.Trip;
import com.stefan.trip.objects.TripStatus;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CostCalculatorTest
{
  private CostCalculator costCalculator =  new CostCalculator();;
  private List<Tap> tapList;

  @Test
  public void testTripStatus() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
  {
    tapList = CsvReader.parse("calculatorTest1.csv");

    Method method = null;
    method = CostCalculator.class.getDeclaredMethod("tripStatus", Tap.class, Tap.class);
    method.setAccessible(true);

    TripStatus actualStatus = (TripStatus) method.invoke(costCalculator, tapList.get(0), tapList.get(1));
    TripStatus expectedStatus = TripStatus.COMPLETED;
    assertEquals(expectedStatus, actualStatus);
  }

  @Test
  public void testTripStatus1() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
  {
    tapList = CsvReader.parse("calculatorTest1.csv");

    Method method = null;
    method = CostCalculator.class.getDeclaredMethod("tripStatus", Tap.class, Tap.class);
    method.setAccessible(true);

    TripStatus actualStatus = (TripStatus) method.invoke(costCalculator, tapList.get(0), null);
    TripStatus expectedStatus = TripStatus.INCOMPLETE;
    assertEquals(expectedStatus, actualStatus);
  }

  @Test
  public void testTripStatus2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
  {
    tapList = CsvReader.parse("calculatorTest2.csv");

    Method method = null;
    method = CostCalculator.class.getDeclaredMethod("tripStatus", Tap.class, Tap.class);
    method.setAccessible(true);

    TripStatus actualStatus = (TripStatus) method.invoke(costCalculator, tapList.get(0), tapList.get(1));
    TripStatus expectedStatus = TripStatus.CANCELLED;
    assertEquals(expectedStatus, actualStatus);
  }

  @Test
  public void testGetPrice() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
  {
    tapList = CsvReader.parse("calculatorTest1.csv");

    Method method = null;
    method = CostCalculator.class.getDeclaredMethod("getPrice", String.class, String.class);
    method.setAccessible(true);

    Double actualPrice = (Double) method.invoke(costCalculator, tapList.get(0).stopId, tapList.get(1).stopId);
    Double expectedPrice = 3.25;
    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  public void testGetPrice1() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
  {
    tapList = CsvReader.parse("calculatorTest2.csv");

    Method method = null;
    method = CostCalculator.class.getDeclaredMethod("getPrice", String.class, String.class);
    method.setAccessible(true);

    Double actualPrice = (Double) method.invoke(costCalculator, tapList.get(0).stopId, tapList.get(1).stopId);
    Double expectedPrice = null;
    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  public void testGetPrice2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
  {
    tapList = CsvReader.parse("calculatorTest2.csv");

    Method method = null;
    method = CostCalculator.class.getDeclaredMethod("getPrice", String.class, String.class);
    method.setAccessible(true);

    Double actualPrice = (Double) method.invoke(costCalculator, tapList.get(0).stopId, "MAX");
    Double expectedPrice = 7.30;
    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  public void testCalculateUserTripCosts() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    tapList = CsvReader.parse("calculatorTest1.csv");

    Method method = null;
    method = CostCalculator.class.getDeclaredMethod("calculateUserTripCosts", List.class);
    method.setAccessible(true);

    List<Trip> actualTrip = (List<Trip>) method.invoke(costCalculator, tapList);
    int expectedTrip = 1;
    assertEquals(expectedTrip, actualTrip.size());
  }

  @Test
  public void testCalculateUserTripCosts1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    tapList = CsvReader.parse("calculatorTest2.csv");

    Method method = null;
    method = CostCalculator.class.getDeclaredMethod("calculateUserTripCosts", List.class);
    method.setAccessible(true);

    List<Trip> actualTrip = (List<Trip>) method.invoke(costCalculator, tapList);
    int expectedTrip = 1;
    TripStatus expectedStatus = TripStatus.CANCELLED;
    assertEquals(expectedTrip, actualTrip.size());
    assertEquals(expectedStatus, actualTrip.get(0).status);
  }
}
