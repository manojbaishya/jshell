package javakit.data;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class PointRepository {
    private final int count;
    private final Faker faker;
    public PointRepository(int count) {
        this.count = count;
        this.faker = new Faker();
    }

    public List<Point> getPoints() {
        var points = new ArrayList<Point>();

        int maxNumberOfDecimals = 6;
        long min = -1000;
        long max = 1000;
        for (int i = 0; i < count; i++)
            points.add(new Point(faker.number().randomDouble(maxNumberOfDecimals, min, max), faker.number().randomDouble(maxNumberOfDecimals, min, max)));

        return points;
    }
}
