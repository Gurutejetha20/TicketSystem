package ticketingManagement.System.messaging;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import ticketingManagement.System.repository.FailedMessageRepository;
import ticketingManagement.System.entity.FailedMessage;


@Component
public class Producer {
	
	    @Autowired
	    private  JmsTemplate jmsTemplate;
	    @Autowired
	    private  FailedMessageRepository failedRepo;

	    public void send(String msg) {
	        int retry = 0;

	        while (retry < 3) {
	            try {
	                jmsTemplate.convertAndSend("ticket.events", msg);
	                return;
	            } catch (Exception e) {
	                retry++;
	                if (retry == 3) {
	                    FailedMessage f = new FailedMessage();
	                    f.setPayload(msg);
	                    f.setErrorMessage(e.getMessage());
	                    f.setRetryCount(retry);
	                    f.setQueueName("ticket.events");
	                    failedRepo.save(f);
	                }
	            }
	        }
	    }
}
