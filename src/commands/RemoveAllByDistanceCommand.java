package commands;

import exceptions.EmptyArgumentException;
import utilities.CollectionManager;

/**
 * Команда "remove_all_by_distance". Удаляет эл-ты с определенным кол-ом комнат
 */
public class RemoveAllByDistanceCommand extends AbstractCommand {
    CollectionManager collectionManager;
    public RemoveAllByDistanceCommand(CollectionManager collectionManager){

        super("remove_all_by_distance distance"," удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному");
        this.collectionManager=collectionManager;
    }
    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */

    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()) throw new EmptyArgumentException();
            float distance=Float.parseFloat(argument);
            int i = collectionManager.remove_all_by_distance(distance);
            System.out.println("\u001B[37m"+"\u001B[33m"+"Было успешно удалено "+i+" маршрутов с длинной "+distance+"\u001B[33m"+"\u001B[37m");
            return true;
        }
        catch (EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент(длинна пути)" );
        }catch (NumberFormatException e){
            System.err.println("Формат введенного аргумента неверен. Он должен быть числом.");
        }
        return false;
    }
}
