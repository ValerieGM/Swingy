package model.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.EntityCreator;
import model.helpers.View;
import model.entities.angels.*;

import static model.helpers.Universal.bIsAngel;
import static model.helpers.Universal.nbAngel;

public class Database {

    //database variables
    private static Database database;
    private Statement statement;
    private Connection connection;

    private PreparedStatement prepared;
    private ResultSet resultSet;

    private static final String angelTable = "angels";
    private static final String dbId = "id";
    private static final String dbName = "name";
    private static final String dbType = "type";
    private static final String dbLevel = "level";
    private static final String dbXP = "xp";
    private static final String dbAttack = "attack";
    private static final String dbDefense = "defense";
    private static final String dbHP = "hp";

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
                    dbHP + ") VALUES(?,?,?,?,?,?,?)";

    private  static final String updateAngelTable = "UPDATE " + angelTable + " SET " +
            dbLevel + " = ? , " + dbXP + " = ? , " + dbAttack + " = ? , " + dbDefense + " = ? ," +
            dbHP + " = ? " + " WHERE " + dbName + " = ?";

    private static final String getAngelTable =
            "SELECT * FROM " + angelTable;

    private static final String getAngelData =
            "SELECT * FROM " + angelTable +
                    " WHERE " + dbName + " = ?";

    private static final String deleteAngelTable =
            "DELETE from " + angelTable + " WHERE " + dbName + " = ?";

    //Functions

    //get instance of the database (synchronization look-up)

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return (database);
    }
    //connect to database
    private Connection dbConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:swingy.db");
            statement = connection.createStatement();
            statement.executeUpdate(createAngelTable);
        } catch(Exception e) {
            View.print("dbConnect:: " + e.getClass() + ":: " + e.getMessage());
            System.exit(0);
        } finally {
            close();
        }
        return (connection);
    }

    //insert into database
    public void insertAngel(Angel angel) {
        try {
            connection = this.dbConnect();
            //check for duplicates before insertion
            if (duplicateAngel(connection, angel))
                View.print("Can't be duplicating perfection now can we?!?!");
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
        } catch (Exception e) {
            View.print("InsertAngel:: " + e.getClass() +":: " + e.getMessage());
        } finally {
            close();
        }
    }
    //duplicate angels
    private boolean duplicateAngel(Connection connection, Angel angel){
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAngelTable);
            while (resultSet.next()) {
                if (angel.getName().equals(resultSet.getString(dbName)))
                    return true;
            }
        } catch (Exception e) {
            View.print("DuplicateAngel:: " + e.getClass() + ":: " + e.getMessage());
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
        } catch (Exception e) {
            View.print("UpdateAngel:: SQL Exception : " + e.getClass() + ":: " + e.getMessage());
            System.exit(0);
        } finally {
            close();
        }
    }

    //delete angel
    public void deleteAngel(String input) {
        try {
            connection = this.dbConnect();
            prepared = connection.prepareStatement(deleteAngelTable);
            prepared.setString(1, input);
            prepared.executeUpdate();
        } catch (Exception e) {
            View.print("Delete:: SQL exception : " + e.getClass() + "::" +  e.getMessage());
            System.exit(0);
        } finally {
            close();
        }
    }

    //print database
    public void printDatabase() {
        try {
            StringBuilder string = new StringBuilder();
            connection = this.dbConnect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAngelTable);
            while (resultSet.next()) {
                bIsAngel = true;
                string.append("Name: ").append(resultSet.getString(dbName)).append("\n")
                        .append("Type: ").append(resultSet.getString(dbType)).append("\n")
                        .append("Level: ").append(resultSet.getString(dbLevel)).append("\n")
                        .append("XP: ").append(resultSet.getString(dbXP)).append("\n")
                        .append("Attack: ").append(resultSet.getString(dbAttack)).append("\n")
                        .append("Defense: ").append(resultSet.getString(dbDefense)).append("\n")
                        .append("HP: ").append(resultSet.getString(dbHP)).append("\n\n");
                nbAngel += 1;
            }
            View.print(string.toString());
        } catch (Exception e) {
            View.print("PrintDB: " + e.getClass() + "::" + e.getMessage());
            System.exit(0);
        } finally {
            close();
        }
    }

    //extract angel details
    public Angel angelDetails(String name) {
        Angel angel = null;
        try {
            connection = this.dbConnect();
            prepared = connection.prepareStatement(getAngelData);
            prepared.setString(1, name);
            resultSet = prepared.executeQuery();

            if(resultSet.next()) {
                if (resultSet.getString(dbType).equals("Archangel"))
                    angel = (Angel) EntityCreator.newAngel(name, "Archangel");
                else if (resultSet.getString(dbType).equals("Cherubim"))
                    angel = (Angel) EntityCreator.newAngel(name, "Cherubim");
                else if (resultSet.getString(dbType).equals("Seraph"))
                    angel = (Angel) EntityCreator.newAngel(name, "Seraph");

                assert angel != null;
                angel.setLevel(resultSet.getInt(dbLevel));
                angel.setXp(resultSet.getInt(dbXP));
                angel.setAttack(resultSet.getInt(dbAttack));
                angel.setDefense(resultSet.getInt(dbDefense));
                angel.setHp(resultSet.getInt(dbHP));
            }
        }catch (Exception e) {
            View.print("AngelDetails: " + e.getClass() + ":: " + e.getMessage());
            System.exit(0);
        } finally {
            close();
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
                bIsAngel = true;
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
        } catch (Exception e) {
            View.print("ExtractDatabase: " + e.getClass() + ":: " + e.getMessage());
            System.exit(0);
        } finally {
            close();
        }
        return null;
    }

    //close all statements, etc

    private void close() {
        try {
            if (resultSet != null && !resultSet.isClosed())
                resultSet.close();
            if (statement != null && !statement.isClosed())
                statement.close();
            if (prepared != null && !prepared.isClosed())
                prepared.close();
        }catch (Exception e) {
            View.print("Close Function:: " + e.getClass() + ":: " + e.getMessage());
            System.exit(0);
        }
    }
}
