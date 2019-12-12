package model.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.EntityFactory;
import model.entities.angels.*;

public class Database {

    //database variables
    private static final String databaseName = "swingy.db";
    private static final String angelTable = "angels";
    private static final String dbId = "id";
    private static final String dbName = "name";
    private static final String dbType = "type";
    private static final String dbLevel = "level";
    private static final String dbXP = "xp";
    private static final String dbAttack = "attack";
    private static final String dbDefense = "defense";
    private static final String dbHP = "hp";
    public static Boolean angelExists = false;
    public static int angelCount;

    private static final String createAngelTable =
            "CREATE TABLE IF NOT EXISTS " + angelTable +
                    "(" + dbId + " INT PRIMARY KEY, " +
                    dbName + " TEXT, " + "type" + " TEXT, " +
                    dbLevel + " INTEGER, " + dbXP + " INTEGER, " +
                    dbAttack + " INTEGER, " + dbDefense + " INTEGER, " +
                    dbHP + " INTEGER)";

    private static final String insertAngelTable =
            "INSERT INTO " + angelTable + "(" +
                    dbName + "," + dbType + "," +
                    dbLevel + "," + dbXP + "," +
                    dbAttack + "," + dbDefense + "," +
                    dbHP + ")" + " VALUES(?,?,?,?,?,?,?)";

    private  static final String updateAngelTable =
            String.format("UPDATE %sSET%s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, ",
                    angelTable, dbName, dbType, dbLevel, dbXP, dbAttack, dbDefense, dbHP);

    private static final String getAngelTable =
            "SELECT * FROM " + angelTable;

    private static final String getAngelData =
            "SELECT * FROM " + angelTable +
                    " WHERE " + dbName + " =?";

    private static final String deleteAngelTable =
            "DELETE from " + angelTable + " WHERE " + dbName + " = ?";

    private static final String sqlDriver = "org.sqlite.JDBC";
    private static final String sqlURL = "jdbc:sqlite:" + databaseName;

    private static Database database;
    private Statement statement;
    private Connection connection;

    private PreparedStatement prepared;
    private ResultSet resultSet;

    //Functions

    //get instance of the database

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return (database);
    }
    //connect to database
    private Connection dbConnect() {
        connection = null;
        try {
            Class.forName(sqlDriver);
            connection = DriverManager.getConnection(sqlURL);
            statement = connection.createStatement();
            statement.executeUpdate(createAngelTable);
            System.out.println("Database Connection Created");
        } catch(Exception e) {
            System.err.println("dbConnect:: " + e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }
        return (connection);
    }

    //insert into database
    public void insertAngel(Angel angel) {
        System.out.println(angel);
        try {
            connection = this.dbConnect();
            //check for duplicates before insertion
            if (duplicateAngel(connection, angel))
                System.out.println("Can't be duplicating perfection now can we?!?!");
            else {
                prepared = connection.prepareStatement(insertAngelTable);
                prepared.setString(1, angel.getName());
                prepared.setString(2, angel.getType());
                prepared.setInt(3, angel.getLevel());
                prepared.setInt(4, angel.getXp());
                prepared.setInt(5, angel.getAttack());
                prepared.setInt(6, angel.getDefense());
                prepared.setInt(7, angel.getHp());
                prepared.executeUpdate();
                System.out.println("Database: ** " + angel.getName() + " ** Created");
            }
        } catch (SQLException | IndexOutOfBoundsException e) { //IOException
            System.err.println("Insert:: SQLite Error: " + e.getMessage());
        }
    }
    //duplicate angels
    private boolean duplicateAngel(Connection connection, Angel angel) throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery(getAngelTable);
        while (resultSet.next()) {
            if(angel.getName().equals(resultSet.getString(dbName)))
                return true;
        }
        return false;
    }

    //update angel
    public void updateAngel(Angel angel) {
        try{
            connection = this.dbConnect();
            prepared = connection.prepareStatement(updateAngelTable);
            prepared.setInt(1, angel.getLevel());
            prepared.setInt(2, angel.getXp());
            prepared.setInt(3, angel.getAttack());
            prepared.setInt(4, angel.getDefense());
            prepared.setInt(5, angel.getHp());
            prepared.setString(6, angel.getName());
            prepared.executeUpdate();
            System.out.println("Database Updated");
        } catch (SQLException e) {
            System.out.println("UpdateAngel:: SQL Exception : " + e.getMessage());
            System.exit(0);
        }
    }

    //delete angel
    public void deleteAngel(String input) {
        try {
            connection = this.dbConnect();
            prepared = connection.prepareStatement(deleteAngelTable);
            prepared.setString(1, input);
            prepared.executeUpdate();
            System.out.println("Angel Destroyed");
        } catch (SQLException e) {
            System.out.println("Delete:: SQL exception : " + e.getMessage());
            System.exit(0);
        }
    }

    //print database
    public void printDatabase() {
        try {
            StringBuilder string = new StringBuilder();
            connection = this.dbConnect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAngelTable);
            angelCount = 0;
            while (resultSet.next()) {
                angelExists = true;
                string.append("Name: ").append(resultSet.getString(dbName)).append("\n")
                        .append("Type: ").append(resultSet.getString(dbType)).append("\n")
                        .append("Level: ").append(resultSet.getString(dbLevel)).append("\n")
                        .append("XP: ").append(resultSet.getString(dbXP)).append("\n")
                        .append("Attack: ").append(resultSet.getString(dbAttack)).append("\n")
                        .append("Defense: ").append(resultSet.getString(dbDefense)).append("\n")
                        .append("HP: ").append(resultSet.getString(dbHP)).append("\n");
                angelCount += 1;
            }
            System.out.println(string.toString());
        } catch (SQLException e) {
            System.out.println("PrintDB: " + e.getMessage());
            System.exit(0);
        }
    }

    //extract angel details
    public Angel angelDetails(String name) {
        Angel angel = null;
        try {
            connection = this.dbConnect();
            prepared = connection.prepareStatement(getAngelData);
            System.out.println(dbName + "********************");
            prepared.setString(1, name);
            resultSet = prepared.executeQuery();

            if(resultSet.next()) {
                if (resultSet.getString(dbType).equals("Archangel"))
                    angel = (Angel) EntityFactory.newAngel(name, "Archangel");
                else if (resultSet.getString(dbType).equals("Cherubim"))
                    angel = (Angel)EntityFactory.newAngel(name, "Cherubim");
                else if (resultSet.getString(dbType).equals("Seraph"))
                    angel = (Angel)EntityFactory.newAngel(name, "Seraph");

                assert angel != null;
                angel.setLevel(resultSet.getInt(dbLevel));
                angel.setXp(resultSet.getInt(dbXP));
                angel.setAttack(resultSet.getInt(dbAttack));
                angel.setDefense(resultSet.getInt(dbDefense));
                angel.setHp(resultSet.getInt(dbHP));
            }
        }catch (SQLException e) {
            System.out.println("AngelDetails: " + e.getMessage());
            System.exit(0);
        }
        return angel;
    }

    //extract database
    public List<Angel> extractDatabase() {
        try {
            List<Angel> angelList = new ArrayList<>();

            connection = this.dbConnect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAngelTable);

            while (resultSet.next()) {
                angelExists = true;
                Angel angel = null;
                if (resultSet.getString(dbType).equals("Archangel"))
                    angel = new Archangel();
                else if (resultSet.getString(dbType).equals("Cherubim"))
                    angel = new Cherub();
                else if (resultSet.getString(dbType).equals("Seraph"))
                    angel = new Seraph();

                assert angel != null;
                angel.setName(resultSet.getString(dbName));
                angel.setType(resultSet.getString(dbType));
                angel.setLevel(resultSet.getInt(dbLevel));
                angel.setXp(resultSet.getInt(dbXP));
                angel.setAttack(resultSet.getInt(dbAttack));
                angel.setDefense(resultSet.getInt(dbDefense));
                angel.setHp(resultSet.getInt(dbHP));
                angelList.add(angel);
            }
            return angelList;
        } catch (SQLException e) {
            System.out.println("ExtractDatabase: " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
