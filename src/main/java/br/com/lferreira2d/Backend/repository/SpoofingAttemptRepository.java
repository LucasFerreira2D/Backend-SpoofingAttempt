package br.com.lferreira2d.Backend.repository;

import br.com.lferreira2d.Backend.model.SpoofingAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpoofingAttemptRepository extends JpaRepository<SpoofingAttempt, UUID> {

    List<SpoofingAttempt> findAll();

    List<SpoofingAttempt> findByAttemptDateHourBetween(
            LocalDateTime start,
            LocalDateTime end
    );

}
