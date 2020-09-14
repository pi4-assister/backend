package com.senac.assister.backend.domain.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String upload(MultipartFile file);

    String get(String imageId);
}
