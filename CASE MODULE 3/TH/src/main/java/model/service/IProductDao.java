package model.service;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {
    Boolean insertProduct(Product product) throws SQLException;

    Product selectProduct(String id);

    List<Product> selectAllProducts();

    Boolean deleteProduct(int id) throws SQLException;

    Boolean updateProduct(Product product) throws ClassNotFoundException,SQLException;

    List<Product> searchProduct(String name);

    List<Product> getNumberPage(int offset, int noOfRecords, String name) throws ClassNotFoundException, SQLException;

    boolean existByProductId(int id);
}
