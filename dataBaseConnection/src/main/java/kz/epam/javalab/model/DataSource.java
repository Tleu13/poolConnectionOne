package kz.epam.javalab.model;

import java.sql.*;

/**
 * Created by akmatleu on 27.03.17.
 */
public class DataSource {

    private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/simpleDB";
    private static final String USER_NAME = "tleu";
    private static final String USER_PASS = "";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ADRESS = "adress";



    public void getUsersNames (Connection connection){

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+TABLE_USERS);
            while (resultSet.next()){
                System.out.println(resultSet.getInt(COLUMN_ID)+" - id and name is "+resultSet.getString(COLUMN_NAME));
            }

            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            e.getMessage();
        }



    }

}
