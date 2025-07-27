package br.com.lferreira2d.Backend.controller;

import br.com.lferreira2d.Backend.model.SpoofingAttempt;
import br.com.lferreira2d.Backend.repository.SpoofingAttemptRepository;
import br.com.lferreira2d.Backend.service.SpoofingAttemptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = { "/front", "/api" })
@Slf4j
public class SpoofingAttemptController {

    private final SpoofingAttemptService service;

    public SpoofingAttemptController(SpoofingAttemptService service) {
        this.service = service;
    }

    @GetMapping("/spoofing")
    public ResponseEntity<List<SpoofingAttempt>> listAll() {
        log.info("Handling GET /spoofing");
        try {
            List<SpoofingAttempt> all = service.findAll();
            log.info("Returning {} spoofing attempts", all.size());
            return ResponseEntity.ok(all);
        } catch (Exception ex) {
            log.error("Error listing all spoofing attempts", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/spoofing/period")
    public ResponseEntity<?> listByPeriod(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end) {

        log.info("Handling GET /spoofing/period?start={} end={}", start, end);

        if (start.isAfter(end)) {
            log.warn("Invalid period: start ({}) > end ({})", start, end);
            return ResponseEntity
                    .badRequest()
                    .body("Parâmetro inválido: 'start' deve ser antes de 'end'");
        }

        try {
            List<SpoofingAttempt> periodList = service.getByPeriod(start, end);
            log.info("Found {} attempts between {} and {}", periodList.size(), start, end);
            return ResponseEntity.ok(periodList);
        } catch (Exception ex) {
            log.error("Error fetching spoofing attempts for period", ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao buscar tentativas por período");
        }
    }


    @PostMapping("/spoofing")
    public ResponseEntity<?> createSpoofingAttempts(
            @RequestBody List<@Valid SpoofingAttempt> attempts) {

        log.info("Handling POST /spoofing – lista com {} registros", attempts.size());

        try {
            List<SpoofingAttempt> salvos = attempts.stream()
                    .map(service::create)
                    .toList();

            return ResponseEntity.ok(salvos);

        } catch (Exception ex) {
            log.error("Erro ao salvar lista de spoofing", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar tentativas de spoofing");
        }
    }


    @GetMapping("/spoofing/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        log.info("Handling GET /spoofing/{}", id);
        try {
            return service.findById(id)
                    .map(attempt -> {
                        log.info("Found attempt {}", id);
                        return ResponseEntity.ok(attempt);
                    })
                    .orElseGet(() -> {
                        log.warn("Attempt {} not found", id);
                        return ResponseEntity.notFound().build();
                    });
        } catch (Exception ex) {
            log.error("Error fetching spoofing attempt {}", id, ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao buscar tentativa por ID");
        }
    }

    @DeleteMapping("/spoofing/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        log.info("Handling DELETE /spoofing/{}", id);
        try {
            if (!service.exists(id)) {
                log.warn("Attempt {} not found for deletion", id);
                return ResponseEntity.notFound().build();
            }
            service.delete(id);
            log.info("Deleted attempt {}", id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            log.error("Error deleting spoofing attempt {}", id, ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao deletar tentativa de spoofing");
        }
    }
}
