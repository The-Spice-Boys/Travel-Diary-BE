package org.spiceboys.Travel.Diary.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "dkrjxn7tm");
        config.put("api_key", "726541414574841");
        config.put("api_secret", "Lcru33ahjZM1E93NYeuL8QlSZug");
        return new Cloudinary(config);
    };


}
