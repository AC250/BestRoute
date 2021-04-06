package main.node;

public class Location {
  private double sourceLatitude;
  private double sourceLongitude;

  public Location(double sourceLatitude, double sourceLongitude) {
    this.sourceLatitude = sourceLatitude;
    this.sourceLongitude = sourceLongitude;
  }

  private double getDistance(double destinationLatitude, double destinationLongitude) {

    // distance between latitudes and longitudes
    double latitudeDistance = Math.toRadians(destinationLatitude - sourceLatitude);
    double longitudeDistance = Math.toRadians(destinationLongitude - sourceLongitude);

    // convert to radians
    sourceLatitude = Math.toRadians(sourceLatitude);
    destinationLatitude = Math.toRadians(destinationLatitude);

    // Intermediate variable required for the math
    double intermediate =
        Math.pow(Math.sin(latitudeDistance / 2), 2)
            + Math.pow(Math.sin(longitudeDistance / 2), 2)
                * Math.cos(sourceLatitude)
                * Math.cos(destinationLatitude);
    // radians = 6371, formula requires it to multiply by 2
    return 12742 * Math.asin(Math.sqrt(intermediate));
  }

  public double getTimeToDestination(double destinationLatitude, double destinationLongitude) {
    // distance is returned in KM, speed of executive is 20Kmph
    // time = distance / speed
    return getDistance(destinationLatitude, destinationLongitude) / 20;
  }

  public double getSourceLatitude() {
    return sourceLatitude;
  }

  public double getSourceLongitude() {
    return sourceLongitude;
  }
}
