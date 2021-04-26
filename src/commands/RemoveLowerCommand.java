package commands;

import exceptions.EmptyArgumentException;
import utilities.CollectionManager;

/**
 * Команда "remove_lower id". Удаляет эл-ты меньше элемента с данным id
 */
public class RemoveLowerCommand extends AbstractCommand {
    CollectionManager collectionManager;
    public RemoveLowerCommand(CollectionManager collectionManager){
        super("remove_lower id","удалить из коллекции все элементы, которые меньше, чем заданный");
        this.collectionManager=collectionManager;
    }
    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */
    public boolean execute(String argument){
        try{
            if (argument.isEmpty()) throw new EmptyArgumentException();
            Integer id = Integer.parseInt(argument);
            if(collectionManager.findById(id) == null) throw new NullPointerException();
            int i=collectionManager.remove_lower(collectionManager.findById(id));
            System.out.println("\u001B[37m"+"\u001B[33m"+"Было удалено "+i+" маршрутов меньше заданного" + "\u001B[33m"+"\u001B[37m");
            return true;
        }catch (EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент(id для поиска элемента)" );
        }catch (NumberFormatException e){
            System.err.println("Формат введенного аргумента неверен. Он должен быть целым числом.");
        }catch (NullPointerException e) {
            System.err.println("Маршрут с данным id не найден");
        }
        return false;
    }
}
