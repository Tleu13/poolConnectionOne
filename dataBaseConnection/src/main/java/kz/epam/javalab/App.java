package kz.epam.javalab;

import kz.epam.javalab.model.ConnectionPoolManager;
import kz.epam.javalab.model.DataSource;


import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ConnectionPoolManager connectionPoolManager = new ConnectionPoolManager();

        DataSource dataSource = new DataSource();


        Connection connection = connectionPoolManager.getConnectionFromPool();
        dataSource.getUsersNames(connection);

        connectionPoolManager.returnConnectionToPool(connection);


    }
}
