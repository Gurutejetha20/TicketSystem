package ticketingManagement.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ticketingManagement.System.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}