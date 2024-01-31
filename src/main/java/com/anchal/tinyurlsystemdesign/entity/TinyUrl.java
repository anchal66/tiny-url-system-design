package com.anchal.tinyurlsystemdesign.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TinyUrl {
    @Id
    private Long id;
    private String shortUrl;
    private String url;
}
