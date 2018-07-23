package br.com.brenohff.later.service;

import br.com.brenohff.later.service.exceptions.FileException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucket;

    public URI uploadFile(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        String contentType = multipartFile.getContentType();
        return uploadFile(inputStream, contentType);

    }

    public URI uploadFile(InputStream inputStream, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            String fileName = generateFileName();

            LOG.info("Iniciando upload...");
            s3Client.putObject(bucket, fileName, inputStream, metadata);
            LOG.info("Upload finalizado!");

            return s3Client.getUrl(bucket, fileName).toURI();
        } catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());
            throw new FileException("AmazonServiceException: " + e.getErrorMessage());
        } catch (AmazonClientException e) {
            LOG.info("AmazonServiceException: " + e.getMessage());
            throw new FileException("AmazonServiceException: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI");
        }

    }

    private String generateFileName() {
        String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder armazenaChaves = new StringBuilder();
        int index = -1;
        for (int i = 0; i < 5; i++) {
            index = random.nextInt(letras.length());
            armazenaChaves.append(letras.substring(index, index + 1));
        }
        return armazenaChaves.toString();
    }

}