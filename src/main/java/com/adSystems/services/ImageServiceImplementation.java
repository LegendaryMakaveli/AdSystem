package com.adSystems.services;

import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@AllArgsConstructor
@Service
public class ImageServiceImplementation implements ImageService{
    @Autowired
    private Cloudinary cloudinary;


    @Override
    public String uploadImage(String listingId, MultipartFile file) {
        try {
            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    Map.of(
                            "folder", "listings/" + listingId,
                            "resource_type", "image"
                    )
            );
            return result.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException("Image upload failed");
        }
    }
}
