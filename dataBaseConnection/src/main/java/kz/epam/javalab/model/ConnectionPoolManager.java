package kz.epam.javalab.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by akmatleu on 27.03.17.
 */
public class ConnectionPoolManager {

    private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/simpleDB";
    private static final String USER_NAME = "tleu";
    private static final String USER_PASS = "";
    private static final int CONNECTION_POOL_SIZE = 5;


    private BlockingQueue<Connection> queue = new ArrayBlockingQueue<>(CONNECTION_POOL_SIZE);

    public ConnectionPoolManager()
    {
        initializeConnectionPool();
        System.out.println(queue.size());
    }

    private void initializeConnectionPool()
    {
       try {
           for (int i = 0 ; i < CONNECTION_POOL_SIZE ; i++ ){
               Connection connection = createNewConnectionForPool();
               if (connection != null) {
                   queue.put(connection);
                    }
                }
       }catch (InterruptedException e){
           System.out.println("Connection Pool initialization failed "+e.getMessage());
       }
        System.out.println("Connection Pool is ready");
    }



    //Creating a connection
    private Connection createNewConnectionForPool()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, USER_PASS);
            System.out.println("Connection: "+connection);
        }
        catch(SQLException sqle)
        {
            System.err.println("SQLException: "+sqle.getMessage());
            return null;
        }
        return connection;
    }

    public  Connection getConnectionFromPool()
    {
        Connection connection = null;
        try {
            connection = queue.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(queue.size());
        return connection;
    }

    public  void returnConnectionToPool(Connection connection)
    {
        //Adding the connection from the client back to the connection pool
        try {
            queue.put(connection);
            System.out.println(queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
