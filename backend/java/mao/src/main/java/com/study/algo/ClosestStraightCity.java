package com.study.algo;

import java.util.*;

public class ClosestStraightCity {
    public static void main(String[] args) {
        String[] names = {"111", "222", "333", "444", "555"};
        int[] X = {100, 200, 300, 400, 500};
        int[] Y = {100, 200, 300, 400, 500};
        String[] qs = {"111", "222", "333", "444", "555"};
        System.out.println(Arrays.toString(getClosest(names, X, Y, qs)));
    }

    static String[] getClosest(String[] names, int[] x, int[] y, String[] qs) {
        int size = names.length;
        List<String> ans = new ArrayList<>();
        Coordinate[] coordinates = new Coordinate[size];
        Map<String, Coordinate> cities = new HashMap<>();
        Map<String, List<Distance>> result = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            coordinates[i] = new Coordinate(x[i], y[i], names[i]);
            cities.put(names[i], coordinates[i]);
        }
        Arrays.sort(coordinates, new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                if (o1.x != o2.x) {
                    return o1.x - o2.x;
                } else {
                    return o1.y - o2.y;
                }
            }
        });
        int minX = 0;
        String minXCity;
        for (String q : qs) {
            Coordinate c = cities.get(q);
            int index = bsByX(coordinates, c);
            int dist1 = Integer.MAX_VALUE, dist2 = Integer.MAX_VALUE;
            if (index + 1 < size && coordinates[index + 1].x == c.x) {
                dist1 = Math.abs(c.y - coordinates[index + 1].y);
            }
            if (index - 1 > 0 && coordinates[index - 1].x == c.x) {
                dist2 = Math.abs(c.y - coordinates[index - 1].y);
            }
            minX = Math.min(dist1, dist2);
            if (minX != Integer.MAX_VALUE) {
                minXCity = minX == dist1 ? coordinates[index + 1].name : coordinates[index - 1].name;
                result.computeIfAbsent(q, k -> new ArrayList<Distance>()).add(new Distance(minX, minXCity));
            } else {
                result.computeIfAbsent(q, k -> new ArrayList<Distance>());
            }
        }

        Arrays.sort(coordinates, new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                if (o1.x != o2.x) {
                    return o1.y - o2.y;
                } else {
                    return o1.x - o2.x;
                }
            }
        });
        int minY = 0;
        String minYCity;
        for (String q : qs) {
            Coordinate c = cities.get(q);
            int index = bsByY(coordinates, c);
            int dist1 = Integer.MAX_VALUE, dist2 = Integer.MAX_VALUE;
            if (index + 1 < size && coordinates[index + 1].y == c.y) {
                dist1 = Math.abs(c.x - coordinates[index + 1].x);
            }
            if (index - 1 > 0 && coordinates[index - 1].y == c.y) {
                dist2 = Math.abs(c.x - coordinates[index - 1].x);
            }
            minY = Math.min(dist1, dist2);
            if (minY != Integer.MAX_VALUE) {
                minYCity = minY == dist1 ? coordinates[index + 1].name : coordinates[index - 1].name;
                result.computeIfAbsent(q, k -> new ArrayList<Distance>()).add(new Distance(minY, minYCity));
            } else {
                result.computeIfAbsent(q, k -> new ArrayList<Distance>());
            }
        }
        result.values().forEach(v -> {
            if (v == null || v.size() == 0) {
                ans.add("none");
            } else if (v.size() == 1) {
                ans.add(v.get(0).name);
            } else {
                ans.add(v.get(0).dist < v.get(1).dist ? v.get(0).name : v.get(1).name);
            }
        });
        return ans.toArray(new String[0]);
    }

    static int bsByX(Coordinate[] coordinates, Coordinate c) {
        int left = 0, right = coordinates.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (coordinates[mid].x == c.x && coordinates[mid].y == c.y) {
                ans = mid;
                break;
            } else if (coordinates[mid].x < c.x) {
                left = mid + 1;
            } else if (coordinates[mid].x > c.x) {
                right = mid - 1;
            } else if (coordinates[mid].y < c.y) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    static int bsByY(Coordinate[] coordinates, Coordinate c) {
        int left = 0, right = coordinates.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (coordinates[mid].x == c.x && coordinates[mid].y == c.y) {
                ans = mid;
                break;
            } else if (coordinates[mid].y < c.y) {
                left = mid + 1;
            } else if (coordinates[mid].y > c.y) {
                right = mid - 1;
            } else if (coordinates[mid].x < c.x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    static class Coordinate {
        int x;
        int y;
        String name;

        public Coordinate(int x, int y, String name) {
            this.x = x;
            this.y = y;
            this.name = name;
        }
    }

    static class Distance {
        int dist;
        String name;

        public Distance(int dist, String name) {
            this.dist = dist;
            this.name = name;
        }
    }
}

