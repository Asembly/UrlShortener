package asembly.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data @Table
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT",nullable = false, unique = true)
    private String longUrl;
    @Column(nullable = false)
    private String shortUrl;
    @Column(nullable = false)
    private Long created_at;
}
