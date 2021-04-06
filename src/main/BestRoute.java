package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import main.node.Location;
import main.utility.DistanceUtil;

public class BestRoute {
  public static void main(String[] args) {
    Logger logger = Logger.getLogger(BestRoute.class.getName());
    // location requires : sourceLatitude, sourceLongitude
    final Location aman = new Location(12.944213, 77.622064);
    final Location restaurant1 = new Location(12.946592, 77.621107);
    final Location restaurant2 = new Location(12.928949, 77.627780);
    final Location customer1 = new Location(12.930208, 77.616507);
    final Location customer2 = new Location(12.930975, 77.632734);
    final double cookTimeRestaurant1 = Math.random();
    final double cookTimeRestaurant2 = Math.random();
    final List<List<Double>> locationTimeMatrix =
        DistanceUtil.getLocationTimeMatrix(restaurant1, restaurant2, customer1, customer2);
    double timeToReachRestaurant1 =
        aman.getTimeToDestination(
            restaurant1.getSourceLatitude(), restaurant1.getSourceLongitude());
    double timeToReachRestaurant2 =
        aman.getTimeToDestination(
            restaurant2.getSourceLatitude(), restaurant2.getSourceLongitude());
    Map<String, Double> totalPathTimes = new HashMap<>();
    // Map for all possible paths
    totalPathTimes.put(
        "R1->R2->C1->C2",
        DistanceUtil.getTimeForPathR1R2C1C2(
            cookTimeRestaurant1, cookTimeRestaurant2, timeToReachRestaurant1, locationTimeMatrix));
    totalPathTimes.put(
        "R1->R2->C2->C1",
        DistanceUtil.getTimeForPathR1R2C2C1(
            cookTimeRestaurant1, cookTimeRestaurant2, timeToReachRestaurant1, locationTimeMatrix));
    totalPathTimes.put(
        "R2->R1->C1->C2",
        DistanceUtil.getTimeForPathR2R1C1C2(
            cookTimeRestaurant1, cookTimeRestaurant2, timeToReachRestaurant2, locationTimeMatrix));
    totalPathTimes.put(
        "R2->R1->C2->C1",
        DistanceUtil.getTimeForPathR2R1C2C1(
            cookTimeRestaurant1, cookTimeRestaurant2, timeToReachRestaurant2, locationTimeMatrix));
    totalPathTimes.put(
        "R1->C1->R2->C2",
        DistanceUtil.getTimeForPathR1C1R2C2(
            cookTimeRestaurant1, cookTimeRestaurant2, timeToReachRestaurant1, locationTimeMatrix));
    totalPathTimes.put(
        "R2->C2->R1->C1",
        DistanceUtil.getTimeForPathR2C2R1C1(
            cookTimeRestaurant1, cookTimeRestaurant2, timeToReachRestaurant2, locationTimeMatrix));

    final double[] leastTime = {Double.MAX_VALUE};
    final String[] pathFollowed = new String[1];
    totalPathTimes.forEach(
        (path, time) -> {
          if (time < leastTime[0]) {
            leastTime[0] = time;
            pathFollowed[0] = path;
          }
        });
    logger.info("Aman should follow the path: Aman->" + pathFollowed[0]);
  }
}
