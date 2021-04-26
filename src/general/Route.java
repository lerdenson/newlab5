package general;

import java.time.LocalDate;

public class Route {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле не может быть null
    private Location to; //Поле может быть null
    private Float distance; //Поле не может быть null, Значение поля должно быть больше 1


    public Route(Integer id, String name, Coordinates coordinates, LocalDate creationDate, Location from, Location to, Float distance) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }


    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Float getDistance() {
        return distance;
    }


    /**
     * Перевод маршрута в вид строки
     *
     * @return string
     */
    @Override
    public String toString() {
        return "\nId: " + id.toString() + "\nНазвание: " + name + "\nКоординаты: x=" + coordinates.getX() +
                "; y=" + coordinates.getY() + "\nВремя создания: " + creationDate + "\nОткуда " + from.toString()
                + "\nКуда " + to.toString() + "\n" + "Расстояние: " + distance + "\n";
    }
}
