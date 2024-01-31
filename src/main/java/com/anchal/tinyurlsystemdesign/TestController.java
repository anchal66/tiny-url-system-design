package com.anchal.tinyurlsystemdesign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {
    private final TinyUrlService tinyUrlService;
    @Autowired
    public TestController(TinyUrlService tinyUrlService) {
        this.tinyUrlService = tinyUrlService;
    }

    @GetMapping("/{url}")
    public ResponseEntity<String> getUrl(@PathVariable(value = "url") String url) {
        return ResponseEntity.ok(tinyUrlService.getUrl(url));
    }
    @PostMapping
    public ResponseEntity<String> hashUrl(@RequestBody UrlReqest urlRequest){
        return ResponseEntity.ok(tinyUrlService.saveUrl(urlRequest));
    }
}
