package com.urlshortener.service;

import com.urlshortener.entity.Url;
import com.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository repo;

    public UrlService(UrlRepository repo) {
        this.repo = repo;
    }

    public Url shorten(String originalUrl) {
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(generateCode());
        url.setClicks(0);
        return repo.save(url);
    }

    public Url getByCode(String code) {
        Url url = repo.findByShortCode(code).orElseThrow();
        url.setClicks(url.getClicks() + 1);
        return repo.save(url);
    }

    private String generateCode() {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(r.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
