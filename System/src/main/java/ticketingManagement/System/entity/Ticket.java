package ticketingManagement.System.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ticketingManagement.System.util.LocalDateTimeAdapter;

@Entity
@Table(name = "ticket")
@XmlRootElement(name = "Ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long customerId;

    @Column
    private String issueType;

    @Column
    private String priority;

    @Column
    private String description;

    @Column
    private String status = "OPEN";

    @Column
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class) // ✅ Adapter connected here
    private LocalDateTime submittedAt;

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Ticket() {
    }

    public Ticket(Long customerId, String issueType, String priority,
                  String description, String status, LocalDateTime submittedAt) {
        this.customerId = customerId;
        this.issueType = issueType;
        this.priority = priority;
        this.description = description;
        this.status = status;
        this.submittedAt = submittedAt;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id +
                ", customerId=" + customerId +
                ", issueType=" + issueType +
                ", priority=" + priority +
                ", description=" + description +
                ", status=" + status +
                ", submittedAt=" + submittedAt + "]";
    }
}