package model.controller;

import model.service.IProductDao;
import model.service.IUserDao;
import model.service.ProductDao;
import model.service.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private IUserDao iUserDao;
    IProductDao iProductDao;

    @Override
    public void init() throws ServletException {
        iUserDao = new UserDao();
        iProductDao = new ProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password= req.getParameter("password");

        if(iUserDao.checkUserExists(username, password)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/products");
            requestDispatcher.forward(req,resp);
        }else{
            req.setAttribute("err","Tài khoản này không hợp lệ !");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/product/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

}




