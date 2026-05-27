package ticketingManagement.System.messaging;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import ticketingManagement.System.repository.AuditLogRepository;
import ticketingManagement.System.entity.AuditLog;

@Component
public class Listener {

    @Autowired
    private AuditLogRepository auditRepo;

    @JmsListener(destination = "ticket.events")
    public void listen(String msg) {
        AuditLog log = new AuditLog();
        log.setAction("RECEIVED");
        log.setSource("JMS");
        log.setMessageId(msg);
        auditRepo.save(log);
    }
}