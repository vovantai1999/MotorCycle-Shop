package model.controller;
import model.User;
import model.service.UserDao;
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

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                    deleteUser(request, response);
                    break;
                default:
                    listNumberPage(request, response);
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    private void listNumberPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        };
        String name = "";
        if (req.getParameter("searchuser") != null) {
            name = req.getParameter("searchuser");
        }
        List<User> listUser = userDao.getNumberPage((page - 1) * recordsPerPage, recordsPerPage, name);
        int noOfRecords = userDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("listUser", listUser);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("searchuser" , name);



        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/list.jsp");
        requestDispatcher.forward(req, resp);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        User user = userDao.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(request, response);
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        User user;
        String fullName = request.getParameter("fullName").trim();
        String userName = request.getParameter("userName").replaceAll(" ", "").toLowerCase();
        String passWord = request.getParameter("passWord").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String address = request.getParameter("address").trim();
        List<String> errors = new ArrayList<>();
        boolean isPassword = ValidateUtil.isPasswordVailid(passWord);
        boolean isPhone = ValidateUtil.isNumberPhoneVailid(phone);
        boolean isEmail = ValidateUtil.isEmailValid(email);

        user = new User(fullName, userName, passWord, email, phone, address);
        if (userName.isEmpty() ||
                passWord.isEmpty() ||
                fullName.isEmpty() ||
                phone.isEmpty() ||
                email.isEmpty() ||
                address.isEmpty() ){
            errors.add("Vui l??ng ??i???n ?????y ????? th??ng tin !");
        }
        if (userName.isEmpty()) {
            errors.add("T??n ng?????i d??ng kh??ng ???????c ????? tr???ng !");
        }
        if (passWord.isEmpty()) {
            errors.add("M???t kh???u kh??ng ???????c ????? tr???ng !");
        }
        if (fullName.isEmpty()) {
            errors.add("H??? v?? t??n kh??ng ???????c ????? tr???ng !");
        }
        if (phone.isEmpty()) {
            errors.add("Phone incorrect input!");
        }
        if (!isPhone) {
            errors.add("S??? ??i???n tho???i kh??ng ????ng ?????nh d???ng !");
        }
        if (userDao.existByPhone(phone)) {
            errors.add("S??? ??i???n tho???i ???? t???n t???i !");
        }
        if (email.isEmpty()) {
            errors.add("?????nh d???ng email kh??ng ????ng !");
        }
        if (!isEmail) {
            errors.add("?????nh d???ng email kh??ng ????ng");
        }

        if (userDao.existsByEmail(email)) {
            errors.add("Email nh???p sai !");
        }

        if (address.isEmpty()) {
            errors.add("?????a ch??? kh??ng ???????c ????? tr???ng !");
        }

        if (userDao.existByUsername(userName)) {
            errors.add("T??n ng?????i d??ng ???? t???n t???i !");
        }
        if (!isPassword) {
            errors.add("M???t kh???u kh??ng ????ng ?????nh d???ng");
        }

        if (errors.size() == 0) {
            user = new User(fullName, userName, passWord, email, phone, address);
            boolean success = false;
            success = userDao.insertUser(user);

            if (success) {
                request.setAttribute("success", true);
            } else {
                request.setAttribute("errors", true);
                errors.add("D??? li???u kh??ng h???p l???, Vui l??ng ki???m tra l???i !");
            }

        }
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<String> errors = new ArrayList<>();
        User user = null;
        String id= request.getParameter("id");

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        boolean isPhone = ValidateUtil.isNumberPhoneVailid(phone);
        boolean isEmail = ValidateUtil.isEmailValid(email);
        user = new User(id, fullName, phone,email, address);
        User userEmail = userDao.selectUser(id);
        String checkEmail = userEmail.getEmail();
        User userPhone = userDao.selectUser(id);
        String checkPhone = userPhone.getPhone();
        if (fullName.isEmpty() ||
                phone.isEmpty() ||
                email.isEmpty() ||
                address.isEmpty()) {
            errors.add("Vui l??ng ??i???n ?????y ????? th??ng tin !");
        }

        if (fullName.isEmpty()) {
            errors.add("H??? v?? T??n kh??ng ???????c ????? tr???ng !");
        }
        if (phone.isEmpty()) {
            errors.add("S??? ??i???n tho???i kh??ng ???????c ????? tr???ng!");
        }
        if (email.isEmpty()) {
            errors.add("email kh??ng ???????c ????? tr???ng!");
        }
        if (!isEmail) {
            errors.add("email kh??ng ????ng ?????nh d???ng !");
        }

        if (userDao.existsByEmail(email) && !email.equals(checkEmail)){
            errors.add("Email ???? t???n t???i ");
        }
        if (!isPhone) {
            errors.add("??inh d???ng s??? ??i???n tho???i kh??ng h???p l??? !");
        }
        if (userDao.existByPhone(phone) && !phone.equals(checkPhone)){
            errors.add("S??? ??i???n tho???i ???? t???n t???i !");
        }
        if (address.isEmpty()) {
            errors.add("?????a ch??? kh??ng ???????c ????? tr???ng !");
        }


        if (errors.size() == 0) {
            user = new User(id, fullName,email,phone, address);
            boolean success = false;
            success = userDao.updateUser(user);

            if (success) {
                request.setAttribute("success", true);
            } else {
                request.setAttribute("errors", true);
                errors.add("D??? li???u kh??ng h???p l???, Vui l??ng ki???m tra l???i !");
            }
        }
        else {
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");

        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(id);

        List<User> listUser = userDao.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listUser = null;
        String name = "";
        if (req.getParameter("searchuser") != null) {
            name = req.getParameter("searchuser");

            listUser = userDao.searchUser(name);
        } else {
            listUser = userDao.selectAllUsers();
        }
        req.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(req, resp);
    }



//    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        boolean checkUser = userDao.existByUsernameLogin(username, password);
//        if (username.isEmpty() && password.isEmpty()) {
//            req.setAttribute("error", "Please enter username and password");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
//            dispatcher.forward(req, resp);
//        }
//        if (username.isEmpty()) {
//            req.setAttribute("error", "Please enter username");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
//            dispatcher.forward(req, resp);
//        }
//        if (password.isEmpty()) {
//            req.setAttribute("error", "Please enter password");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
//            dispatcher.forward(req, resp);
//        }
//        if (checkUser) {
//            resp.sendRedirect("/users");
//        }
//        else {
//            req.setAttribute("error", "Account not exist");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
//            dispatcher.forward(req, resp);
//        }
//
//    }
}

