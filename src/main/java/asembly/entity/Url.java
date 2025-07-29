package asembly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity @Data @Table
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT", unique = true, nullable = false)
    @Size(min = 9, message = "Url must be large 9 symbols")
    private String longUrl;
    @Column(nullable = false)
    private String shortUrl;
    @Column(nullable = false)
    private Long created_at;
}
