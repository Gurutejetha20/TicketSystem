package ticketingManagement.System.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;
import ticketingManagement.System.repository.TicketRepository;
import ticketingManagement.System.repository.AuditLogRepository;
import ticketingManagement.System.messaging.Producer;
import ticketingManagement.System.entity.Ticket;
import ticketingManagement.System.entity.AuditLog;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repo;

    @Autowired
    private AuditLogRepository auditRepo;

    @Autowired
    private Producer producer;

    public Ticket create(Ticket ticket) {
        if ("PREMIUM".equals(ticket.getIssueType())) {
            ticket.setPriority("HIGH");
        }
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        Ticket saved = repo.save(ticket);
        producer.send("Ticket Created: " + saved.getId());
        log(saved.getId(), "CREATE", "REST");
        return saved;
    }

    public Ticket get(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<Ticket> getAll() {
        return repo.findAll();
    }

    public List<Ticket> getByCustomerId(Long customerId) {
        return repo.findAll().stream()
                .filter(t -> customerId.equals(t.getCustomerId()))
                .toList();
    }

    public Ticket update(Long id, Ticket ticket) {
        Ticket existing = repo.findById(id).orElseThrow();
        existing.setDescription(ticket.getDescription());
        existing.setIssueType(ticket.getIssueType());
        existing.setPriority(ticket.getPriority());
        existing.setUpdatedAt(LocalDateTime.now());
        Ticket saved = repo.save(existing);
        producer.send("Ticket Updated: " + saved.getId());
        log(saved.getId(), "UPDATE", "REST");
        return saved;
    }

    public Ticket closeTicket(Long id) {
        Ticket existing = repo.findById(id).orElseThrow();
        existing.setStatus("CLOSED");
        existing.setUpdatedAt(LocalDateTime.now());
        Ticket saved = repo.save(existing);
        producer.send("Ticket Closed: " + saved.getId());
        log(saved.getId(), "CLOSE", "REST");
        return saved;
    }

    public void delete(Long id) {
        repo.deleteById(id);
        log(id, "DELETE", "REST");
    }

    private void log(Long id, String action, String source) {
        AuditLog log = new AuditLog();
        log.setTicketId(id);
        log.setAction(action);
        log.setSource(source);
        auditRepo.save(log);
    }
}