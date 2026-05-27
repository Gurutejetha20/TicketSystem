package ticketingManagement.System.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_AuditLog")
public class AuditLog {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column
	    private Long ticketId;
	    @Column
	    private String action;
	    @Column
	    private String source;
	    @Column
	    private String messageId;
	    @Column
	    private LocalDateTime timestamp = LocalDateTime.now();
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getTicketId() {
			return ticketId;
		}
		public void setTicketId(Long ticketId) {
			this.ticketId = ticketId;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getMessageId() {
			return messageId;
		}
		public void setMessageId(String messageId) {
			this.messageId = messageId;
		}
		public LocalDateTime getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
		@Override
		public String toString() {
			return "AuditLog [id=" + id + ", ticketId=" + ticketId + ", action=" + action + ", source=" + source
					+ ", messageId=" + messageId + ", timestamp=" + timestamp + "]";
		}
		public AuditLog() {
			
		}
		public AuditLog(Long ticketId, String action, String source, String messageId, LocalDateTime timestamp) {
			super();
			this.ticketId = ticketId;
			this.action = action;
			this.source = source;
			this.messageId = messageId;
			this.timestamp = timestamp;
		}

}
