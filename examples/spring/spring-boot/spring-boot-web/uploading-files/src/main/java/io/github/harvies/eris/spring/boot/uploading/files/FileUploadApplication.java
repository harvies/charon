package io.github.harvies.eris.spring.boot.uploading.files;


import io.github.harvies.eris.spring.boot.uploading.files.storage.StorageProperties;
import io.github.harvies.eris.spring.boot.uploading.files.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "io.github.harvies.eris.spring.boot.uploading.files")
@EnableConfigurationProperties(StorageProperties.class)
public class FileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
