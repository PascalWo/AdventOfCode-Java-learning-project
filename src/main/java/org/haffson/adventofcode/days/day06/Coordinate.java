package org.haffson.adventofcode.days.day06;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Coordinate(int xCoordinate, int yCoordinate) {


    public static Coordinate of(final String input) {
        final String[] split1 = input.split(",");
        final String[] split2 = split1[1].split(" ");

        int xCoordinate = Integer.parseInt(split1[0]);
        int yCoordinate = Integer.parseInt(split2[1]);

        return new Coordinate(xCoordinate, yCoordinate);
    }

    public static List<Coordinate> of(final List<String> stringList) {
        return stringList.stream().map(Coordinate::of).toList();
    }

    public static int coordinateFieldDistance
            (Coordinate coordinateToCompare, Coordinate comparedCoordinate) {
        int xDistance = Math.abs(coordinateToCompare.xCoordinate - comparedCoordinate.xCoordinate);
        int yDistance = Math.abs(coordinateToCompare.yCoordinate - comparedCoordinate.yCoordinate);

        return xDistance + yDistance;
    }

    public record CoordinateAreas(List<Coordinate> coordinates, String[][] coordinateSystem) {
        public CoordinateAreas(List<Coordinate> coordinates) {
            this(coordinates, createCoordinateSystem(coordinates));
        }

        //    public String[][] coordinateSystem = createCoordinateSystem();

        public static String[][] createCoordinateSystem(List<Coordinate> coordinates) {
            int maxCoordX = coordinates.stream()
                    .max(Comparator.comparingInt(c -> c.xCoordinate))
                    .orElseThrow(NoSuchElementException::new)
                    .xCoordinate();
            int maxCoordY = coordinates.stream()
                    .max(Comparator.comparingInt(c -> c.yCoordinate)).
                    orElseThrow(NoSuchElementException::new)
                    .yCoordinate();

            return new String[maxCoordX + 1][maxCoordY + 1];
        }

        List<Integer> findCoordinatesWithShortestDistance(Coordinate toCompare) {
            Map<Integer, Integer> distancesByCoordinate = new HashMap<>();
            coordinates.forEach(coordinate -> distancesByCoordinate
                    .put(coordinates.indexOf(coordinate), coordinateFieldDistance(toCompare, coordinate)));

            int shortestDistance = distancesByCoordinate
                    .entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .orElseThrow(NoSuchElementException::new)
                    .getValue();

            List<Integer> nearestCoordinates = new ArrayList<>();

            distancesByCoordinate.forEach((comparedCoordinate, coordinateDistance) -> {
                if (coordinateDistance.equals(shortestDistance)) {
                    nearestCoordinates.add(comparedCoordinate);
                }
            });

            return nearestCoordinates;

        }

        public void fillCoordinateSystem() {
//        String[][] coordinateSystem = createCoordinateSystem();
            Arrays.stream(coordinateSystem).forEach(arrayOfXCoordinates ->
                    IntStream.range(0, arrayOfXCoordinates.length)
                            .forEach(yCoordinate -> {

                                int xCoordinate = Arrays.asList(coordinateSystem).indexOf(arrayOfXCoordinates);

                                Coordinate toCompare = new Coordinate(xCoordinate, yCoordinate);

                                List<Integer> nearestCoordinates = findCoordinatesWithShortestDistance(toCompare);

                                if (nearestCoordinates.size() > 1) {
                                    coordinateSystem[xCoordinate][yCoordinate] = ".";
                                } else {
                                    coordinateSystem[xCoordinate][yCoordinate] = String.valueOf(nearestCoordinates.get(0));
                                }

                            }));
        }


        public Set<String> findInfiniteCoordinates() {
//        String[][] coordinateSystem = fillCoordinateSystem();
            // Walk the outer edges, and if we find an ID, it is to be excluded
            // Top
            Set<String> infiniteCoordinates = Arrays.stream(coordinateSystem)
                    .map(strings -> strings[0])
                    .filter(gridElement -> !Objects.equals(gridElement, "."))
                    .collect(Collectors.toSet());

            // Bottom
            Arrays.stream(coordinateSystem)
                    .map(strings -> strings[coordinateSystem[0].length - 1])
                    .filter(gridElement -> !Objects.equals(gridElement, "."))
                    .forEach(infiniteCoordinates::add);

            // Left
            Arrays.stream(coordinateSystem[0])
                    .filter(gridElement -> !Objects.equals(gridElement, "."))
                    .forEach(infiniteCoordinates::add);

            // Right
            Arrays.stream(coordinateSystem[coordinateSystem.length - 1], 0, coordinateSystem[0].length)
                    .filter(gridElement -> !Objects.equals(gridElement, "."))
                    .forEach(infiniteCoordinates::add);

            System.out.println(infiniteCoordinates);
            return infiniteCoordinates;
        }

        public Map<String, Long> getAreaByCoordinate() {
//        String[][] coordinateSystem = fillCoordinateSystem();

            List<String> flattedCoordinateSystem = Arrays.stream(coordinateSystem)
                    .flatMap(Arrays::stream).toList();

            return flattedCoordinateSystem.stream()
                    .collect(Collectors.groupingBy(Function.identity(),
                            Collectors.counting()));
        }

        public Map<String, Long> removeInfiniteCoordsFromMap(Map<String, Long> areaByPoint,
                                                             Set<String> infiniteCoordinates) {
            infiniteCoordinates.forEach(areaByPoint.keySet()::remove);
            System.out.println(areaByPoint);
            return areaByPoint;
        }

        public long findCoordinateWithBiggestArea(Map<String, Long> areaByCoordinates) {
            System.out.println(areaByCoordinates.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow(NoSuchElementException::new).getValue());
            return areaByCoordinates.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .orElseThrow(NoSuchElementException::new)
                    .getValue();
        }

        public long biggestCoordAreaWithoutInfinites() {
            fillCoordinateSystem();
            Set<String> infiniteCoords = findInfiniteCoordinates();
            Map<String, Long> areaByCoords = getAreaByCoordinate();
            Map<String, Long> areaByCoordsWithoutInfinites = removeInfiniteCoordsFromMap(areaByCoords, infiniteCoords);

            return findCoordinateWithBiggestArea(areaByCoordsWithoutInfinites);
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
