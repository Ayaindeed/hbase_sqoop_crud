package org.hsc;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class flightCreate {
    private static final String TABLE_NAME = "flights_hbase";
    private static final String CF = "cf";

    public static void putFlight(String rowKey, String qualifier, String value) throws Exception {
        try (Table table = HBaseConnection.getConnection().getTable(TableName.valueOf(TABLE_NAME))) {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(CF), Bytes.toBytes(qualifier), Bytes.toBytes(value));
            table.put(put);
            System.out.println("Put data for row: " + rowKey);
        }
    }
}
