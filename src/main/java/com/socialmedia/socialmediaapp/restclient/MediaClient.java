package com.socialmedia.socialmediaapp.restclient;

import com.socialmedia.socialmediaapp.dto.MediaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "media-service")
public interface MediaClient {

    @PostMapping(value = "/media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadMedia(
            @RequestPart("file") MultipartFile file,
            @RequestPart("userId") Long userId,
            @RequestPart("postId") Long postId,
            @RequestPart("mediaType") String mediaType,
            @RequestPart("category") String category
    );
}
