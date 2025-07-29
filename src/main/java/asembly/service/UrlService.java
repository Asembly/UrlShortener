package asembly.service;

import asembly.dto.LongUrlResponseDto;
import asembly.dto.ShortUrlResponseDto;
import asembly.dto.UrlCreateDto;
import asembly.entity.Url;
import asembly.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Url> findAll()
    {
        return urlRep.findAll();
    }

    public LongUrlResponseDto findLongUrlById(String shortUrl)
    {
        return urlRep.findLongUrlByShort(shortUrl);
    }

    public ShortUrlResponseDto create(UrlCreateDto urlDto) throws NoSuchAlgorithmException {
        try{
            String shortId = generateShortId(urlDto.longUrl());
            LongUrlResponseDto findLongUrl = this.findLongUrlById(shortId);

            if(findLongUrl != null)
            {
                return new ShortUrlResponseDto(shortId);
            }

            Url url = new Url();
            url.setLongUrl(urlDto.longUrl());
            url.setShortUrl(shortId);
            url.setCreated_at(new Date().getTime());
            urlRep.save(url);
            return new ShortUrlResponseDto(shortId);
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

}
