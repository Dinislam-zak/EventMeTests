package ru.kpfu.itis.zakirov.eventme.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ru.kpfu.itis.zakirov.eventme.dto.UserLoginDto;
import ru.kpfu.itis.zakirov.eventme.service.UserService;
import ru.kpfu.itis.zakirov.eventme.service.impl.UserServiceImpl;
import ru.kpfu.itis.zakirov.eventme.util.PasswordUtil;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        try {
            userService = new UserServiceImpl();
        } catch (Exception e) {
            System.err.println("Ошибка инициализации UserService: " + e.getMessage());
            userService = null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ftl/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (userService == null) {
            req.setAttribute("errorMessage", "Сервис временно недоступен, попробуйте позже.");
            req.getRequestDispatcher("/ftl/login.ftl").forward(req, resp);
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserLoginDto userLoginDto = userService.getUserLoginDto(username);

        if (userLoginDto == null) {
            req.setAttribute("errorMessage", "Пользователь с таким именем не найден, попробуйте еще раз!");
            req.getRequestDispatcher("/ftl/login.ftl").forward(req, resp);
        } else {
            if (userLoginDto.getPassword().equals(PasswordUtil.encrypt(password))) {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(60 * 60);
                resp.sendRedirect("/profile");
            } else {
                req.setAttribute("errorMessage", "Неверный пароль! Попробуйте еще раз.");
                req.getRequestDispatcher("/ftl/login.ftl").forward(req, resp);
            }
        }
    }
}
