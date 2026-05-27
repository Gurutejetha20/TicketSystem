package ticketingManagement.System.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ticketingManagement.System.entity.FailedMessage;

public interface FailedMessageRepository extends JpaRepository<FailedMessage, Long>{

}
