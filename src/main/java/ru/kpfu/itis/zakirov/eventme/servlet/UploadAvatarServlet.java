package ru.kpfu.itis.zakirov.eventme.servlet;

import com.cloudinary.Cloudinary;
import jakarta.servlet.http.*;
import ru.kpfu.itis.zakirov.eventme.dto.UserDto;
import ru.kpfu.itis.zakirov.eventme.service.UserService;
import ru.kpfu.itis.zakirov.eventme.service.impl.UserServiceImpl;
import ru.kpfu.itis.zakirov.eventme.util.CloudinaryUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@WebServlet("/uploadAvatar")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024) // 5 MB
public class UploadAvatarServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        Part filePart = req.getPart("file");
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileContent = filePart.getInputStream();
            File tempFile = File.createTempFile("upload_", ".tmp");
            Files.copy(fileContent, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            try {
                Map uploadResult = cloudinary.uploader().upload(tempFile, Map.of());
                String avatarUrl = (String) uploadResult.get("secure_url");

                UserDto user = userService.getByLogin(username);
                userService.updateAvatar(user.getUsername(), avatarUrl);

                resp.sendRedirect("/profile");
            } catch (Exception e) {
                throw new RuntimeException("Error uploading avatar", e);
            } finally {
                tempFile.delete();
            }
        }
    }
}
