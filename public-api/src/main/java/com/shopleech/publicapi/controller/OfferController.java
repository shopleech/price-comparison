package com.shopleech.publicapi.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.shopleech.publicapi.bll.service.OfferService;
import com.shopleech.publicapi.bll.service.PriceService;
import com.shopleech.publicapi.dto.v1.OfferDTO;
import com.shopleech.publicapi.dto.v1.PriceDTO;
import com.shopleech.publicapi.dto.v1.mapper.OfferMapper;
import com.shopleech.publicapi.dto.v1.mapper.PriceMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahto Jalak
 * @since 01.04.2023
 */
@RestController
@RequestMapping("/v1/offer")
@Tag(name = "Offer controller", description = "Endpoint")
public class OfferController {
    Logger logger = LoggerFactory.getLogger(OfferController.class);

    @Value("${aws.bucket.name}")
    private String bucketName;
    @Value("${aws.bucket.folder}")
    private String bucketFolderName;

    @Autowired
    private AmazonS3 s3;
    @Autowired
    private OfferService offerService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private OfferMapper offerMapper;
    @Autowired
    private PriceMapper priceMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = offerService.getAll();
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getAllByProductId(@PathVariable(value = "id") Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var items = offerService.getAllByProductId(id);
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(items));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = offerService.get(id);
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody OfferDTO offerDTO) {
        // image
//        if (offerDTO.getProductImage() != null &&
//                !offerDTO.getProductImage().isEmpty() && !offerDTO.getBarcode().isEmpty()) {
//            InputStream inputStream = null;
//
//            try {
//                logger.info(offerDTO.getProductImage());
//                String filename = offerDTO.getBarcode() + ".png";
//                String str = offerDTO.getProductImage();
//                byte[] imagedata = DatatypeConverter.parseBase64Binary(str.substring(str.indexOf(",") + 1));
//                ByteArrayOutputStream os = new ByteArrayOutputStream();
//                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagedata));
//                ImageIO.write(bufferedImage, "png", os);
//                byte[] buffer = os.toByteArray();
//                InputStream is = new ByteArrayInputStream(buffer);
//
//                // byte[] data = Base64.decodeBase64(offerDTO.getProductImage());
//
//                ObjectMetadata objectMetadata = new ObjectMetadata();
//                objectMetadata.setContentLength(buffer.length);
//                objectMetadata.setContentType("image/png");
//                //objectMetadata.setCacheControl("public, max-age=31536000");
//
//                //PutObjectRequest req = new PutObjectRequest(bucketName, bucketFolderName + filename,
//                //        new ByteArrayInputStream(bufferedImage), objectMetadata);
//                //PutObjectResult response = s3.putObject(req);
//                ObjectMetadata meta = new ObjectMetadata();
//                meta.setContentLength(imagedata.length);
//                s3.putObject(new PutObjectRequest(bucketName, bucketFolderName + filename, is, meta));
//
////                s3.setObjectAcl(bucketName, bucketFolderName + filename, CannedAccessControlList.PublicRead);
////                S3Object object = s3.getObject(bucketName, bucketFolderName + filename);
////                logger.info(object.getKey());
////
////                URL fileUrl = s3.getUrl(bucketName, bucketFolderName + filename);
////                logger.info(fileUrl.getFile());
//            } catch (Exception e) {
//                logger.error(MessageFormat.format("Upload error: {0}", e.getMessage()));
//            }
//        }

        Map<String, Object> responseMap = new HashMap<>();
        try {
            // offer data
            var item = offerService.add(
                    offerMapper.mapToEntity(offerDTO));
            var newPrice = new PriceDTO();
            newPrice.setOfferId(item.getId());
            newPrice.setAmount(offerDTO.getPrice().getAmount());
            var priceItem = priceService.add(
                    priceMapper.mapToEntity(newPrice));
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestPart(name = "multipartfile", required = true) MultipartFile multipartfile,
            String barcode) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = offerService.upload(multipartfile, barcode);
            responseMap.put("error", false);
            responseMap.put("details", item);
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody OfferDTO offerDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = offerService.update(id, offerMapper.mapToEntity(offerDTO));
            responseMap.put("error", false);
            responseMap.put("details", offerMapper.mapToDto(item));
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable(value = "id") Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            var item = offerService.remove(id);
            responseMap.put("error", false);
            responseMap.put("details", item);
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            responseMap.put("error", true);
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(500).body(responseMap);
        }
    }
}
