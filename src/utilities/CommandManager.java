package utilities;

import commands.AbstractCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для запуска команд
 */
public class CommandManager {
    private final int maxCommandSize = 14;

    private List<AbstractCommand> commands = new ArrayList<>();//массив с командами
    private AbstractCommand helpCommand;
    private AbstractCommand infoCommand;
    private AbstractCommand showCommand;
    private AbstractCommand addCommand;
    private AbstractCommand updateIdCommand;
    private AbstractCommand filterLessThanDistanceCommand;
    private AbstractCommand executeScriptCommand;
    private AbstractCommand saveCommand;
    private AbstractCommand clearCommand;
    private AbstractCommand exitCommand;
    private AbstractCommand removeGreaterCommand;
    private AbstractCommand removeLowerCommand;
    private AbstractCommand removeByIdCommand;
    private AbstractCommand removeAllByDistanceCommand;
    private AbstractCommand countGreaterThanDistanceCommand;
    private AbstractCommand addIfMaxCommand;

    public CommandManager(AbstractCommand helpCommand, AbstractCommand infoCommand, AbstractCommand showCommand, AbstractCommand addCommand, AbstractCommand updateIdCommand,
                          AbstractCommand filterLessThanDistanceCommand, AbstractCommand saveCommand, AbstractCommand clearCommand, AbstractCommand executeScriptCommand, AbstractCommand exitCommand,
                          AbstractCommand removeGreaterCommand, AbstractCommand removeLowerCommand, AbstractCommand removeByIdCommand, AbstractCommand removeAllByDistanceCommand,
                          AbstractCommand countGreaterThanDistanceCommand, AbstractCommand addIfMaxCommand) {
        this.helpCommand = helpCommand;
        commands.add(helpCommand);
        this.infoCommand = infoCommand;
        commands.add(infoCommand);
        this.showCommand = showCommand;
        commands.add(showCommand);
        this.addCommand = addCommand;
        commands.add(addCommand);
        this.updateIdCommand = updateIdCommand;
        commands.add(updateIdCommand);
        this.filterLessThanDistanceCommand = filterLessThanDistanceCommand;
        commands.add(filterLessThanDistanceCommand);
        this.saveCommand = saveCommand;
        commands.add(saveCommand);
        this.clearCommand = clearCommand;
        commands.add(clearCommand);
        this.executeScriptCommand = executeScriptCommand;
        commands.add(executeScriptCommand);
        this.exitCommand = exitCommand;
        commands.add(exitCommand);
        this.removeGreaterCommand = removeGreaterCommand;
        commands.add(removeGreaterCommand);
        this.removeLowerCommand = removeLowerCommand;
        commands.add(removeLowerCommand);
        this.removeByIdCommand = removeByIdCommand;
        commands.add(removeByIdCommand);
        this.removeAllByDistanceCommand = removeAllByDistanceCommand;
        commands.add(removeAllByDistanceCommand);
        this.countGreaterThanDistanceCommand = countGreaterThanDistanceCommand;
        commands.add(countGreaterThanDistanceCommand);
        this.addIfMaxCommand = addIfMaxCommand;
        commands.add(addIfMaxCommand);
    }

    /**
     * Выводит все доступные команды с описанием
     * @param argument это аргумент
     * @return Состояние работы команды
     */

    public boolean help (String argument){
        if (helpCommand.execute(argument)) {
            for (AbstractCommand command : commands) {
                System.out.println("\u001B[37m"+"\u001B[33m"+command.getName()+"\u001B[33m"+"\u001B[37m" + ": " + command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Запускает команду информации о коллекции
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean info(String argument){
        return infoCommand.execute(argument);
    }
    /**
     * Запускает команду выхода из программы
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean exit(String argument){
            return exitCommand.execute(argument);
    }
    /**
     * Запускает команду показа всех элементов коллекции
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean show(String argument){
        return showCommand.execute(argument);
    }
    /**
     * Запускает команду очистки коллекции
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean clear(String argument){
        return clearCommand.execute(argument);
    }
    /**
     * Запускает команду сохранения коллекции в файл
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean save(String argument){
        return saveCommand.execute(argument);
    }
    /**
     * Запускает команду добавления нового элемента
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean insert(String argument){
        return addCommand.execute(argument);
    }
    /**
     * Запускает команду замены элемента по ключу
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean update(String argument){
        return updateIdCommand.execute(argument);
    }
    /**
     * Запускает команду удаления элемента по ключу
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean removeKey(String argument){
        return filterLessThanDistanceCommand.execute(argument);
    }
    /**
     * Запускает команду удаления элементов по количеству комнат
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean removeAllByNumber(String argument){
        return removeAllByDistanceCommand.execute(argument);
    }
    /**
     * Запускает команду удаления элементов с ключом меньшим чем заданный
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean removeLowerKey(String argument){
        return removeByIdCommand.execute(argument);
    }
    /**
     * Запускает команду, которая выводит элементы по имени
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean filterName(String argument){
        return addIfMaxCommand.execute(argument);
    }
    /**
     * Запускает команду выполнения скрипта
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean executeScript(String argument){
        return executeScriptCommand.execute(argument);
    }
    /**
     * Запускает команду замены элемента, если он больше
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean replaceIfGreater(String argument){
        return removeGreaterCommand.execute(argument);
    }
    /**
     * Запускает команду замены элемента, если он меньше
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean replaceIfLower(String argument){
        return removeLowerCommand.execute(argument);
    }
    /**
     * Запускает команду подсчета кол-ва элементов с определенной отделкой
     * @param argument это переданный аргумент
     * @return состояние работы программы
     */
    public boolean countFurnish(String argument){
        return countGreaterThanDistanceCommand.execute(argument);
    }



}
