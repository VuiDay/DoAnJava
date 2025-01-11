package com.example.demo.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class ImageService {
	@Autowired
    private Cloudinary cloudinary;

    public Map<String, Object> upload(MultipartFile file) throws IOException {
        // Chuyển MultipartFile → File tạm trên server
        File convFile = convertMultiPartToFile(file);

        // Tham số upload (folder, resize, crop, v.v. nếu cần)
        Map<String, Object> params = ObjectUtils.asMap(
                "folder", "articles" 
        );

        // Gửi file lên Cloudinary
        Map<String, Object> uploadResult = cloudinary.uploader().upload(convFile, params);

        // Xóa file tạm
        convFile.delete();

        return uploadResult;
    }

    // Hàm chuyển MultipartFile thành File tạm
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }
}
