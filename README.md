# HBase Sqoop CRUD Application

A Java application demonstrating CRUD (Create, Read, Update, Delete) operations on Apache HBase, designed for managing flight data in a NoSQL database environment. This project showcases how to integrate HBase with Java applications using Docker for easy deployment and testing.

## Features

- **Create**: Add new flight records to HBase
- **Read**: Retrieve flight information by row key and column qualifier
- **Delete**: Remove specific columns or entire rows from HBase tables
- **Docker Support**: Containerized application for easy deployment
- **HBase Integration**: Native HBase client API usage
- **Logging**: Configurable logging with Log4j

## Prerequisites

Before running this application, ensure you have:

- Java 8 or higher
- Maven 3.6+
- Docker and Docker Compose
- Apache HBase cluster (or use Docker setup)
- Apache Zookeeper (typically bundled with HBase)

## Quick Start

### Using Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/Ayaindeed/hbase_sqoop_crud.git
   cd hbase_sqoop_crud
   ```

2. **Build and run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

   This will:
   - Build the Java application
   - Create a containerized environment
   - Connect to HBase cluster via the external network `hbase_pj_default`

### Manual Setup

1. **Build the application**
   ```bash
   mvn clean package
   ```

2. **Run the application**
   ```bash
   java -jar target/hbaseSqoopCrud-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

## Configuration

The application connects to HBase using the following default configuration:

- **HBase ZooKeeper Quorum**: `hbase` (configurable via environment variable)
- **ZooKeeper Port**: `2181`
- **Table Name**: `flights_hbase`
- **Column Family**: `cf`

### Environment Variables

- `HBASE_ZOOKEEPER_QUORUM`: HBase ZooKeeper server hostname/IP
- `HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT`: ZooKeeper client port

## Usage Examples

The application demonstrates the following operations:

### Create a Flight Record
```java
// Create a new flight record
String rowKey = "20250630_1980";
String qualifier = "AIRLINE";
String value = "Delta";
flightCreate.putFlight(rowKey, qualifier, value);
```

### Read Flight Information
```java
// Retrieve airline information for a specific flight
String airline = flightRead.getFlightValue(rowKey, qualifier);
System.out.println("AIRLINE: " + airline);
```

### Delete Operations
```java
// Delete a specific column
FlightDelete.deleteColumn(rowKey, qualifier);

// Delete entire row
FlightDelete.deleteRow(rowKey);
```

## Project Structure

```
hbase_sqoop_crud/
├── src/
│   └── main/
│       ├── java/org/hsc/
│       │   ├── MainApp.java          # Main application entry point
│       │   ├── HBaseConnection.java  # HBase connection management
│       │   ├── flightCreate.java     # Create operations
│       │   ├── flightRead.java       # Read operations
│       │   └── FlightDelete.java     # Delete operations
│       └── resources/
│           └── log4j.properties      # Logging configuration
├── Dockerfile                        # Docker container definition
├── docker-compose.yml               # Docker Compose configuration
├── pom.xml                          # Maven dependencies and build config
└── README.md                        # This file
```

## Class Overview

### MainApp
The main entry point that demonstrates all CRUD operations in sequence.

### HBaseConnection
Manages HBase connections using connection pooling and proper resource cleanup.

### flightCreate
Handles insertion of new flight data into HBase tables.

### flightRead
Provides methods to retrieve flight information from HBase.

### FlightDelete
Implements deletion operations for both individual columns and entire rows.

## HBase Table Schema

- **Table Name**: `flights_hbase`
- **Column Family**: `cf`
- **Row Key Format**: `YYYYMMDD_FlightNumber` (e.g., `20250630_1980`)
- **Qualifiers**: `AIRLINE`, `DEPARTURE`, `ARRIVAL`, etc.

## Development

### Building the Project
```bash
# Compile only
mvn compile

# Run tests
mvn test

# Create JAR with dependencies
mvn clean package
```

### Docker Development
```bash
# Build Docker image
docker build -t hbase-crud-app .

# Run container
docker run -p 5005:5005 --network hbase_pj_default hbase-crud-app
```

## Dependencies

Key dependencies include:

- **HBase Client** (2.4.12): For HBase operations
- **Hadoop Common** (2.10.0): Core Hadoop functionality
- **SLF4J + Log4j**: For logging
- **Maven Assembly Plugin**: For creating executable JAR

## Troubleshooting

### Common Issues

1. **Connection Refused**: Ensure HBase and ZooKeeper services are running
2. **Table Not Found**: Create the `flights_hbase` table in HBase before running
3. **Network Issues**: Verify Docker network configuration if using containers

### HBase Table Creation

Create the required table in HBase shell:
```bash
create 'flights_hbase', 'cf'
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
