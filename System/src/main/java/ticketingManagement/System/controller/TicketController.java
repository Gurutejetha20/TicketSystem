package ticketingManagement.System.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import ticketingManagement.System.service.TicketService;
import ticketingManagement.System.util.XmlValidator;
import ticketingManagement.System.util.JwtUtil;
import ticketingManagement.System.entity.Ticket;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @Autowired
    private XmlValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    private Long extractUserId(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return jwtUtil.extractUserId(header.substring(7));
        }
        return null;
    }

    @PostMapping(consumes = "application/xml")
    public Ticket create(@RequestBody String xml, HttpServletRequest request) throws Exception {
        validator.validate(xml);
        JAXBContext context = JAXBContext.newInstance(Ticket.class);
        Unmarshaller um = context.createUnmarshaller();
        Ticket ticket = (Ticket) um.unmarshal(new StringReader(xml));
        ticket.setCustomerId(extractUserId(request));
        return service.create(ticket);
    }

    @PostMapping(consumes = "application/json")
    public Ticket createJson(@RequestBody Ticket ticket, HttpServletRequest request) {
        ticket.setCustomerId(extractUserId(request));
        return service.create(ticket);
    }

    @GetMapping("/{id}")
    public Ticket get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<Ticket> getAll() {
        return service.getAll();
    }

    @GetMapping("/my")
    public List<Ticket> getMyTickets(HttpServletRequest request) {
        Long userId = extractUserId(request);
        return service.getByCustomerId(userId);
    }

    @PutMapping("/update/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody Ticket ticket) {
        return service.update(id, ticket);
    }

    @PutMapping("/close/{id}")
    public Ticket close(@PathVariable Long id) {
        return service.closeTicket(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}