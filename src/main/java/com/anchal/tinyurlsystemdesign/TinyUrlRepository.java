package com.anchal.tinyurlsystemdesign;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TinyUrlRepository extends JpaRepository<TinyUrl, Long> {
    List<TinyUrl> findTinyUrlByShortUrl(String shortUrl);
}
