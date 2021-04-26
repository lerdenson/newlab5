package commands;

import exceptions.IncorrectValueException;
import general.Route;
import utilities.CollectionManager;
import utilities.Creator;

import java.time.LocalDate;

/**
 * Команда "add_if_max". Добавляет новый элемент в коллекцию
 */
public class AddIfMaxCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private Creator creator;

    public AddIfMaxCommand(CollectionManager collectionManager, Creator creator) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.creator = creator;
    }

    /**
     * Выполнение команды
     *
     * @return состояние выполнения команды
     */

    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new IncorrectValueException();
            Route route = new Route(collectionManager.newId(), creator.newName(), creator.newCoordinates(), LocalDate.now(),
                    creator.newLocation("начальной локации"), creator.newLocation("конечной локации"), creator.newDistance());
            if (collectionManager.isMaxRoute(route)) {
                collectionManager.add(route);
                System.out.println("\u001B[37m" + "\u001B[33m" + "Элемент успешно добавлен" + "\u001B[33m" + "\u001B[37m");
            } else
                System.out.println("\u001B[37m" + "\u001B[33m" + "Элемент не является максимальным" + "\u001B[33m" + "\u001B[37m");
            return true;
        } catch (
                IncorrectValueException e) {
            System.err.println("У этой команды нет параметров! Необходимо ввести: add_if_max");
        }
        return false;
    }
}

