package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String upload(MultipartFile file, Customer customer);
}
