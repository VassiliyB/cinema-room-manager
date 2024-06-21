package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static final Scanner SC = new Scanner(System.in);
    static final int MAX_COAST = 10;
    static final int MIN_COAST = 8;
    static final int EXCESS_THRESHOLD = 60;
    static int purchasedTickets = 0;
    static int currentIncome = 0;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = SC.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = SC.nextInt();
        boolean flag = true;


        char[][] room = new char[rows][numSeats];
        initArray(room);

        while (flag) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int menu = SC.nextInt();
            switch (menu) {
                case 1 -> printSeats(room);
                case 2 -> ticketPrice(rows, numSeats, room);
                case 3 -> getStatistics(rows, numSeats);
                case 0 -> flag = false;
            }
        }
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

    private static void ticketPrice(int rows, int numSeats, char[][] room) {
        System.out.println();
        boolean flag = true;
        int userRow = 0, userSeat = 0;
        while (flag) {
            System.out.println("Enter a row number:");
            userRow = SC.nextInt();
            System.out.println("Enter a seat number in that row:");
            userSeat = SC.nextInt();
            if ((userRow -1 >= 0 && userRow <= rows ) && (userSeat -1 >= 0 && userSeat <= numSeats)) {
                if (room[userRow - 1][userSeat - 1] != 'B') {
                    room[userRow - 1][userSeat - 1] = 'B';
                    purchasedTickets++;
                    flag = false;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            } else {
                System.out.println("Wrong input!");
            }
        }
        int totalSeats = rows * numSeats;
        int frontSeats = rows / 2;

        int price = totalSeats <= EXCESS_THRESHOLD || userRow <= frontSeats ? MAX_COAST : MIN_COAST;
        currentIncome += price;
        System.out.println("Ticket price: $" + price);
    }

    private static void calculateProfit(int rows, int numSeats) {
        int totalSeats = rows * numSeats;
        int profit = 0;
        int frontRows, backRows;

        if (totalSeats < EXCESS_THRESHOLD) {
            profit = totalSeats * MAX_COAST;
        } else {
            frontRows = rows / 2;
            backRows = rows - frontRows;
            profit = (frontRows * numSeats * MAX_COAST) + (backRows * numSeats * MIN_COAST);
        }
        System.out.println("Total income: $"+ profit);
    }

    private static void getPercentage(int rows, int numSeats) {

        double totalSeats = rows * numSeats;
        double oneSeatPercent = 100.0 / totalSeats;
        double percentage = oneSeatPercent * purchasedTickets;

        String result = String.format("Percentage: %.2f%%", percentage);
        System.out.println(result);
    }

    private static void getStatistics(int rows, int numSeats) {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        getPercentage(rows, numSeats);
        System.out.println("Current income: $" + currentIncome);
        calculateProfit(rows, numSeats);
    }
}
