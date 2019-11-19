/**
 * Privacy Note information.......
 */

import java.sql.*;

/**
 *
 */
public class Lab3 {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;


    private static String SELECT_QUERY = "select * from actor";
    private static String INSERT_QUERY = "insert into actor (first_name,last_name) values (?, ?)";
    private static String UPDATE_QUERY = "update actor set first_name=? where id = ?";


    // static block which gets executed at the time of class loaded into memory.
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/xyz", "test", "test");

            statement = connection.createStatement();

            printMessage("Jdbc connection is established successfully");

        } catch (ClassNotFoundException e) {
            printMessage("Connection failed: " + e.toString());
        } catch (SQLException e) {
            printMessage("Connection failed: " + e.toString());
        }
    }


    /**
     * Method to retrieve records from given table and print
     *
     * @param tableName
     */
    public static void retrieveAllRecordsFromTable(String tableName) {

        String query = SELECT_QUERY + tableName;

        try {
            resultSet = statement.executeQuery(query);

            // iterate through the java resultset
            while (resultSet.next()) {
                int id = resultSet.getInt("actor_id");
                String name = resultSet.getString("first_name");
                String address = resultSet.getString("last_name");
                // print the results
                System.out.format("%s, %s, %s\n", id, name, address);
            }

        } catch (SQLException e) {
            printMessage("Error in executing the select query and getting the resultset : " + e.toString());
        }

    }


    /**
     * Method to insert new record
     */
    private static void insertNewRecord() {

        try {
            preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, "first_name");
            preparedStatement.setString(2, "last_name");
            preparedStatement.execute();
            printMessage("New record is insert successfully");
        } catch (Exception ex) {
            printMessage("Error in executing the insert statement : " + ex.toString());
        }
    }


    /**
     * Method to update record.
     *
     * @param name
     * @param id
     */
    private static void updateRecord(String name, int id) {

        try {
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, first_name);
            preparedStatement.setInt(2, actor_id);
            preparedStatement.executeUpdate();
            printMessage("Record is updated successfully");

        } catch (SQLException e) {
            printMessage("Error in updating the record :" + e.toString());
        }

    }


    /**
     * Method to close all the resources.
     */
    private static void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            printMessage("All resources are closed");
        } catch (SQLException e) {
            System.out.println("Error in closing the resources : " + e.toString());
        }

    }


    /**
     * Method to print message on console
     *
     * @param message
     */
    private static void printMessage(String message) {
        System.out.println("------------------------------------------------");
        System.out.println(message);
        System.out.println("------------------------------------------------");
    }

    public static void main(String args[]) {
        // Retrieving all records from table and printing on console
        retrieveAllRecordsFromTable("actor");

        // Calling method to insert new record
        // insertNewRecord();

        // Calling method to update new record
        // updateRecord("building4",7);

        // Closing all the resources at final
        closeResources();

    }


}
