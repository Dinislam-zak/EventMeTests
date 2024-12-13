package ru.kpfu.itis.zakirov.eventme.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public final class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", "eventme");
            configMap.put("api_key", "323951582445765");
            configMap.put("api_secret", "1zdqHoFwiZQO29glCK4BnUIGej8");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}