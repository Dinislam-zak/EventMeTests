package ru.kpfu.itis.zakirov.eventme.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.kpfu.itis.zakirov.eventme.dto.UserDto;
import ru.kpfu.itis.zakirov.eventme.entity.User;
import ru.kpfu.itis.zakirov.eventme.service.UserService;
import ru.kpfu.itis.zakirov.eventme.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        UserDto user = userService.getByLogin(username);

        req.setAttribute("user", user);
        req.getRequestDispatcher("/ftl/profile.ftl").forward(req, resp);
    }
}
