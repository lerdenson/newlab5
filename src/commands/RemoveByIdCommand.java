package commands;

import exceptions.EmptyArgumentException;
import utilities.CollectionManager;

/**
 * Команда "remove_key". Удаляет эл-т по ключу
 */
public class RemoveByIdCommand extends AbstractCommand {
    CollectionManager collectionManager;
    public RemoveByIdCommand(CollectionManager collectionManager){
        super("remove_by_id id","удалить элемент из коллекции по его id");
        this.collectionManager=collectionManager;

    }
    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */
    public boolean execute(String argument){// проверить ключ на наличие
        try{
            if (argument.isEmpty()) throw new EmptyArgumentException();
            Integer id = Integer.parseInt(argument);
            boolean elementRemoved = collectionManager.remove_by_id(id);
            if (elementRemoved) return true;
        }catch (EmptyArgumentException e) {
                System.err.println("У этой команды должен быть аргумент(ключ для удаления элемента)" );
        }catch (NumberFormatException e){
                System.err.println("Формат введенного аргумента неверен. Он должен быть целым.");
        }
        return false;
    }
}
