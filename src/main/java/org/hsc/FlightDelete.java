package org.hsc;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class FlightDelete {
    private static final String TABLE_NAME = "flights_hbase";
    private static final String CF = "cf";

    public static void deleteColumn(String rowKey, String qualifier) throws Exception {
        try (Table table = HBaseConnection.getConnection().getTable(TableName.valueOf(TABLE_NAME))) {
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            delete.addColumns(Bytes.toBytes(CF), Bytes.toBytes(qualifier));
            table.delete(delete);
            System.out.println("Deleted column " + qualifier + " for row: " + rowKey);
        }
    }

    public static void deleteRow(String rowKey) throws Exception {
        try (Table table = HBaseConnection.getConnection().getTable(TableName.valueOf(TABLE_NAME))) {
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            table.delete(delete);
            System.out.println("Deleted row: " + rowKey);
        }
    }
}
