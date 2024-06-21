package com.anchal.tinyurlsystemdesign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TinyUrlService {
    private final TinyUrlRepository tinyUrlRepository;

    @Autowired
    public TinyUrlService(TinyUrlRepository tinyUrlRepository) {
        this.tinyUrlRepository = tinyUrlRepository;
    }

    public String getUrl(String url) {
        List<TinyUrl> tinyUrlByShortUrl = tinyUrlRepository.findTinyUrlByShortUrl(url);
        return tinyUrlByShortUrl.get(0).getUrl();
    }

    public String saveUrl(UrlReqest urlReqest) {
        String hashedString = HashUtil.hashString(urlReqest.url());
        hashedString = hashedString.substring(0, 15);
        TinyUrl tinyUrl = TinyUrl.builder().shortUrl(hashedString).url(urlReqest.url()).build();
        tinyUrlRepository.save(tinyUrl);
        return tinyUrl.getShortUrl();
    }
}
