package utilities;


import exceptions.FileException;
import exceptions.IdNotUnique;
import general.Coordinates;
import general.Location;
import general.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для чтения xml файла.
 */

public class XmlParser {
    /**
     * Метод для считывания коллекции из XML
     * @param data data
     * @return routeCollection
     */
    public LinkedHashSet<Route> parse(String data) {
        String[] lines = data.split("\n");
        LinkedHashSet<Route> routes = new LinkedHashSet<>();


        String name = "", fromName = "", toName = "";
        int id = 0, fromY = 0, toY = 0;
        float distance = 0, coordinatesX = 0, fromX = 0, toX = 0;
        long coordinatesY = 0;
        int year = 0, month = 0, dayOfMonth = 0;
        int countForCoordinateX, countForCoordinateY, countName;

        ArrayList<Integer> listId = new ArrayList<>();

        Pattern pattern = Pattern.compile(">.+?<");

        try {
            if (!checkIfCorrect(data)) throw new FileException();
            for (int i = 1; i < lines.length; i++) {

                if (lines[i].contains("<Route>")) {
                    countForCoordinateX = 0;
                    name = null;
                    fromName = null;
                    toName = null;
                    countForCoordinateY = 0;
                    coordinatesX = 0;
                    fromX = 0;
                    toX = 0;
                    coordinatesY = 0;
                    fromY = 0;
                    toY = 0;
                    countName = 0;

                    i++;
                    while (!lines[i].contains("</Route>")) {

                        if (lines[i].contains("<id>")) {

                            Matcher matcher = pattern.matcher(lines[i]);
                            if(matcher.find()) {
                                String tmp = lines[i].substring(matcher.start() + 1, matcher.end() - 1);

                                id = Integer.parseInt(tmp);

                                if (id <= 0) {
                                    throw new NumberFormatException();
                                }

                                for (int j = 0; j < listId.size(); j++) {
                                    if (listId.contains(id)) throw new IdNotUnique();
                                }
                                listId.add(id);
                            }


                        }

                        if (lines[i].contains("<name>")) {
                            Matcher matcher = pattern.matcher(lines[i]);
                            if(matcher.find()) {
                                String newName = lines[i].substring(matcher.start() + 1, matcher.end() - 1);
                                switch (countName) {
                                    case 0: {
                                        name = newName;
                                        break;
                                    }
                                    case 1: {
                                        fromName = newName;
                                        break;
                                    }
                                    case 2: {
                                        toName = newName;
                                        break;
                                    }
                                }

                                countName++;
                            }

                        }

                        if (lines[i].contains("<x>")) {
                            Matcher matcher = pattern.matcher(lines[i]);
                            if(matcher.find()) {
                                float newX = Float.parseFloat(lines[i].substring(matcher.start() + 1, matcher.end() - 1));

                                switch (countForCoordinateX) {
                                    case 0: {
                                        coordinatesX = newX;
                                        break;
                                    }
                                    case 1: {
                                        fromX = newX;
                                        break;
                                    }
                                    case 2: {
                                        toX = newX;
                                        break;
                                    }
                                }
                                countForCoordinateX++;
                            }

                        }
                        if (lines[i].contains("<y>")) {
                            Matcher matcher = pattern.matcher(lines[i]);
                            if(matcher.find()) {
                                String newY = lines[i].substring(matcher.start() + 1, matcher.end() - 1);
                                switch (countForCoordinateY) {
                                    case 0: {
                                        coordinatesY = Long.parseLong(newY);
                                        break;
                                    }
                                    case 1: {
                                        fromY = Integer.parseInt(newY);
                                        break;
                                    }
                                    case 2: {
                                        toY = Integer.parseInt(newY);
                                    }
                                }
                                countForCoordinateY++;
                            }


                        }

                        if (lines[i].contains("<year>")) {
                            Matcher matcher = pattern.matcher(lines[i]);
                            if(matcher.find()) {
                                year = Integer.parseInt(lines[i].substring(matcher.start() + 1, matcher.end() - 1));
                            }
                        }

                        if (lines[i].contains("<month>")) {
                            Matcher matcher = pattern.matcher(lines[i]);
                            if (matcher.find()) {
                                month = Integer.parseInt(lines[i].substring(matcher.start() + 1, matcher.end() - 1));
                            }

                        }

                        if (lines[i].contains("<dayOfMonth>")) {
                            Matcher matcher = pattern.matcher(lines[i]);
                            if(matcher.find()) {
                                dayOfMonth = Integer.parseInt(lines[i].substring(matcher.start() + 1, matcher.end() - 1));
                            }

                        }

                        if (lines[i].contains(("<distance>"))) {
                            Matcher matcher = pattern.matcher((lines[i]));
                            if(matcher.find()) {
                                distance = Float.parseFloat(lines[i].substring(matcher.start() + 1, matcher.end() - 1));
                            }


                        }
                        i++;
                    }
                }

                if(lines[i].contains("</Route>")) {
                    routes.add(new Route(id, name, new Coordinates(coordinatesX, coordinatesY), LocalDate.of(year, month, dayOfMonth),
                            new Location(fromX, fromY, fromName), new Location(toX, toY, toName), distance));


                }

            }
        } catch (IdNotUnique e) {
            System.out.println("Ошибка! Есть элементы  одинаковым id");
        } catch (FileException e) {
            System.out.println("Ошибка при чтении файла");
        } catch (Exception e) {
            System.out.println("Файл не удовлетворяет требованию формата");
        }
        return routes;
    }


    public String unparse(LinkedHashSet<Route> routeCollection) {
        String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        result += "<Routes>\n";
        for (Route route : routeCollection) {
            result += "\t<Route>\n" +
                    "\t\t<id>" + route.getId() + "</id>\n" +
                    "\t\t<name>" + route.getName() + "</name>\n" +
                    "\t\t<coordinates>\n" +
                    "\t\t\t<x>" + route.getCoordinates().getX() + "</x>\n" +
                    "\t\t\t<y>" + route.getCoordinates().getY() + "</y>\n" +
                    "\t\t</coordinates>\n" +
                    "\t\t<creationDate>\n" +
                    "\t\t\t<year>" + route.getCreationDate().getYear() + "</year>\n" +
                    "\t\t\t<month>" + route.getCreationDate().getMonthValue() + "</month>\n" +
                    "\t\t\t<dayOfMonth>" + route.getCreationDate().getDayOfMonth() + "</dayOfMonth>\n" +
                    "\t\t</creationDate>\n" +
                    "\t\t<from>\n" +
                    "\t\t\t<name>" + route.getFrom().getName() + "</name>\n" +
                    "\t\t\t<x>" + route.getFrom().getX() + "</x>\n" +
                    "\t\t\t<y>" + route.getFrom().getY() + "</y>\n" +
                    "\t\t</from>\n" +
                    "\t\t<to>\n" +
                    "\t\t\t<name>" + route.getTo().getName() + "</name>\n" +
                    "\t\t\t<x>" + route.getTo().getX() + "</x>\n" +
                    "\t\t\t<y>" + route.getTo().getY() + "</y>\n" +
                    "\t\t</to>\n" +
                    "\t\t<distance>" + route.getDistance() + "</distance>\n" +
                    "\t</Route>\n";
        }
        result += "</Routes>";
        return result;
    }

    public boolean checkIfCorrect(String string) {
        return string.matches("(<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>(\n" +
                "<Routes>\n" +
                "(\t<Route>\n" +
                "\t\t<id>\\d+?</id>\n" +
                "\t\t<name>.+?</name>\n" +
                "\t\t<coordinates>\n\t\t\t<x>\\d+?</x>\n\t\t\t<y>\\d+?</y>\n\t\t</coordinates>\n" +
                "\t\t<creationDate>\n\t\t\t<year>\\d+?</year>\n\t\t\t<month>\\d+?</month>\n\t\t\t<dayOfMonth>\\d+?</dayOfMonth>\n\t\t</creationDate>\n" +
                "\t\t<from>\n\t\t\t<name>.{1,514}?</name>\n\t\t\t<x>\\d+?</x>\n\t\t\t<y>\\d+?</y>\n\t\t</from>\n" +
                "\t\t<to>\n\t\t\t<name>.{1,514}?</name>\n\t\t\t<x>\\d+?</x>\n\t\t\t<y>\\d+?</y>\n\t\t</to>\n" +
                "\t\t<distance>\\d+?</distance>\n" +
                "\t</Route>\n)*</Routes>)?\n?)?");
    }
}