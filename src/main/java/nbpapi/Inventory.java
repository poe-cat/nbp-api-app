package nbpapi;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Inventory {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:inventory.db";

    private Connection connection;
    private Statement statement;

    public Inventory() {

        try {
            Class.forName(Inventory.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC.");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Nie można nawiązać połączenia.");
            e.printStackTrace();
        }

        createTables();
    }

    public boolean createTables() {
        String createProducts = "CREATE TABLE IF NOT EXISTS products " +
                "(productId INTEGER PRIMARY KEY AUTOINCREMENT, productName varchar(255), " +
                "postingDate varchar(255), priceUSD double, pricePLN double)";

        try {
            statement.execute(createProducts);
        } catch (SQLException e) {
            System.err.println("Błąd przy tworzeniu tabeli.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addProduct(String productName, String postingDate, double priceUSD, double pricePLN) {

        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into products values (NULL, ?, ?, ?, ?);");
            prepStmt.setString(1, productName);
            prepStmt.setString(2, postingDate);
            prepStmt.setDouble(3, priceUSD);
            prepStmt.setDouble(4, pricePLN);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy dodawaniu produktu");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Product> selectProducts() {

        List<Product> productsList = new LinkedList<Product>();

        try {
            ResultSet result = statement.executeQuery("SELECT * FROM products");
            int productId;
            String productName, postingDate;
            double priceUSD, pricePLN;

            while(result.next()) {
                productId = result.getInt("productId");
                productName = result.getString("productName");
                postingDate = result.getString("postingDate");
                priceUSD = result.getDouble("priceUSD");
                pricePLN = result.getDouble("pricePLN");
                productsList.add(new Product(productId, productName, postingDate, priceUSD, pricePLN));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return productsList;
    }

    public List<Product> findProductsByName(String name) {

        List<Product> productsList = new LinkedList<Product>();

        try {
            ResultSet result = statement.executeQuery("SELECT * FROM products " +
                    "WHERE productName LIKE '%" + name + "%'");
            int productId;
            String productName, postingDate;
            double priceUSD, pricePLN;

            while(result.next()) {
                productId = result.getInt("productId");
                productName = result.getString("productName");
                postingDate = result.getString("postingDate");
                priceUSD = result.getDouble("priceUSD");
                pricePLN = result.getDouble("pricePLN");
                productsList.add(new Product(productId, productName, postingDate, priceUSD, pricePLN));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return productsList;
    }

    public List<Product> searchProductByDate(String date) {

        List<Product> productsList = new LinkedList<Product>();
        return productsList;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknięciem połączenia");
            e.printStackTrace();
        }
    }
}