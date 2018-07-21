package br.com.brenohff.later.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucket;

    public void uploadFile(String localFilePath) {

        try {
            File file = new File(localFilePath);
            LOG.info("Iniciando upload...");
            s3Client.putObject(new PutObjectRequest(bucket, generateFileName(), file));
            LOG.info("Upload finalizado!");
        } catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            LOG.info("AmazonServiceException: " + e.getMessage());
        }
    }

    private String generateFileName() {
        // Determia as letras que poderão estar presente nas chaves
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
