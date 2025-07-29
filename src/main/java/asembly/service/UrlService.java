package asembly.service;

import asembly.dto.LongUrlResponseDto;
import asembly.dto.ShortUrlResponseDto;
import asembly.dto.UrlCreateDto;
import asembly.entity.Url;
import asembly.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRep;

    public ResponseEntity<List<Url>> findAll()
    {
        return ResponseEntity.ok(urlRep.findAll());
    }

    public ResponseEntity<LongUrlResponseDto> findLongUrlById(String shortUrl)
    {
        return ResponseEntity.ok(urlRep.findLongUrlByShort(shortUrl));
    }

    public ResponseEntity<ShortUrlResponseDto> create(UrlCreateDto urlDto) throws NoSuchAlgorithmException {
        try{

            if(validateUrl(urlDto.longUrl()))
            {
                String shortId = generateShortId(urlDto.longUrl());
                LongUrlResponseDto findLongUrl = urlRep.findLongUrlByShort(shortId);

                if(findLongUrl != null)
                    return ResponseEntity.ok(new ShortUrlResponseDto(shortId));

                Url url = new Url();
                url.setLongUrl(urlDto.longUrl());
                url.setShortUrl(shortId);
                url.setCreated_at(new Date().getTime());
                urlRep.save(url);
                return ResponseEntity.ok(new ShortUrlResponseDto(shortId));
            }
            return ResponseEntity.badRequest().body(new ShortUrlResponseDto(null));
        }catch(NoSuchAlgorithmException e)
        {
            throw new NoSuchAlgorithmException(e.getMessage());
        }
    }

    private String generateShortId(String longUrl) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(longUrl.getBytes());

        byte[] shortHash = new byte[6];
        System.arraycopy(hash, 0, shortHash, 0, 6);
        String base64Encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(shortHash);

        return base64Encoded.substring(0, Math.min(base64Encoded.length(), 7));
    }

    private boolean validateUrl(String url)
    {
        try{
            URL validUrl = new URI(url).toURL();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
