package ru.dev.messenger.DL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mariadb.jdbc.Driver;
import ru.dev.messenger.DTO.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBL {

    public DBL() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");

            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?" + "user=root&password=root");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db?" + "user=root&password=root");

            statement = connection.createStatement();
            System.out.println("Connected!");
            initialize();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //-------------------------------
    public static final DBL INSTANCE = new DBL();   // SINGLETONE
    //-------------------------------
    private static List<User> Storage = new ArrayList();
    private List<User> Row = new ArrayList();
    private Connection connection;
    private Statement statement;
    //-------------------------------

    public List getData(int index) {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connecton working");
                if (index > 0) return getByIndex(index);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initialize() {
        try {
            if (!connection.isClosed()) {

            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List getFullData() {
        return  null;
    }

    private List<User> getByIndex(int index) {
        String SqlQuery = "SELECT id, name, count, categories FROM products WHERE id = ?";
        PreparedStatement st = null;
        return  null;
    }

    public void addToDB(User product) {
        try {
            if (!connection.isClosed()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void clearDB() {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connection working");
                Statement statement = connection.createStatement();
                statement.execute("DROP TABLE IF EXISTS `db`.`products`;");
                statement.execute("CREATE TABLE IF NOT EXISTS `db`.`products` " + "(`id` INT NOT NULL AUTO_INCREMENT UNIQUE,`name` VARCHAR(45) NOT NULL,`" + "count` INT(7) NOT NULL,`categories` VARCHAR(45) NOT NULL, " + "PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;");
                System.out.println("DB Cleared!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteZero() {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connection working");
                Statement statement = connection.createStatement();
                System.out.println(statement.execute("DELETE FROM `db`.`products` WHERE `count` < 1;"));

                System.out.println("Empty fields deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connection working");
                String stmnt = "DELETE FROM `db`.`products` WHERE `id` = ?;";
                PreparedStatement prepStmnt = connection.prepareStatement(stmnt);
                prepStmnt.setInt(1, id);
                System.out.println(prepStmnt.execute());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillDB() {
        try {
            System.out.println("DB Filled!");

            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('1', 'lg l90', '1', 'phone 3g 2g cheap quick_memo') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('2', 'lg k8', '10', 'phone 4g knock_code android_7') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('3', 'samsung GT', '12', 'tablet 3g 10_inch') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('4', 'Nokia c6', '1', 'phone 3g') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('5', 'lenovo a3500', '2', 'tablet 3g 1gb') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('6', 'lenovo a319', '0', 'phone 3g 1gb') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('7', 'iphone X', '0', 'phone 4g 3gb') " + "ON DUPLICATE KEY UPDATE id = id;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
