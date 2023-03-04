package com.poly.datn;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

//Swagger url: http://localhost:8080/swagger-ui/index.html
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API for project", version = "v1.0.0"), security = {
        @SecurityRequirement(name = "bearerAuth") })
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@EnableScheduling
public class BackendApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(BackendApplication.class, args);
        ClassPathResource serviceAccount = new ClassPathResource(
                "image-cloud-98533-firebase-adminsdk-egb09-95daffa97d.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                .setStorageBucket("image-cloud-98533.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
