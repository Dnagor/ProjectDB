package servlets;

import com.projectdb.domain.User;
import com.projectdb.service.UserService;
import com.projectdb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userService.readUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            request.setAttribute("email", email);
            request.getRequestDispatcher("/cabinet.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
