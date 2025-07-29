package asembly.repository;

import asembly.dto.LongUrlResponseDto;
import asembly.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UrlRepository extends JpaRepository<Url, Integer> {

    @Query(value = "select long_url from url where short_url = :short_url", nativeQuery = true)
    public LongUrlResponseDto findLongUrlByShort(String short_url);
}
