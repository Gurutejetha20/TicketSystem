package ticketingManagement.System.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import ticketingManagement.System.service.TicketService;
import ticketingManagement.System.util.XmlValidator;
import ticketingManagement.System.entity.Ticket;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @Autowired
    private XmlValidator validator;

    @PostMapping(consumes = "application/xml")
    public Ticket create(@RequestBody String xml) throws Exception {

        validator.validate(xml);

        JAXBContext context = JAXBContext.newInstance(Ticket.class);
        Unmarshaller um = context.createUnmarshaller();
        Ticket ticket = (Ticket) um.unmarshal(new StringReader(xml));

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

    @PutMapping("/update/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody Ticket ticket) {
        return service.update(id, ticket);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
