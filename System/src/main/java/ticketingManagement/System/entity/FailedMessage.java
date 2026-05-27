package ticketingManagement.System.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "failed_messages")
public class FailedMessage {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column
	    private String queueName;
	    @Column
	    private String payload;
	    @Column
	    private String errorMessage;
	    @Column
	    private int retryCount = 0;
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getQueueName() {
			return queueName;
		}
		public void setQueueName(String queueName) {
			this.queueName = queueName;
		}
		public String getPayload() {
			return payload;
		}
		public void setPayload(String payload) {
			this.payload = payload;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		public int getRetryCount() {
			return retryCount;
		}
		public void setRetryCount(int retryCount) {
			this.retryCount = retryCount;
		}
		@Override
		public String toString() {
			return "FailedMessage [id=" + id + ", queueName=" + queueName + ", payload=" + payload + ", errorMessage="
					+ errorMessage + ", retryCount=" + retryCount + "]";
		}
		public FailedMessage() {
			
		}
		public FailedMessage(String queueName, String payload, String errorMessage, int retryCount) {
			super();
			this.queueName = queueName;
			this.payload = payload;
			this.errorMessage = errorMessage;
			this.retryCount = retryCount;
		}
	
}
