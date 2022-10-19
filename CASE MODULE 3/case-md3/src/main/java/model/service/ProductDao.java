package model.service;

import model.Product;
import model.connection.ConnectionMySQL;
import model.connection.IConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    private IConnectionMySQL connectionMySQL = new ConnectionMySQL();

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO `products` (`img`, `title`, `quantity`, `price`) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID = "select id,img,title,quantity,price from products where id =?";
    private static final String SELECT_ALL_PRODUCT = "select * from products";
    private static final String DELETE_PRODUCT_SQL = "delete from products where id = ?;";
    private static final String UPDATE_PRODUCT_SQL = "update products set img = ?,title= ?, quantity =?, price= ? where id = ?;";

    private static final String SEARCH_BY_NAME_TYPE = "SELECT * FROM users  WHERE  title LIKE ? OR price LIKE ? OR quantity LIKE ? ; ";

    private static String PRODUCT_EXIST_BY_ID = "" +
            "SELECT COUNT(*) AS COUNT " +
            "FROM `products` AS p " +
            "WHERE p.id = ?;";
    private int noOfRecords;

    public int getNoOfRecords(){
        return noOfRecords;
    }
    @Override
    public Boolean insertProduct(Product product) throws SQLException {
        boolean rowInsert = false;
        System.out.println(INSERT_PRODUCT_SQL);
        try (Connection connection = connectionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getImg());
            preparedStatement.setString(2, product.getTitle());
            preparedStatement.setString(3, product.getQuantity());
            preparedStatement.setString(4, product.getPrice());
            rowInsert =  preparedStatement.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowInsert;
    }

    public List<Product> getNumberPage(int offset, int noOfRecords, String title) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMySQL.getConnection();
        System.out.println("numberpage");

        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM products where title LIKE ? OR price LIKE ? OR quantity LIKE ? limit " + offset + "," + noOfRecords;
        List<Product> list = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, '%' + title + '%');
        ps.setString(2, '%' + title + '%');
        ps.setString(3, '%' + title + '%');
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getString("id"));
            product.setImg(rs.getString("img"));
            product.setTitle(rs.getString("title"));
            product.setQuantity(rs.getString("quantity"));
            product.setPrice(rs.getString("price"));
            list.add(product);
        }
        rs = ps.executeQuery("SELECT FOUND_ROWS()");
        if (rs.next()){
            this.noOfRecords = rs.getInt(1);
        }
        connection.close();
        return list;
    }

    @Override
    public boolean existByProductId(int id) {
        boolean exist = false;
        try {
            Connection connection = connectionMySQL.getConnection();
            PreparedStatement statement = connection.prepareCall(PRODUCT_EXIST_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    @Override
    public Product selectProduct(String id) {
        Product product = null;
        try {
            Connection connection = connectionMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String img = rs.getString("img");
                String title = rs.getString("title");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                product = new Product(id, img, title, quantity, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> selectAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = connectionMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String img = rs.getString("img");
                String title = rs.getString("title");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                products.add(new Product(id, img,title, quantity, price));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override


    public Boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = connectionMySQL.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public Boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = connectionMySQL.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
            statement.setString(1, product.getImg());
            statement.setString(2, product.getTitle());
            statement.setString(3, product.getQuantity());
            statement.setString(4, product.getPrice());
            statement.setString(5, product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    @Override
    public List<Product> searchProduct(String query) {
        List<Product> listProduct = new ArrayList<>();
        try {
            Connection connection = connectionMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareCall(SEARCH_BY_NAME_TYPE);
            preparedStatement.setString(1, '%' + query + '%');
            preparedStatement.setString(2, '%' + query + '%');
            preparedStatement.setString(3, '%' + query + '%');
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String image = rs.getString("img");
                String title = rs.getString("title");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                listProduct.add(new Product(id, image, title, quantity, price));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProduct;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
