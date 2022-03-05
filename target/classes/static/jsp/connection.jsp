<%@ page import="java.sql.*" %>
<%
    // Read RDS connection information from the environment
    String dbName = System.getProperty("find_user");
    String userName = System.getProperty("admin");
    String password = System.getProperty("chicken!1");
    String hostname = System.getProperty("sortdb.cq4dou6ddhua.us-east-2.rds.amazonaws.com");
    String port = System.getProperty("3306");
    String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
            port + "/" + dbName + "?user=" + userName + "&password=" + password;

    // Load the JDBC driver
    try {
        System.out.println("Loading driver...");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded!");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("Cannot find the driver in the classpath!", e);
    }

    Connection conn = null;
    Statement setupStatement = null;
    Statement readStatement = null;
    ResultSet resultSet = null;
    String results = "";
    int numresults = 0;
    String statement = null;

    try {
        // Create connection to RDS DB instance
        conn = DriverManager.getConnection(jdbcUrl);

        // Create a table and write two rows
        setupStatement = conn.createStatement();
        String createTable = "CREATE TABLE Beanstalk (Resource char(50));";
        String insertRow1 = "INSERT INTO Beanstalk (Resource) VALUES ('EC2 Instance');";
        String insertRow2 = "INSERT INTO Beanstalk (Resource) VALUES ('RDS Instance');";

        setupStatement.addBatch(createTable);
        setupStatement.addBatch(insertRow1);
        setupStatement.addBatch(insertRow2);
        setupStatement.executeBatch();
        setupStatement.close();

    } catch (SQLException ex) {
        // Handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    } finally {
        System.out.println("Closing the connection.");
        if (conn != null) try {
            conn.close();
        } catch (SQLException ignore) {
        }
    }

    try {
        conn = DriverManager.getConnection(jdbcUrl);

        readStatement = conn.createStatement();
        resultSet = readStatement.executeQuery("SELECT Resource FROM Beanstalk;");

        resultSet.first();
        results = resultSet.getString("Resource");
        resultSet.next();
        results += ", " + resultSet.getString("Resource");

        resultSet.close();
        readStatement.close();
        conn.close();

    } catch (SQLException ex) {
        // Handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    } finally {
        System.out.println("Closing the connection.");
        if (conn != null) try {
            conn.close();
        } catch (SQLException ignore) {
        }
    }
%>