package ticketingManagement.System.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Ticket update(Long id, Ticket ticket) {
        Ticket existing = repo.findById(id).orElseThrow();
        existing.setDescription(ticket.getDescription());
        existing.setIssueType(ticket.getIssueType());
        existing.setPriority(ticket.getPriority());
        return repo.save(existing);
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