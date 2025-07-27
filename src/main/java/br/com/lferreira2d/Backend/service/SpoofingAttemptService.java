package br.com.lferreira2d.Backend.service;

import br.com.lferreira2d.Backend.model.SpoofingAttempt;
import br.com.lferreira2d.Backend.repository.SpoofingAttemptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpoofingAttemptService {

    private final SpoofingAttemptRepository repository;

    public SpoofingAttemptService(SpoofingAttemptRepository repository) {
        this.repository = repository;
    }

    public List<SpoofingAttempt> findAll() {
        return repository.findAll();
    }

    public Optional<SpoofingAttempt> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public SpoofingAttempt create(SpoofingAttempt attempt) {
        if (attempt.getId() == null) {
            attempt.setId(UUID.randomUUID());
        }
        return repository.save(attempt);
    }

    @Transactional
    public SpoofingAttempt update(UUID id, SpoofingAttempt attempt) {
        attempt.setId(id);
        return repository.save(attempt);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public boolean exists(UUID id) {
        return repository.existsById(id);
    }

    public List<SpoofingAttempt> getByPeriod(LocalDateTime start, LocalDateTime end) {
        return repository.findByAttemptDateHourBetween(start, end);
    }
}
