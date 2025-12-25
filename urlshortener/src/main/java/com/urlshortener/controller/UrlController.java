package com.urlshortener.controller;

import com.urlshortener.entity.Url;
import com.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public Url shorten(@RequestParam String url) {
        return service.shorten(url);
    }

    @GetMapping("/{code}")
    public String redirect(@PathVariable String code) {
        return service.getByCode(code).getOriginalUrl();
    }
}
