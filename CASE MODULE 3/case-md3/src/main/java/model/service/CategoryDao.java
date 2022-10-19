package model.service;

import model.Category;
import model.connection.ConnectionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends ConnectionMySQL implements ICategoryDao {


    private static final String SELECT_ALL_CATEGORY = "SELECT * FROM case_md3.category;";
    private static final String SELECT_CATEGORY = "SELECT * FROM category where cID = ?;";



    @Override
    public void insertCategory(Category category) throws SQLException {

    }

    @Override
    public Category selectCategory(int cID) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY);
            preparedStatement.setInt(1, cID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int cIDCategory = rs.getInt("cID");
                String cName = rs.getString("cName");
                Category category = new Category(cIDCategory, cName);
                return category;
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<Category> selectAllCategory() {
        List<Category> listCategory = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllCategory " + preparedStatement);
            while (rs.next()){
                int cID = rs.getInt("cID");
                String cName = rs.getString("cName");
                Category category = new Category(cID, cName);
                listCategory.add(category);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }

        return listCategory;
    }

    @Override
    public boolean deleteCategory(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateCategory(Category category) throws SQLException {
        return false;
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
