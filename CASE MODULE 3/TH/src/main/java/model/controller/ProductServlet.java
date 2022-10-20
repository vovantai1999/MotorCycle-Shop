package model.controller;

import model.Category;
import model.Product;
import model.service.CategoryDao;
import model.service.ICategoryDao;
import model.service.ProductDao;
import model.utils.ValidateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductDao productDAO;

    public void init() {
        productDAO = new ProductDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProduct(request, response);
                    break;
                case "edit":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                default:
                    listNumberPage(request, response);
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
//    public void init() {
//        productDAO = new ProductDao();
//        ICategoryDao =new CategoryDao();
//        List<Category> categoryList = CategoryDao.selectAllCategory();
//        if (getServletContext().getAttribute("categoryList") == null) {
//            getServletContext().setAttribute("categoryList", categoryList);
//        }
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                default:
                    listNumberPage(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void listNumberPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        };
        String title = "";
        if (req.getParameter("searchproduct")!=null) {
            title = req.getParameter("searchproduct");
        }
        List<Product> listProduct = productDAO.getNumberPage((page - 1) * recordsPerPage, recordsPerPage, title);
        int noOfRecords = productDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("listProduct", listProduct);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("searchproduct", title);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/list.jsp");
        requestDispatcher.forward(req, resp);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        Product existingProduct = productDAO.selectProduct(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        request.setAttribute("product", existingProduct);
        dispatcher.forward(request, response);

    }


    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Product product;
        String img = request.getParameter("img").trim();
        String title = request.getParameter("title").trim();
        String quantity = request.getParameter("quantity").trim();
        String price = request.getParameter("price").trim();
        List<String> errors = new ArrayList<>();
        boolean isPrice = ValidateUtil.isNumberVailid(price);
        boolean isQuantity = ValidateUtil.isNumberVailid(quantity);

        product = new Product(img, title, quantity,price);



        if (
                title.isEmpty() ||
                        price.isEmpty() ||
                        quantity.isEmpty()
        ) {
            errors.add("Please complete all information!");
        }

        if (title.isEmpty()) {
            errors.add("Name product cannot be left blank!");
        }
        if (price.isEmpty()) {
            errors.add("Price cannot be left blank!");
        }
        if (quantity.isEmpty()) {
            errors.add("Quantity cannot be left blank!");
        }

        double checkPrice= 1.0;
        try {
            if (isPrice)
                checkPrice = Double.parseDouble(price);
        } catch (Exception e) {
            errors.add("Invalid price format!");
        }
        if (!isPrice || checkPrice < 0 || checkPrice > 1000000000) {
            errors.add("Price greater than 0 and less than 100000000!");
        }

        int checkQuantity= 1;
        try {
            if (isQuantity)
                checkQuantity = Integer.parseInt(quantity);
        } catch (Exception e) {
            errors.add("Invalid quantity format!");
        }
        if (!isQuantity || checkQuantity < 0 || checkQuantity > 100) {
            errors.add("số lượng lớn hơn 0 và nhỏ hơn 100 !");
        }


        if (errors.isEmpty()) {
            product = new Product( img,title, quantity,price );
            boolean success = false;
            success = productDAO.insertProduct(product);
            if (success) {
                request.setAttribute("success", true);
            } else {
                request.setAttribute("errors", true);
                errors.add("Dữ liệu không hợp lệ vui lòng kiểm tra lại!");
            }
        }
        else {
            request.setAttribute("errors", errors);
            request.setAttribute("inserProduct", product);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(request, response);
    }


    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<String> errors = new ArrayList<>();
        Product product;
        String id= request.getParameter("id").trim();
        boolean isId = ValidateUtil.isIntValid(id);
        int checkId=0;
        if (isId) {
            checkId=Integer.parseInt(id);
        }
        if (!productDAO.existByProductId(checkId)) {
            errors.add("Id must exist!");
        }

        if (!isId) {
            errors.add("ID phải là một số nguyên!");
        }
        String img = request.getParameter("img").trim();
        String title = request.getParameter("title").trim();
        String quantity = request.getParameter("quantity").trim();
        String price = request.getParameter("price").trim();
        boolean isPrice = ValidateUtil.isNumberVailid(price);
        boolean isQuantity = ValidateUtil.isNumberVailid(quantity);
        product = new Product(id, img, title, quantity,price );

        if (
                title.isEmpty() ||
                        quantity.isEmpty() ||
                        price.isEmpty()
        ) {
            errors.add("Vui lòng điền đầy đủ thông tin !");
        }
        if (title.isEmpty()) {
            errors.add("Tên không được để trống !");
        }
        if (price.isEmpty()) {
            errors.add("Số lượng không được để trống !");
        }
        if (quantity.isEmpty()) {
            errors.add(" Giá không được để trống !");
        }

        double checkPrice=1.0;
        try {
            if (isPrice)
                checkPrice = Double.parseDouble(price);
        } catch (Exception e) {
            errors.add("Định dạng giá không hợp lệ !");
        }
        if (!isPrice || checkPrice < 0 || checkPrice > 2000000000) {
            errors.add("Giá lớn hơn 0 và nhỏ hơn 2000000000!");
        }

        int checkQuantity=1;
        try {
            if (isQuantity)
                checkQuantity = Integer.parseInt(quantity);
        } catch (Exception e) {
            errors.add("Định dạng số lượng không hợp lệ!");
        }
        if (!isQuantity || checkQuantity < 0 || checkQuantity  > 100 ) {
            errors.add("Số lượng phải lớn hơn 0 và nhỏ hơn 100!");
        }

        if (errors.size() == 0) {
            product = new Product(id, img, title, quantity, price);
            boolean success = false;
            success = productDAO.updateProduct(product);
            if (success) {
                request.setAttribute("success", true);
            } else {
                request.setAttribute("errors", true);
                errors.add("");
            }
        }
        else  {
            request.setAttribute("errors", errors);
            request.setAttribute("product", product);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);

        List<Product> listProduct = productDAO.selectAllProducts();
        request.setAttribute("listProduct", listProduct);

        response.sendRedirect("/products");
    }
}
