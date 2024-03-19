package project.sgs.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class )
public class AbstractEntity implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private Instant creationDate;
    private Instant lastModifiedDate;
    @PrePersist
    void perPersit(){
        creationDate=Instant.now();
    }
    @PreUpdate
    void preUpdate(){
        lastModifiedDate=Instant.now();
    }
}
