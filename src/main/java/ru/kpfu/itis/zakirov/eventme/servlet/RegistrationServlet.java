package ru.kpfu.itis.zakirov.eventme.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.zakirov.eventme.service.UserService;
import ru.kpfu.itis.zakirov.eventme.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ftl/registration.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (confirmPassword.equals(password)) {
            userService.register(username, email, password, null);
            resp.sendRedirect("/profile");
        } else {
            req.setAttribute("errorMessage", "Пароли не совпадают. Пожалуйста, попробуйте еще раз.");
            try {
                req.getRequestDispatcher("/ftl/registration.ftl").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}

