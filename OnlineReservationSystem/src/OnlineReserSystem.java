import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class OnlineReserSystem {
    private static final int min = 1000;
    private static final int max = 9999;

    public static class user {
        private String username;      //Enter your username to login your page
        private String password;      //Enter password of your login credential

        Scanner sc = new Scanner(System.in);

        public user() {
        }

        public String getUsername() {
            System.out.print("Enter Your Username : ");
            username = sc.nextLine();
            return username;
        }

        public String getPassword() {
            System.out.print("Enter Password : ");
            password = sc.nextLine();
            return password;
        }
    }

    public static class PnrRecord {
        private int pnrNumber;
        private String passengerName;
        private String trainNumber;
        private String classType;
        private String journeyDate;
        private String from;
        private String to;

        Scanner sc = new Scanner(System.in);

        public int getpnrNumber() {
            Random random = new Random();
            pnrNumber = random.nextInt(max) + min;
            return pnrNumber;
        }

        public String getPassengerName() {
            System.out.print("Enter the passenger name : ");
            passengerName = sc.nextLine();
            return passengerName;
        }

        public String gettrainNumber() {
            System.out.print("Enter the train number : ");
            trainNumber = sc.nextLine();
            return trainNumber;
        }

        public String getclassType() {
            System.out.print("Enter the class type : ");
            classType = sc.nextLine();
            return classType;
        }

        public String getjourneyDate() {
            System.out.print("Enter the Journey date in 'DD/MM/YYYY' format: ");
            journeyDate = sc.nextLine();
            return journeyDate;
        }

        public String getfrom() {
            System.out.print("Enter the Starting place : ");
            from = sc.nextLine();
            return from;
        }

        public String getto() {
            System.out.print("Enter the Destination place :  ");
            to = sc.nextLine();
            return to;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        user u1 = new user();
        String username = u1.getUsername();
        String password = u1.getPassword();

        String url = "jdbc:mysql://localhost:3306/ravig"; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("User Connection Granted.\n");
                while (true) {
                    String InsertQuery = "insert into OnlineReservations values (?, ?, ?, ?, ?, ?, ?)";
                    String DeleteQuery = "DELETE FROM OnlineReservations WHERE pnr_number = ?";
                    String ShowQuery = "Select * from OnlineReservations";

                    System.out.println("**********ONLINE  RESERVATIONS  SYSTEM***********");
                    System.out.println("1. Insert Record.\n");
                    System.out.println("2. Delete Record.\n");
                    System.out.println("3. Show All Records.\n");
                    System.out.println("4. Exit.\n");
                    System.out.print("Enter the choice : ");
                    int choice = sc.nextInt();

                    if(choice == 1) {

                        PnrRecord p1 = new PnrRecord();
                        int pnr_number = p1.getpnrNumber();
                        String passengerName = p1.getPassengerName();
                        String trainNumber = p1.gettrainNumber();
                        String classType = p1.getclassType();
                        String journeyDate = p1.getjourneyDate();
                        String getfrom = p1.getfrom();
                        String getto = p1.getto();

                        try (PreparedStatement preparedStatement = connection.prepareStatement(InsertQuery)) {
                            preparedStatement.setInt(1, pnr_number);
                            preparedStatement.setString(2, passengerName);
                            preparedStatement.setString(3, trainNumber);
                            preparedStatement.setString(4, classType);
                            preparedStatement.setString(5, journeyDate);
                            preparedStatement.setString(6, getfrom);
                            preparedStatement.setString(7, getto);

                            int rowsAffected = preparedStatement.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Record added successfully.");
                            }

                            else {
                                System.out.println("No records were added.");
                            }
                        }

                        catch (SQLException e) {
                            System.err.println("SQLException: " + e.getMessage());
                        }

                    }

                    else if (choice == 2) {
                        System.out.println("Enter the PNR number to delete the record : ");
                        int pnrNumber = sc.nextInt();
                        try (PreparedStatement preparedStatement = connection.prepareStatement(DeleteQuery)) {
                            preparedStatement.setInt(1, pnrNumber);
                            int rowsAffected = preparedStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                System.out.println("Record deleted successfully.");
                            }

                            else {
                                System.out.println("No records were deleted.");
                            }
                        }

                        catch (SQLException e) {
                            System.err.println("SQLException: " + e.getMessage());
                        }
                    }

                    else if (choice == 3) {
                        try (PreparedStatement preparedStatement = connection.prepareStatement(ShowQuery);
                                ResultSet resultSet = preparedStatement.executeQuery()) {
                            System.out.println("\nAll records printing.\n");
                            while (resultSet.next()) {
                                String pnrNumber = resultSet.getString("PNR_Number");
                                String passengerName = resultSet.getString("Passenger_Name");
                                String trainNumber = resultSet.getString("Train_Number");
                                String classType = resultSet.getString("Class_Type");
                                String journeyDate = resultSet.getString("Journey_Date");
                                String fromLocation = resultSet.getString("From_Location");
                                String toLocation = resultSet.getString("To_Location");

                                System.out.println("PNR Number: " + pnrNumber);
                                System.out.println("Passenger Name: " + passengerName);
                                System.out.println("Train Number: " + trainNumber);
                                System.out.println("Class Type: " + classType);
                                System.out.println("Journey Date: " + journeyDate);
                                System.out.println("From Location: " + fromLocation);
                                System.out.println("To Location: " + toLocation);
                                System.out.println("--------------");
                            }
                        } catch (SQLException e) {
                            System.err.println("SQLException: " + e.getMessage());
                        }
                    }

                    else if (choice == 4) {
                        System.out.println("Exiting the program.\n");
                        break;
                    }

                    else {
                        System.out.println("You have entered an invalid choice.\n");
                    }
                }

            }

            catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
            }
        }

        catch (ClassNotFoundException e) {
            System.err.println("Error into loading JDBC driver: " + e.getMessage());
        }

        sc.close();
    }
}