package jshell.data;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PointService {
    public double euclideanDistance(Point p1, Point p2) {
        return sqrt(pow(p1.x() - p2.x(), 2) + pow(p1.y() - p2.y(), 2));
    }
}
