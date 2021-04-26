package utilities;

import general.Route;

import java.io.*;
import java.util.LinkedHashSet;

/**
 * Класс для работы с файлами
 */
public class FileManager {
    private File file;
    private XmlParser parser;
    private String envVariable;

    public FileManager(String fileName, XmlParser xmlParser) {

        this.parser = xmlParser;

        this.envVariable = fileName;
        try {
            this.file = new File(System.getenv(envVariable));
        } catch (NullPointerException e) {
            System.out.println("\u001B[37m" + "\u001B[31m" + "Вам необходимо задать переменную окружения!!!" + "\u001B[31m" + "\u001B[37m");
        }
    }


    /**
     * Чтение коллекции из файла
     *
     * @return коллекция, которая была считана из файла
     */
    public LinkedHashSet<Route> readCollection() {
        if (System.getenv().get(envVariable) != null) {
            if (file.exists() && !file.canRead()) {
                System.out.println("\u001B[37m" + "\u001B[31m" + "Недостаточно прав для чтения данных из файла. Добавьте права на чтение и запустите программу вновь" + "\u001B[31m" + "\u001B[37m");
                System.exit(0);
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(envVariable))) {
                String data = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    data += line + "\n";
                }
                System.out.println("\u001B[37m" + "\u001B[33m" + "Коллекция успешно загружена!" + "\u001B[33m" + "\u001B[37m");
                return parser.parse(data);
            } catch (FileNotFoundException e) {
                System.err.println("Файл с таким именем не найден :(");

            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода");

            }
        } else {
            System.out.println("\u001B[37m" + "\u001B[31m" + "Системная переменная с загрузочным файлом не найдена!" + "\u001B[31m" + "\u001B[37m");
        }
        return new LinkedHashSet<Route>();

    }

    /**
     * Метод для записи коллекции в файл
     *
     * @param routeCollection routeCollection
     */
    public void writeCollection(LinkedHashSet<Route> routeCollection) {


        if (System.getenv().get(envVariable) != null) {
            if (!file.canWrite()) {
                System.out.println("\u001B[37m" + "\u001B[31m" + "Недостаточно прав для записи в файл. Добавьте права на запись " + "\u001B[31m" + "\u001B[37m");
                try (FileWriter fileWriter = new FileWriter(new File("file2.XML"))) {
                    fileWriter.write(parser.unparse(routeCollection));
                    System.out.println("Не переживайте. Мы записали вашу коллекцию в новый файл: " + "file2.XML");
                } catch (Exception e) {
                    System.out.println();
                }

            } else {
                try (FileWriter fileWriter = new FileWriter(envVariable)) {

                    fileWriter.write(parser.unparse(routeCollection));
                    System.out.println("Коллекция успешно сохранена в файл!");

                } catch (Exception e) {
                    System.out.println("f");

                }
            }
        } else System.out.println("Системная переменная с загрузочным файлом не найдена!");


    }

}
