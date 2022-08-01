package org.haffson.adventofcode.days.day06;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Helper record for day 6.
 * Is used to work better with the string inputs.
 * Coordinates have an x and y value.
 * Also, you can compare two coordinates with each other.
 */
public record Coordinate(int xCoordinate, int yCoordinate) {

    @Nonnull
    public static Coordinate of(@Nonnull final String input) {
        final String[] split1 = input.split(",");
        final String[] split2 = split1[1].split(" ");

        final int xCoordinate = Integer.parseInt(split1[0]);
        final int yCoordinate = Integer.parseInt(split2[1]);

        return new Coordinate(xCoordinate, yCoordinate);
    }

    /**
     * Helper method for day 6.
     * Gives a list<Coordinate> of a string-list.
     *
     * @return List<Coordinate>
     */
    @Nonnull
    public static List<Coordinate> of(@Nonnull final List<String> stringList) {
        return stringList.stream().map(Coordinate::of).toList();
    }

    /**
     * Helper method for day 6.
     * Compares the distance of two coordinates.
     *
     * @return int of how many fields are between two coordinates.
     */
    public static int coordinateFieldDistance(@Nonnull final Coordinate coordinateToCompare,
                                              @Nonnull final Coordinate comparedCoordinate) {
        int xDistance = Math.abs(coordinateToCompare.xCoordinate - comparedCoordinate.xCoordinate);
        int yDistance = Math.abs(coordinateToCompare.yCoordinate - comparedCoordinate.yCoordinate);

        return xDistance + yDistance;
    }

    /**
     * Helper record for day 6.
     * Uses a List<Coordinates> to get a coordinate system.
     * Has some helper methods to calculate areas of coordinates.
     */
    public record CoordinateAreas(@Nonnull List<Coordinate> coordinates, @Nonnull String[][] coordinateSystem) {

        public CoordinateAreas(@Nonnull List<Coordinate> coordinates) {
            this(coordinates, createCoordinateSystem(coordinates));
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
            int maxCoordinateX = coordinates.stream()
                    .max(Comparator.comparingInt(c -> c.xCoordinate))
                    .orElseThrow(NoSuchElementException::new)
                    .xCoordinate();
            int maxCoordinateY = coordinates.stream()
                    .max(Comparator.comparingInt(c -> c.yCoordinate)).
                    orElseThrow(NoSuchElementException::new)
                    .yCoordinate();

            return new String[maxCoordinateX + 1][maxCoordinateY + 1];
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
            List<Integer> nearestCoordinates = new ArrayList<>();

            int shortestDistance = distancesByCoordinate
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
         * Needs a Coordinate and compares it to given coordinates.
         * Uses a helper method to get a List<Integer> of the closest coordinates.
         *
         * @return List<Integer> with coordinate ids.
         */
        @Nonnull
        private List<Integer> findNearestCoordinates(@Nonnull final Coordinate coordinateToCompare) {
            Map<Integer, Integer> distancesByCoordinate = getDistanceByCoordinate(coordinateToCompare);

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
            Map<Integer, Integer> distancesByCoordinate = new HashMap<>();
            coordinates.forEach(coordinate -> distancesByCoordinate
                    .put(coordinates.indexOf(coordinate), coordinateFieldDistance(coordinateToCompare, coordinate)));

            return distancesByCoordinate;
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
         * Iterates over the coordinate system.
         * Fills each entry with a coordinate id or "." when a spot is claimed by several coordinates
         */
        private void fillCoordinateSystem() {
            Arrays.stream(coordinateSystem).forEach(arrayOfXCoordinates ->
                    IntStream.range(0, arrayOfXCoordinates.length)
                            .forEach(yCoordinate -> {
                                int xCoordinate = Arrays.asList(coordinateSystem).indexOf(arrayOfXCoordinates);
                                Coordinate toCompare = new Coordinate(xCoordinate, yCoordinate);

                                List<Integer> nearestCoordinates = findNearestCoordinates(toCompare);
                                coordinateSystem[xCoordinate][yCoordinate] = givesCoordinateSystemInput(nearestCoordinates);
                            }));
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
            Set<String> infiniteCoordinates = Arrays.stream(coordinateSystem)
                    .map(coordinates -> coordinates[0])
                    .filter(coordinateSystem -> !Objects.equals(coordinateSystem, "."))
                    .collect(Collectors.toSet());

            Arrays.stream(coordinateSystem)
                    .map(coordinates -> coordinates[coordinateSystem[0].length - 1])
                    .filter(coordinateSystem -> !Objects.equals(coordinateSystem, "."))
                    .forEach(infiniteCoordinates::add);

            Arrays.stream(coordinateSystem[0])
                    .filter(coordinateSystem -> !Objects.equals(coordinateSystem, "."))
                    .forEach(infiniteCoordinates::add);

            Arrays.stream(coordinateSystem[coordinateSystem.length - 1], 0, coordinateSystem[0].length)
                    .filter(coordinateSystem -> !Objects.equals(coordinateSystem, "."))
                    .forEach(infiniteCoordinates::add);

            return infiniteCoordinates;
        }

        /**
         * Helper method for day 6.
         * Uses a coordinate system to create a Map with coordinate ids and claimed area.
         *
         * @return Map<String, Integer> AreaByCoordinate
         */
        @Nonnull
        private Map<String, Long> getAreaByCoordinate() {
            List<String> flattedCoordinateSystem = Arrays.stream(coordinateSystem)
                    .flatMap(Arrays::stream).toList();

            return flattedCoordinateSystem.stream()
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
         * Helper method for day 6.
         * Finds the value of the coordinate which claims the largest area when growing.
         *
         * @return long max claimed area by a coordinate.
         */
        public long maxCoordinateAreaWithoutInfinites() {
            fillCoordinateSystem();
            Set<String> infiniteCoords = findInfiniteCoordinates();
            Map<String, Long> areaByCoords = getAreaByCoordinate();
            Map<String, Long> areaByCoordsWithoutInfinites = removeInfiniteCoordsFromMap(areaByCoords, infiniteCoords);

            return findCoordinateWithBiggestArea(areaByCoordsWithoutInfinites);
        }
//        ----------------------------------------------------------------------------------------------------

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
                                int xCoordinate = Arrays.asList(coordinateSystem).indexOf(arrayOfXCoordinates);
                                Coordinate toCompare = new Coordinate(xCoordinate, yCoordinate);

                                Map<Integer, Integer> distancesByCoordinate = getDistanceByCoordinate(toCompare);

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

            int claimedArea = distancesByCoordinate.values().stream().mapToInt(claim -> claim).sum();

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
        private long getValueClaimedByCoordinateLessThanDistanceToCheck(Map<String, Long> areaByString) {
            return areaByString.get("#");
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
            Map<String, Long> areaByString = getAreaByCoordinate();
            return getValueClaimedByCoordinateLessThanDistanceToCheck(areaByString);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CoordinateAreas that = (CoordinateAreas) o;
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
}
