package com.example.accountservice.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Configuration
public class AzureBlobConfig {


    @Value("${azure.blob-storage.connection-string}")
    private String connectionString;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Bean
    public BlobServiceClient blobServiceClient() {

        BlobServiceClient blobServiceClient =
                new BlobServiceClientBuilder()
                        .connectionString(connectionString)
                        .buildClient();

        return blobServiceClient;

    }

    @Bean
    public BlobContainerClient blobContainerClient() {

        BlobContainerClient blobContainerClient =
                blobServiceClient()
                        .getBlobContainerClient(containerName);

        return blobContainerClient;

    }
}
