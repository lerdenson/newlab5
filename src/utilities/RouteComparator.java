package utilities;

import general.Route;

import java.util.Comparator;

/**
 * Класс для сравнения route. Сравнивает разницы между прямым расстоянием и длинной пути
 * Чем больше разница, тем меньше route
 */
public class RouteComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        return Double.compare(o1.getDistance(), o2.getDistance());
    }
}