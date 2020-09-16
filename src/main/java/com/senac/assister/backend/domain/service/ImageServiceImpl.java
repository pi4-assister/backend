package com.senac.assister.backend.domain.service;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.io.Files;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.exception.InvalidImageExtensionException;
import com.senac.assister.backend.domain.security.Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class ImageServiceImpl implements ImageService {

    private static Storage storage;

    @Value("${GCP_BUCKET}")
    private String bucketName;

    public ImageServiceImpl() {
    }

    @Override
    public String upload(MultipartFile file, Customer customer) {
        validateImageExtension(file.getOriginalFilename());

        validateCredentials();

        String generatedImageName = Hash.convertToMd5(customer.getPersonIdentifier() + customer.getCreatedAt());

        File convertedFile = convertMultiPartToFile(file);

        try {
            BlobInfo blobInfo = storage
                    .create(BlobInfo
                                    .newBuilder(bucketName, generatedImageName)
                                    .setAcl(new ArrayList<>(
                                            Collections.singletonList(
                                                    Acl.of(Acl.User.ofAllUsers(),
                                                            Acl.Role.READER)))).build(),
                            Files.toByteArray(convertedFile));
            return blobInfo.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException("Error on uploading image.");
        }
    }

    private File convertMultiPartToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());

        try (FileOutputStream stream = new FileOutputStream(convertedFile)) {
            stream.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error converting image.");
        }

        return convertedFile;
    }

    private void validateImageExtension(String fileName) {
        if (!fileName.matches("([^\\s]+(\\.(?i)(jpe?g|png))$)")) {
            throw new InvalidImageExtensionException(fileName);
        }
    }

    private void validateCredentials() {
        storage = StorageOptions.getDefaultInstance().getService();
    }
}
