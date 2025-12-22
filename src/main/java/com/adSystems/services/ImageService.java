package com.adSystems.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(String listingId, MultipartFile file);
}
