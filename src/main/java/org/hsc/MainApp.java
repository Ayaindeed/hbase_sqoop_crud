package org.hsc;

public class MainApp {
    public static void main(String[] args) throws Exception {
        String rowKey = "20250630_1980";
        String qualifier = "AIRLINE";
        String value = "Delta";

        flightCreate.putFlight(rowKey, qualifier, value);

        String airline = flightRead.getFlightValue(rowKey, qualifier);
        System.out.println("AIRLINE: " + airline);

        FlightDelete.deleteColumn(rowKey, qualifier);

        FlightDelete.deleteRow(rowKey);

        // Close c
        HBaseConnection.closeConnection();
    }
}
