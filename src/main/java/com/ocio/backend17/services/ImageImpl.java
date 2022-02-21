package com.ocio.backend17.services;

import com.ocio.backend17.dao.ImagesDao;
import com.ocio.backend17.entities.Images;
import com.ocio.backend17.utils.DateFormatterSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class ImageImpl implements IImages {
    @Autowired
    ImagesDao imagesDao;
    @Autowired
    DateFormatterSQL dateFormatterSQL;

    @Override
    public Optional<Images> getById(int id) {
        return imagesDao.findById(id);
    }

    @Override
    public List<Images> getAll() {
        return (List<Images>) imagesDao.findAll();
    }

    @Transactional
    @Override
    public int deleteById(int id) {
        if (imagesDao.findById(id).isPresent()) {
            imagesDao.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public Images updloadImage(Images image) {
        DateFormat dateFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        Images imageCreated = image;
        imageCreated.setCreatedAt(dateFormatterSQL.todaySQLFormat());
        imageCreated.setUpdatedAt(dateFormatterSQL.todaySQLFormat());
        return imagesDao.save(image);
    }

    public Resource downloadImage(int id) {
        return null;
    }
}


