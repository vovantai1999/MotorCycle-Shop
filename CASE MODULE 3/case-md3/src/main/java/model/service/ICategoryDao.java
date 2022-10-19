package model.service;

import model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDao {
    public void insertCategory(Category category) throws SQLException;

    public Category selectCategory(int cID);

    public List<Category> selectAllCategory();

    public boolean deleteCategory(int cID) throws SQLException;

    public boolean updateCategory(Category category) throws SQLException;
}
