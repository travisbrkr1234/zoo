/**
 * Created by carlos.ochoa on 4/27/2016.
 */
public class Menu {

    public static void printMenu() {

        final StringBuilder zooMenu = new StringBuilder();

        zooMenu.append("-------------------------------------------------\n")
                .append("********000000*******0000*****0000***************\n")
                .append("***********//*******0****0***0****0**************\n")
                .append("**********000000*****0000*****0000***************\n")
                .append("-------------------------------------------------\n")
                .append("-------------Please enter a choice---------------\n")
                .append("--1. Add animal----------------------------------\n")
                .append("--2. List animals--------------------------------\n")
                .append("--3. Remove animal-------------------------------\n")
                .append("--4. Find animal---------------------------------\n")
                .append("----Type e, exit, q, or quit to leave program----\n");
        System.out.println(zooMenu);
    }

}