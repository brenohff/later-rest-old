package br.com.brenohff.later.service;

import br.com.brenohff.later.service.exceptions.FileException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private ImageService imageService;

    @Value("${s3.bucket}")
    private String bucket;

    @Value("${img.event.width.size}")
    private Integer width;

    @Value("${img.event.height.size}")
    private Integer height;

    URI uploadFile(MultipartFile multipartFile) {
        BufferedImage bufferedImage = imageService.getJpgImageFromFile(multipartFile);
        bufferedImage = imageService.resize(bufferedImage, width, height);
        return uploadFile(imageService.getInputStream(bufferedImage, "jpg"));
    }

    private URI uploadFile(InputStream inputStream) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image");
            String fileName = generateFileName() + ".jpg";

            LOG.info("Iniciando upload do arquivo " + fileName + "...");
            s3Client.putObject(bucket, fileName, inputStream, metadata);
            LOG.info("Upload finalizado! " + fileName);

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

    void deleteFile(String fileName) {
        try {
            LOG.info("Deletando arquivo: " + fileName + "...");
            s3Client.deleteObject(bucket, fileName.substring(fileName.length() - 14));
            LOG.info("Arquivo " + fileName + " deletado com sucesso!");
        } catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());
            throw new FileException("AmazonServiceException: " + e.getErrorMessage());
        }
    }

    private String generateFileName() {
        String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder armazenaChaves = new StringBuilder();
        int index;
        for (int i = 0; i < 10; i++) {
            index = random.nextInt(letras.length());
            armazenaChaves.append(letras, index, index + 1);
        }
        return armazenaChaves.toString();
    }

}
