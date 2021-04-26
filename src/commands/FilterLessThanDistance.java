package commands;

import exceptions.EmptyArgumentException;
import utilities.CollectionManager;

/**
 * Команда, выводящая элементы, значение поля distance которых меньше заданного
 */
public class FilterLessThanDistance extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterLessThanDistance(CollectionManager collectionManager) {
        super("filter_less_than_distance distance", "вывести элементы, значение поля distance которых меньше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new EmptyArgumentException();
            Float distance = Float.parseFloat(argument);
            collectionManager.filterLessThanDistance(distance);
            return true;
        } catch (EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент distance");
        } catch (NumberFormatException e) {
            System.err.println("Формат введенного аргумента неверен . Он должен быть числом.");
        }
        return false;
    }
}
