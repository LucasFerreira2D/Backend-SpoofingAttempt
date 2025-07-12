package br.com.lferreira2d.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "SPOOFING_ATTEMPTS")
@AllArgsConstructor
@NoArgsConstructor
public class SpoofingAttempt implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "UUID DEFAULT RANDOM_UUID()")
    private UUID id;

    @Column(name = "ATTEMPT_DATE_HOUR", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime attemptDateHour;

    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "LONGITUDE")
    private String longitude;

    @Lob
    @Column(name = "IMAGE_SPOOFING", columnDefinition = "CLOB")
    private String imageSpoofing;


}
