package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = sc.nextInt();

        char[][] room = new char[rows][numSeats];
        // Write your code here
        initArray(room);
        printSeats(room);
//        calculateProfit(rows, numSeats);
        ticketPrice(rows, numSeats, room);
        printSeats(room);
    }

    private static void initArray(char[][] room) {
        for (char[] item : room) {
            Arrays.fill(item, 'S');
        }
    }

    private static void printSeats(char[][] room) {
        System.out.println();
        System.out.print("Cinema:\n ");
        int row = room.length;
        int col = room[0].length;

        for (int i = 1; i <= col; ++i) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < row; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < col; j++) {
                System.out.print(" " + room[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void calculateProfit(int rows, int numSeats) {
        int totalSeats = rows * numSeats;
        int profit = 0;
        int maxCost = 10;
        int minCost = 8;
        int frontRows, backRows;
        if (totalSeats < 60) {
            profit = totalSeats * maxCost;
        } else {
            frontRows = rows / 2;
            backRows = rows - frontRows;
            profit = (frontRows * numSeats * maxCost) + (backRows * numSeats * minCost);
        }
        System.out.println("Total income:");
        System.out.println("$" + profit);
    }

    private static void ticketPrice(int rows, int numSeats, char[][] room) {
        System.out.println("Enter a row number:");
        int userRow = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int userSeat = sc.nextInt();
        room[userRow - 1][userSeat - 1] = 'B';

        int totalSeats = rows * numSeats;
        int maxCost = 10;
        int minCost = 8;
        int frontRows;
        int price = totalSeats <= 60 || userRow <= rows / 2 ? 10 : 8;
//        if (totalSeats < 60) {
//            price = String.format("Ticket price: $%d", maxCost);
//        } else {
//            frontRows = rows / 2;
//            price = userRow <= frontRows
//                    ? String.format("Ticket price: $%d", maxCost)
//                    : String.format("Ticket price: $%d", minCost);
//        }
        System.out.println();
        System.out.println("Ticket price: $" + price);
    }
}