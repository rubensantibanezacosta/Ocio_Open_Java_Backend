package com.ocio.backend17.imgbb;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
import com.ocio.backend17.config.IConfigImpl;
import com.ocio.backend17.dto.ImgbbDto;
import com.ocio.backend17.security.google.GoogleRequestDto;
import com.ocio.backend17.security.google.GoogleResponseDto;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Service
public class ImagebbImpl implements IImgbb {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    private Logger logger = LoggerFactory.getLogger(IImgbb.class);

    @Autowired
    IConfigImpl iConfig;

    @Override
    public String postImage(MultipartFile file) {
        CloseableHttpResponse response;
        try {
            Files.deleteIfExists(Path.of(System.getProperty("user.dir") + "/src/assets/image.jpg"));
            Files.copy(file.getInputStream(), Path.of(System.getProperty("user.dir") + "/src/assets/image.jpg"));
            File imageToUpload=new File(System.getProperty("user.dir") + "/src/assets/image.jpg");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(iConfig.imgbbUrl());
            URI uri = new URIBuilder(httpPost.getURI())
            .addParameter("key",iConfig.imgbbApiKey())
            .build();
           
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("image",new FileInputStream(imageToUpload), ContentType.APPLICATION_OCTET_STREAM, imageToUpload.getName());
          
            HttpEntity multipart=builder.build();
            httpPost.setEntity(multipart);
            httpPost.setURI(uri);
            response = httpClient.execute(httpPost);
           
           
            if (response.getStatusLine().getStatusCode() == 200) {

                String str = EntityUtils.toString(response.getEntity(), "UTF-8");
                ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false);
                ImgbbDto imgbbDto = om.readValue(str, ImgbbDto.class);
                return imgbbDto.getData().getUrl();
            }
        } catch (Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        logger.debug(String.valueOf(response.getStatusLine().getStatusCode()));
        return null;
    }

}
