package org.hsc;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class HBaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Configuration config = HBaseConfiguration.create();
            config.set("hbase.zookeeper.quorum", "hbase");
            config.set("hbase.zookeeper.property.clientPort", "2181");

            connection = ConnectionFactory.createConnection(config);
        }
        return connection;
    }

    public static void closeConnection() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

