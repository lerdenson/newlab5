package utilities;

import exceptions.IncorrectValueException;
import general.Coordinates;
import general.Location;

import java.util.Scanner;

/**
 * Спрашивает пользователя значения для нового экземпдяра Route
 */

public class Creator {
    private boolean t=true;
    private Scanner scanner;
    public Creator(Scanner scanner){
        this.scanner=scanner;
    }
    /**
     * Установка сканера для нового ввода пользователя
     * @param scanner сканнер
     */
    public void setScanner(Scanner scanner){
        this.scanner=scanner;
    }
    /**
     *
     * @return Scanner, который используется для пользовательсеого ввода
     */
    public Scanner getScanner(){
        return this.scanner;
    }

    /**
     * Узнает название пути
     * @return название
     */
    public String newName(){
        t=true;
        String name="";
        while (t) {
            try {

                System.out.println("Введите название");
                name = scanner.nextLine().trim();
                if (name.equals("") || name.equals(null)) throw new IncorrectValueException();
                t = false;
            } catch (IncorrectValueException e) {
                System.err.println("Название не может быть пустой строкой");

            }
        }
        return name;}

    /**
     * Узнает координаты
     * @return Координаты
     */
    public Coordinates newCoordinates(){
        float x=5.5f;
        long y= 6L;
        t=true;
        System.out.println("Введите координаты");
        while(t){
            try{
                System.out.println("x:");
                x=Float.parseFloat(scanner.nextLine().trim());//!!!!потумкать над исключениями
                t=false;
            }catch (NumberFormatException e){
                System.err.println("Координата может быть только числом");
            }
        }
        t=true;
        while(t){
            try{
                System.out.println("y:");
                y=Long.parseLong(scanner.nextLine().trim());
                t=false;
            }catch (NumberFormatException e){
                System.err.println("Координата может быть только числом");
            }
        }return new Coordinates(x,y);

    }

    /**
     * Узнает длинну пути от места выезда до пункта назначения
     * @return расстояние
     */
    public Float newDistance(){
        t=true;
        Float distance = 5f;
        while(t){
            try{
                System.out.println("Введите расстояние");
                distance=Float.parseFloat(scanner.nextLine().trim());
                if (distance<=1) throw new IncorrectValueException();
                t=false;
            }catch (NumberFormatException e){
                System.err.println("Расстояние должно быть целым числом");
            }catch(IncorrectValueException e){
                System.err.println("Расстояние должно быть больше одного");
            }
        }
        return distance;

    }

    /**
     * Узнает Локацию
     * @return локация
     */
    public Location newLocation(String whichOne){
        t=true;
        String name="";
        while (t) {
            try {

                System.out.println("Введите название " + whichOne);
                name = scanner.nextLine().trim();
                if (name.equals("") || name.equals(null)) throw new IncorrectValueException();
                t = false;
            } catch (IncorrectValueException e) {
                System.err.println("Название не может быть пустой строкой");

            }
        }

        float x=5.5f;
        int y= 6;
        t=true;
        System.out.println("Введите координаты локции");
        while(t){
            try{
                System.out.println("x:");
                x=Float.parseFloat(scanner.nextLine().trim());//!!!!потумкать над исключениями
                t=false;
            }catch (NumberFormatException e){
                System.err.println("Координата может быть только числом");
            }
        }
        t=true;
        while(t){
            try{
                System.out.println("y:");
                y=Integer.parseInt(scanner.nextLine().trim());
                t=false;
            }catch (NumberFormatException e){
                System.err.println("Координата может быть только числом");
            }
        }
        return new Location(x, y, name);
    }
}
