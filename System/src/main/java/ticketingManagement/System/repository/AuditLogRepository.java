package ticketingManagement.System.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ticketingManagement.System.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{

}
