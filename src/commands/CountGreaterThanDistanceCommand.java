package commands;

import exceptions.EmptyArgumentException;
import utilities.CollectionManager;

/**
 * Класс для команды "count_greater_than_distance"
 */
public class CountGreaterThanDistanceCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public CountGreaterThanDistanceCommand(CollectionManager collectionManager) {
        super("count_greater_than_distance distance", "вывести количество элементов, значение поля distance которых больше заданного");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполнение команды
     *
     * @param argument аргумент
     * @return состояние выполнения команды
     */
    @Override
    public boolean execute(String argument) {
        int count = 0;
        try {
            if (argument.isEmpty()) throw new EmptyArgumentException();
            count = collectionManager.countGreaterThanDistance(Float.parseFloat(argument));
            System.out.println(count + "\u001B[37m" + "\u001B[33m" + "-столько элементов имеет значение furnish болше заданного" + "\u001B[33m" + "\u001B[37m");
            return true;
        } catch (EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент(значение distance)");
        } catch (IllegalArgumentException e) {
            System.err.println("Такого значения нет в distance");
        }
        return false;
    }
}