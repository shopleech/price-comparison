package com.shopleech.publicapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ahto Jalak
 * @since 01.04.2023
 */
@RestController
@RequestMapping("/v1/review")
@Tag(name = "Review controller", description = "Endpoint")
public class ReviewController {
    Logger logger = LoggerFactory.getLogger(ReviewController.class);


}
