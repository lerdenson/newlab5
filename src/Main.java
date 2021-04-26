import commands.*;
import utilities.*;

import java.util.Scanner;

/**
 * Главный класс, в котором создаются все экземпляры и запускается программа
 *
 * @author Morozov Maxim
 */
public class Main {
    public static void main(String[] args) {
        FileManager fileManager;
        CollectionManager collectionManager;
        final String myenv = "envVariable";
        Scanner scanner = new Scanner(System.in);
        Creator creator = new Creator(scanner);
        XmlParser parser = new XmlParser();
        fileManager = new FileManager(myenv, parser);
        collectionManager = new CollectionManager(fileManager, creator);
        collectionManager.loadCollection();
        CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager, creator),
                new UpdateCommand(collectionManager, creator),
                new RemoveByIdCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ClearCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new AddIfMaxCommand(collectionManager, creator),
                new RemoveGreaterCommand(collectionManager),
                new RemoveLowerCommand(collectionManager),
                new RemoveAllByDistanceCommand(collectionManager),
                new CountGreaterThanDistanceCommand(collectionManager),
                new FilterLessThanDistance(collectionManager));
        Console console = new Console(scanner, commandManager, creator);
        console.workMode();
    }

}
