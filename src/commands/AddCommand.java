package commands;

import exceptions.IncorrectValueException;
import general.Route;
import utilities.CollectionManager;
import utilities.Creator;

import java.time.LocalDate;

/**
 * Команда "add". Добавляет новый элемент в коллекцию
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private Creator creator;

    public AddCommand(CollectionManager collectionManager, Creator creator) {
        super("add {element}", "добавить новый элемент в коллекцию");
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
            collectionManager.add(new Route(collectionManager.newId(), creator.newName(), creator.newCoordinates(), LocalDate.now(),
                    creator.newLocation("начальной локации"), creator.newLocation("конечной локации"), creator.newDistance()));
            System.out.println("\u001B[37m" + "\u001B[33m" + "Элемент успешно добавлен" + "\u001B[33m" + "\u001B[37m");
            return true;
        } catch (
                IncorrectValueException e) {
            System.err.println("У этой команды нет параметров! Необходимо ввести: add");
        }
        return false;
    }
}
