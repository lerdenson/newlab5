package commands;

import exceptions.EmptyArgumentException;
import general.Route;
import utilities.CollectionManager;
import utilities.Creator;

import java.time.LocalDate;

/**
 * Класс команды "update". Заменяет элемент по ключу
 */
public class UpdateCommand extends AbstractCommand {
    Creator creator;
    CollectionManager collectionManager;
    public UpdateCommand(CollectionManager collectionManager,Creator creator){
        super("update id {element}","обновить значение элемента коллекции, id которого равен заданному");
        this.creator=creator;
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
            Integer id =Integer.parseInt(argument);
            collectionManager.update(id, new Route(id, creator.newName(), creator.newCoordinates(), LocalDate.now(),
                    creator.newLocation("начальной локации"), creator.newLocation("конечной локации"), creator.newDistance()));
            return true;
        }catch (EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент(ID для удаления элементов)" );
        }catch (NumberFormatException e){
            System.err.println("Формат введенного аргумента неверен . Он должен быть целым числом.");
        } catch (NullPointerException e){
            System.err.println("Элемента с таким id не существует");
        }
        return false;}
}
