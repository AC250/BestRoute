package main.utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import main.node.Location;

public class DistanceUtil {
  public static List<List<Double>> getLocationTimeMatrix(final Location... locations) {
    return Arrays.stream(locations)
        .map(
            location1 ->
                Arrays.stream(locations)
                    .map(
                        location2 ->
                            location2.getTimeToDestination(
                                location1.getSourceLatitude(), location1.getSourceLongitude()))
                    .collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

  public static double getTimeForPathR1R2C1C2(
      final double cookTimeR1,
      final double cookTimeR2,
      final double timeToReachR1,
      final List<List<Double>> timeMatrix) {
    double totalTime = 0;
    totalTime += Math.max(timeToReachR1, cookTimeR1);
    if (totalTime + timeMatrix.get(1).get(0) < cookTimeR2) {
      totalTime += cookTimeR2;
    } else {
      totalTime += timeMatrix.get(1).get(0);
    }
    totalTime += timeMatrix.get(1).get(2);
    totalTime += timeMatrix.get(2).get(3);
    return totalTime;
  }

  public static double getTimeForPathR1R2C2C1(
      final double cookTimeR1,
      final double cookTimeR2,
      final double timeToReachR1,
      final List<List<Double>> timeMatrix) {
    return getTimeForPathR1R2C1C2(cookTimeR1, cookTimeR2, timeToReachR1, timeMatrix)
        - timeMatrix.get(1).get(2)
        + timeMatrix.get(1).get(3);
  }

  public static double getTimeForPathR2R1C1C2(
      final double cookTimeR1,
      final double cookTimeR2,
      final double timeToReachR2,
      final List<List<Double>> timeMatrix) {
    double totalTime = 0;
    totalTime += Math.max(timeToReachR2, cookTimeR2);
    if (totalTime + timeMatrix.get(1).get(0) < cookTimeR1) {
      totalTime += cookTimeR2;
    } else {
      totalTime += timeMatrix.get(1).get(0);
    }
    totalTime += timeMatrix.get(2).get(0);
    totalTime += timeMatrix.get(2).get(3);
    return totalTime;
  }

  public static double getTimeForPathR2R1C2C1(
      final double cookTimeR1,
      final double cookTimeR2,
      final double timeToReachR2,
      final List<List<Double>> timeMatrix) {
    return (getTimeForPathR1R2C2C1(cookTimeR1, cookTimeR2, timeToReachR2, timeMatrix)
        - timeMatrix.get(2).get(0)
        + timeMatrix.get(1).get(3));
  }

  public static double getTimeForPathR1C1R2C2(
      final double cookTimeR1,
      final double cookTimeR2,
      final double timeToReachR1,
      final List<List<Double>> timeMatrix) {
    double totalTime = 0;
    totalTime += Math.max(timeToReachR1, cookTimeR1);
    totalTime += timeMatrix.get(2).get(0);
    if (totalTime + timeMatrix.get(2).get(1) < cookTimeR2) {
      totalTime += cookTimeR2;
    } else {
      totalTime += timeMatrix.get(2).get(1);
    }
    totalTime += timeMatrix.get(1).get(3);
    return totalTime;
  }

  public static double getTimeForPathR2C2R1C1(
      final double cookTimeR1,
      final double cookTimeR2,
      final double timeToReachR2,
      final List<List<Double>> timeMatrix) {
    double totalTime = 0;
    totalTime += Math.max(timeToReachR2, cookTimeR2);
    totalTime += timeMatrix.get(1).get(3);
    if (totalTime + timeMatrix.get(3).get(0) < cookTimeR1) {
      totalTime += cookTimeR1;
    } else {
      totalTime += timeMatrix.get(3).get(0);
    }
    totalTime += timeMatrix.get(2).get(0);
    return totalTime;
  }
}
