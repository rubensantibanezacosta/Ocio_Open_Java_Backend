package com.ocio.backend17.imgbb;

import org.springframework.web.multipart.MultipartFile;

public interface IImgbb {
    public String postImage(MultipartFile file);
}
