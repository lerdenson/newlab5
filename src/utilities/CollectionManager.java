package utilities;

import general.Route;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Управляет коллекцией
 */
//работа с коллекцией: создание нового id, добавление элемента, удаление и тд
public class CollectionManager {

    private LinkedHashSet<Route> routeCollection = new LinkedHashSet<>();

    private LocalDateTime lastInitTime;
    private FileManager fileManager;
    private Creator creator;

    public CollectionManager(FileManager fileManager, Creator creator) {
        this.fileManager = fileManager;
        this.creator=creator;
    }

    /**
     * Записывает коллекцию в файл
     */
    public void saveToFile(){
        fileManager.writeCollection(routeCollection);
    }

    /**
     * Читает коллекцию из файла
     */
    public void loadCollection() {
        routeCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * Представляет все элементы коллекции в виде строки
     * @return строковое представление коллекции
     */
    public String getStringElements(){
        String strElem="";
        if (routeCollection.isEmpty()) return "Коллекция пуста!!!";
        for(Route route: routeCollection){
            strElem += route.toString();
        }
        return strElem;


    }

    /**
     * Чистит коллекцию
     */

    public void clear(){
        routeCollection.clear();
    }

    /**
     * Определяет класс коллекции
     * @return имя класса коллекции
     */
    public String collectionType(){
        return routeCollection.getClass().getName();

    }

    /**
     * Определяет размер коллекции
     * @return размер коллекции
     */
    public int collectionSize(){
        return routeCollection.size();
    }
    public LocalDateTime getLastInitTime(){
        return lastInitTime;
    }

    /**
     * Генерирует ID
     * @return ID
     */
    public Integer newId(){
        if (routeCollection.isEmpty()) return 1;
        int lastId = 0;
        for (Route route: routeCollection) {
            lastId = Math.max(lastId, route.getId());
        }
        return lastId + 1;
    }

    /**
     * Заменяет элемент по ID
     * @param id ID
     * @param route route
     */
    public void update(Integer id, Route route){
        remove_by_id(id);
        routeCollection.add(route);
    }

    /**
     * Добавляет новый элемент в коллекцию
     * @param route route
     */
    public void add(Route route){
        routeCollection.add(route);
        sort();
    }

    /**
     * Удаляет элемент по его ID
     * @param id id
     */
    public boolean remove_by_id(Integer id){
        Iterator<Route> genCollectionIterator = routeCollection.iterator();
        boolean t = false;
        while (genCollectionIterator.hasNext()) {
            if(genCollectionIterator.next().getId() == id) {
                genCollectionIterator.remove();
                System.out.println("Элемент с id=" + id + " успешно удален");
                t = true;
            }
        }
        if(!t) System.out.println("Элемента с данным id не существует");
        sort();
        return t;
    }

    /**
     * подсчитывает количество маршрутов с длинной больше заданного
     * @param distance distance
     * @return колличество маршрутов
     */
    public int countGreaterThanDistance(Float distance) {
        int counter = 0;
        for (Route route: routeCollection) {
            if (route.getDistance() > distance) counter++;
        }
        return counter;
    }

    public void filterLessThanDistance(float distance) {
        for(Route route: routeCollection) {
            if(route.getDistance() < distance){
                System.out.println(route.toString());
            }
        }
    }

    /**
     * Удаляет все элемнеты с такой длинной пути
     * @param distance distance
     * @return количество удаленных элементов
     */
    public Integer remove_all_by_distance(float distance) {
        int counter = 0;
        Iterator<Route> genCollectionIterator = routeCollection.iterator();
        while (genCollectionIterator.hasNext()) {
            if (genCollectionIterator.next().getDistance() == distance) {
                genCollectionIterator.remove();
                counter++;
            }
        }
        sort();
        return counter;
    }

    /**
     * Удаляет все элемнеты больше заданного
     * @param route route
     */
    public int remove_greater(Route route) {
        int counter = 0;
        RouteComparator comparator = new RouteComparator();
        Iterator<Route> genCollectionIterator = routeCollection.iterator();
        while (genCollectionIterator.hasNext()) {
            if (comparator.compare(route, genCollectionIterator.next()) > 0) {
                genCollectionIterator.remove();
                counter++;
            }
        }
        sort();
        return counter;
    }

    /**
     * Удаляет все элемнеты меньше заданного
     * @param route route
     */
    public int remove_lower(Route route) {
        int counter = 0;
        RouteComparator comparator = new RouteComparator();
        Iterator<Route> genCollectionIterator = routeCollection.iterator();
        while (genCollectionIterator.hasNext()) {
            if (comparator.compare(route, genCollectionIterator.next()) < 0) {
                genCollectionIterator.remove();
                counter++;
            }
        }
        sort();
        return counter;
    }

    /**
     * Добавляет элемент в коллекцию, если он больше всех в коллекции
     * @param route route
     */
    public boolean isMaxRoute(Route route) {
        boolean t = true;
        RouteComparator comparator = new RouteComparator();
        for (Route routeInCollection: routeCollection) {
            if (comparator.compare(route, routeInCollection) < 0) t = false;
        }
        return t;
    }

    /**
     * сортирует коллекцию
     */
    public void sort() {
        Route[] routes = routeCollection.toArray(new Route[0]);
        RouteComparator comparator = new RouteComparator();

        Arrays.sort(routes, comparator);
        routeCollection.clear();
        for (Route route: routes) {
            routeCollection.add(route);
        }
    }

    /**
     * Найти элемент по заданному id
     * @param id id
     * @return route
     */
    public Route findById(int id) {
        for (Route route: routeCollection){
            if(route.getId() == id){
                return route;
            }
        }
        return null;
    }
}
