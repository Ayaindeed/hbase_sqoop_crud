package org.hsc;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class flightRead {
    private static final String TABLE_NAME = "flights_hbase";
    private static final String CF = "cf";

    public static String getFlightValue(String rowKey, String qualifier) throws Exception {
        try (Table table = HBaseConnection.getConnection().getTable(TableName.valueOf(TABLE_NAME))) {
            Get get = new Get(Bytes.toBytes(rowKey));
            get.addColumn(Bytes.toBytes(CF), Bytes.toBytes(qualifier));
            Result result = table.get(get);
            byte[] val = result.getValue(Bytes.toBytes(CF), Bytes.toBytes(qualifier));
            return val != null ? Bytes.toString(val) : null;
        }
    }
}

