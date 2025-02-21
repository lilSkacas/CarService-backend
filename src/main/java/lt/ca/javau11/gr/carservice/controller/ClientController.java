package lt.ca.javau11.gr.carservice.controller;


import lt.ca.javau11.gr.carservice.dto.ClientDto;
import lt.ca.javau11.gr.carservice.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class ClientController {

    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/carservice/client/create")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){
        ClientDto createdClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);

    }

    @GetMapping("/carservice/client/get/all")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/carservice/client/get/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id){
        Optional<ClientDto> clientInBox = clientService.getClientById(id);
        return clientInBox.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/carservice/client/update/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id,
                                                  @RequestBody ClientDto clientDto){
        Optional<ClientDto> clientInBox = clientService.updateClient(id, clientDto);
        return clientInBox
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/carservice/client/delete/{id}")
    public ResponseEntity<ClientDto> deleteClient(@PathVariable Long id){

        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
