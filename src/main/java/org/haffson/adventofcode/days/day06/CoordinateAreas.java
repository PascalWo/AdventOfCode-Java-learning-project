package org.haffson.adventofcode.days.day06;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * Helper record for day 6.
 * Uses a List<Coordinates> to get a coordinate system.
 * Has some helper methods to calculate areas of coordinates.
 */
public final class CoordinateAreas {
    @Nonnull
    private final List<Coordinate> coordinates;
    @Nonnull
    private final String[][] coordinateSystem;


    public CoordinateAreas(@Nonnull final List<Coordinate> coordinates, @Nonnull final String[][] coordinateSystem) {
        requireNonNull(coordinates, "coordinates");
        requireNonNull(coordinateSystem, "coordinateSystem");
        this.coordinates = coordinates;
        this.coordinateSystem = coordinateSystem;
    }

    public CoordinateAreas(@Nonnull final List<Coordinate> coordinates) {
        this(coordinates, createCoordinateSystem(coordinates));
        requireNonNull(coordinates, "coordinates");
    }

    /**
     * Helper method for day 6.
     * Creates a coordinate system.
     * The length and height depends on the max values of the List<Coordinate>
     *
     * @return String[][] coordinate system.
     */
    @Nonnull
    private static String[][] createCoordinateSystem(@Nonnull final List<Coordinate> coordinates) {
        final int maxCoordinateX = coordinates.stream()
                .max(Comparator.comparingInt(Coordinate::x))
                .orElseThrow(NoSuchElementException::new)
                .x();
        final int maxCoordinateY = coordinates.stream()
                .max(Comparator.comparingInt(Coordinate::y)).
                orElseThrow(NoSuchElementException::new)
                .y();

        return new String[maxCoordinateX + 1][maxCoordinateY + 1];
    }

    /**
     * Helper method for day 6.
     * Finds the value of the coordinate which claims the largest area when growing.
     *
     * @return long max claimed area by a coordinate.
     */
    public long maxCoordinateAreaWithoutInfinites() {
        fillCoordinateSystem();
        final Set<String> infiniteCoords = findInfiniteCoordinates();
        final Map<String, Long> areaByCoords = getAreaByCoordinate();
        final Map<String, Long> areaByCoordsWithoutInfinites = removeInfiniteCoordsFromMap(areaByCoords, infiniteCoords);

        return findCoordinateWithBiggestArea(areaByCoordsWithoutInfinites);
    }

    /**
     * Helper method for day 6.
     * Iterates over the coordinate system.
     * Fills each entry with a coordinate id or "." when a spot is claimed by several coordinates
     */
    private void fillCoordinateSystem() {
        Arrays.stream(coordinateSystem).forEach(arrayOfXCoordinates ->
                IntStream.range(0, arrayOfXCoordinates.length)
                        .forEach(yCoordinate -> {
                            final int xCoordinate = Arrays.asList(coordinateSystem).indexOf(arrayOfXCoordinates);
                            final Coordinate toCompare = new Coordinate(xCoordinate, yCoordinate);

                            final List<Integer> nearestCoordinates = findNearestCoordinates(toCompare);
                            coordinateSystem[xCoordinate][yCoordinate] = givesCoordinateSystemInput(nearestCoordinates);
                        }));
    }

    /**
     * Helper method for day 6.
     * Needs a Coordinate and compares it to given coordinates.
     * Uses a helper method to get a List<Integer> of the closest coordinates.
     *
     * @return List<Integer> with coordinate ids.
     */
    @Nonnull
    private List<Integer> findNearestCoordinates(@Nonnull final Coordinate coordinateToCompare) {
        final Map<Integer, Integer> distancesByCoordinate = getDistanceByCoordinate(coordinateToCompare);

        return findShortestDistances(distancesByCoordinate);
    }

    /**
     * Helper method for day 6.
     * Needs a Coordinate and compares it to given coordinates.
     * Maps coordinate distance by compared coordinate
     *
     * @return Map<Integer, Integer> distanceByCoordinate.
     */
    @Nonnull
    private Map<Integer, Integer> getDistanceByCoordinate(@Nonnull final Coordinate coordinateToCompare) {
        final Map<Integer, Integer> distancesByCoordinate = new HashMap<>();
        coordinates.forEach(coordinate -> distancesByCoordinate
                .put(coordinates.indexOf(coordinate), coordinateToCompare.coordinateFieldDistance(coordinate)));

        return distancesByCoordinate;
    }

    /**
     * Helper method for day 6.
     * Needs a Map with key=coordinate ids and value=distance.
     * Lists the coordinate ids, which have the shortest distance.
     *
     * @return List<Integer> with coordinate ids.
     */
    @Nonnull
    private List<Integer> findShortestDistances(@Nonnull final Map<Integer, Integer> distancesByCoordinate) {
        final List<Integer> nearestCoordinates = new ArrayList<>();

        final int shortestDistance = distancesByCoordinate
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(NoSuchElementException::new)
                .getValue();

        distancesByCoordinate.forEach((comparedCoordinate, coordinateDistance) -> {
            if (coordinateDistance.equals(shortestDistance)) {
                nearestCoordinates.add(comparedCoordinate);
            }
        });

        return nearestCoordinates;
    }

    /**
     * Helper method for day 6.
     * Needs a List<Integer> with Coordinate ids.
     * Proves whether the list contains one or more values.
     * If the list just contains one value, it means the spot in the coordinate system is only claimed by one coordinate.
     * If the list contains two or more values, the spot is claimed by more coordinates.
     * Is it claimed by more coordinates it should be marked with a ".".
     *
     * @return String to fill the coordinate system.
     */
    @Nonnull
    private String givesCoordinateSystemInput(@Nonnull final List<Integer> nearestCoordinates) {
        if (nearestCoordinates.size() > 1) {
            return ".";
        } else {
            return String.valueOf(nearestCoordinates.get(0));
        }
    }

    /**
     * Helper method for day 6.
     * After a coordinate system is filled with coordinate ids it returns infinite coordinates.
     * Coordinates who grow till infinity.
     * To find those, this method irritates over all 4 borders and collects the coordinate ids to a Set.
     *
     * @return Set<String> with coordinate ids who grow till infinity.
     */
    @Nonnull
    private Set<String> findInfiniteCoordinates() {
        final Stream<String> topBorderCoordinates = Arrays.stream(coordinateSystem)
                .map(coordinate -> coordinate[0])
                .filter(coordinateSystemEntry -> !Objects.equals(coordinateSystemEntry, "."));

        final Stream<String> bottomBorderCoordinates = Arrays.stream(coordinateSystem)
                .map(coordinate -> coordinate[coordinateSystem[0].length - 1])
                .filter(coordinateSystemEntry -> !Objects.equals(coordinateSystemEntry, "."));

        final Stream<String> leftBorderCoordinates = Arrays.stream(coordinateSystem[0])
                .filter(coordinateSystemEntry -> !Objects.equals(coordinateSystemEntry, "."));

        final Stream<String> rightBorderCoordinates = Arrays.stream(coordinateSystem[coordinateSystem.length - 1],
                        0, coordinateSystem[0].length)
                .filter(coordinateSystemEntry -> !Objects.equals(coordinateSystemEntry, "."));

        final Stream<String> borderCoordinates = Stream.of(
                topBorderCoordinates, bottomBorderCoordinates, leftBorderCoordinates, rightBorderCoordinates).flatMap(coordinate -> coordinate);

        return borderCoordinates.collect(Collectors.toSet());
    }

    /**
     * Helper method for day 6.
     * Uses a coordinate system to create a Map with coordinate ids and claimed area.
     *
     * @return Map<String, Integer> AreaByCoordinate
     */
    @Nonnull
    private Map<String, Long> getAreaByCoordinate() {
        return Arrays.stream(coordinateSystem)
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
    }

    /**
     * Helper method for day 6.
     * Needs a Map of coordinate ids and claimed area + infinite coordinates.
     * Removes the infinitely growing coordinates from the map.
     *
     * @return Map<String, long> without infinite coordinates.
     */
    @Nonnull
    private Map<String, Long> removeInfiniteCoordsFromMap(@Nonnull final Map<String, Long> areaByPoint,
                                                          @Nonnull final Set<String> infiniteCoordinates) {
        infiniteCoordinates.forEach(areaByPoint.keySet()::remove);
        return areaByPoint;
    }

    /**
     * Helper method for day 6.
     * Needs a Map of coordinate ids and claimed area.
     * Finds the highest value in the map -> coordinate with max claimed area.
     *
     * @return long max claimed area by a coordinate.
     */
    private long findCoordinateWithBiggestArea(@Nonnull final Map<String, Long> areaByCoordinates) {
        return areaByCoordinates.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(NoSuchElementException::new)
                .getValue();
    }

    /**
     * Helper method for day 6 Part2.
     * Finds the size of the region which is claimed when each coordinate in this region has less than 10,000
     * distance to every other coordinate.
     *
     * @return long region size.
     */
    public long regionSizeOfCoordinatesBetweenDistance(final int distanceToCheck) {
        fillCoordinateSystemByDistance(distanceToCheck);
        final Map<String, Long> areaByString = getAreaByCoordinate();
        return getValueClaimedByCoordinateLessThanDistanceToCheck(areaByString);
    }

    /**
     * Helper method for day 6 Part2.
     * Iterates over the coordinate system.
     * Fills each entry with a "#" when the combined distance to each coordinate is less than 10,000.
     * Else fills the spot with ".".
     */
    private void fillCoordinateSystemByDistance(final int distanceToCheck) {
        Arrays.stream(coordinateSystem).forEach(arrayOfXCoordinates ->
                IntStream.range(0, arrayOfXCoordinates.length)
                        .forEach(yCoordinate -> {
                            final int xCoordinate = Arrays.asList(coordinateSystem).indexOf(arrayOfXCoordinates);
                            final Coordinate toCompare = new Coordinate(xCoordinate, yCoordinate);

                            final Map<Integer, Integer> distancesByCoordinate = getDistanceByCoordinate(toCompare);

                            coordinateSystem[xCoordinate][yCoordinate] = getClaimIdentification(distancesByCoordinate, distanceToCheck);

                        }));
    }

    /**
     * Helper method for day 6 Part2.
     * Needs a Map of coordinate index and claimed area.
     * If the combined distance to all other coordinates is less than 10,000 it returns "#".
     * Else it return ".".
     *
     * @return String with identification("#" or ".")
     */
    @Nonnull
    private String getClaimIdentification(@Nonnull final Map<Integer, Integer> distancesByCoordinate, final int distanceToCheck) {

        final int claimedArea = distancesByCoordinate.values().stream().mapToInt(claim -> claim).sum();

        if (claimedArea < distanceToCheck) {
            return "#";
        } else {
            return ".";
        }
    }

    /**
     * Helper method for day 6 Part2.
     * Needs a Map of coordinate ids and claimed area.
     * Return the value (claimed region) for coordinate id "#".
     * All coordinates in this region have a combined distance less than 10,000 to all other coordinates.
     *
     * @return long claimed region with distance less than 10,000.
     */
    private long getValueClaimedByCoordinateLessThanDistanceToCheck(final Map<String, Long> areaByString) {
        return areaByString.get("#");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CoordinateAreas that = (CoordinateAreas) o;
        return Objects.equals(coordinates, that.coordinates) && Arrays.deepEquals(coordinateSystem, that.coordinateSystem);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(coordinates);
        result = 31 * result + Arrays.deepHashCode(coordinateSystem);
        return result;
    }

    @Override
    public String toString() {
        return "CoordinateAreas{" +
                "coordinates=" + coordinates +
                ", coordinateSystem=" + Arrays.toString(coordinateSystem) +
                '}';
    }
}
