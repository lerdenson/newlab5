package commands;

import java.util.Objects;

/**
 * Абстрактный класс для команд, содержит методы объекта,имя и описание
 */
public abstract class AbstractCommand {
    private String name;
    private String description;
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return имя команды
     */
    public String getName(){
        return name;
    }
    /**
     * @return Описание еоманды
     */
    public String getDescription(){
        return description;
    }
    public abstract boolean execute(String argument);
    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
    @Override
    public String toString() {
        return name + " (" + description + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.name) && description.equals(other.description);
    }
}
