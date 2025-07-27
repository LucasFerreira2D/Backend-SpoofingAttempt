package br.com.lferreira2d.Backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(name = "ATTEMPT_DATE_HOUR", nullable = false, columnDefinition = "TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime attemptDateHour;

    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "LONGITUDE")
    private String longitude;

    @Lob
    @Column(name = "IMAGE_SPOOFING", columnDefinition = "CLOB")
    private String imageSpoofing;


}
