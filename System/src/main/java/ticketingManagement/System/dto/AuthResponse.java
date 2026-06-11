package ticketingManagement.System.dto;

public class AuthResponse {
    private String token;
    private String username;
    private String fullName;
    private String email;
    private String role;
    private Long userId;

    public AuthResponse(String token, String username, String fullName, String email, String role, Long userId) {
        this.token = token;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.userId = userId;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public Long getUserId() { return userId; }
}