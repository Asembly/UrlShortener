package asembly.controller;

import asembly.dto.LongUrlResponseDto;
import asembly.dto.ShortUrlResponseDto;
import asembly.dto.UrlCreateDto;
import asembly.entity.Url;
import asembly.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@RequestMapping("/url-shortener")
@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public List<Url> findAll()
    {
        return urlService.findAll();
    }

    @GetMapping("/{shortUrl}")
    public LongUrlResponseDto findById(@PathVariable String shortUrl)
    {
        return urlService.findLongUrlById(shortUrl);
    }

    @PostMapping
    public ShortUrlResponseDto create(@RequestBody UrlCreateDto url) throws NoSuchAlgorithmException {
        return urlService.create(url);
    }
}
