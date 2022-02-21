package com.ocio.backend17.security.google;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocio.backend17.config.IConfigImpl;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleValidation {

    private Logger logger = LoggerFactory.getLogger(GoogleValidation.class);

    CloseableHttpClient httpClient = HttpClients.createDefault();

    @Autowired
    IConfigImpl iConfig;

    public GoogleResponseDto validateToken(String token) {
        try {
            GoogleRequestDto googleRequestDto = new GoogleRequestDto(iConfig.getGoogleApiUrl(), token);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(googleRequestDto.getRequestCompleteUrl());
            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {

                String str = EntityUtils.toString(response.getEntity(), "UTF-8");
                ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false);
                GoogleResponseDto googleResponseDto = om.readValue(str, GoogleResponseDto.class);
                return googleResponseDto;

            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Bean
    public CloseableHttpClient closeableHttpClient() {
        return new CloseableHttpClient() {
            @Override
            protected CloseableHttpResponse doExecute(HttpHost httpHost, HttpRequest httpRequest,
                                                      HttpContext httpContext) throws IOException, ClientProtocolException {
                return null;
            }

            @Override
            public void close() throws IOException {

            }

            @Override
            public HttpParams getParams() {
                return null;
            }

            @Override
            public ClientConnectionManager getConnectionManager() {
                return null;
            }
        };
    }
}
