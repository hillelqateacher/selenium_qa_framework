package core.utils;

import core.dto.ProductDetailsDTO;

import java.sql.*;

public class DbHelper {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    private DbHelper() {
    }

    public static void connectToDb(final String userName, final String password, final String connectionString) throws SQLException {
        connection = DriverManager.getConnection(connectionString, userName, password);
    }

    public static void executeQuery(final String sqlQuery) throws SQLException {
        if (!connection.isClosed()) {
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
        }
    }

    public static ProductDetailsDTO mapDbData() throws SQLException {
        final ProductDetailsDTO detailsDTO = new ProductDetailsDTO();
        final ResultSetMetaData metaData = preparedStatement.getMetaData();
        detailsDTO.setTitle(resultSet.getString("Title"));
        detailsDTO.setQty(resultSet.getString("Quantity"));
        detailsDTO.setPrice(resultSet.getString("Price"));
        detailsDTO.setTotal(resultSet.getString("Total"));
        detailsDTO.setColor(resultSet.getString("Colour"));
        detailsDTO.setSize(resultSet.getString("Size"));
        return detailsDTO;
    }
}
