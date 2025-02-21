package lt.ca.javau11.gr.carservice.controller;


import lt.ca.javau11.gr.carservice.dto.MaintenanceDto;
import lt.ca.javau11.gr.carservice.service.MaintenanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping("/carservice/maintenance/create")
    public ResponseEntity<MaintenanceDto> createMaintenance(@RequestBody MaintenanceDto mDto) {
        MaintenanceDto createdMaintenance = maintenanceService.createMaintenance(mDto);
        return new ResponseEntity<>(createdMaintenance, HttpStatus.CREATED);

    }

    @GetMapping("/carservice/maintenance/get/all")
    public ResponseEntity<List<MaintenanceDto>> getAllMaintenance() {
        List<MaintenanceDto> maintenance = maintenanceService.getAllMaintenance();
        return new ResponseEntity<>(maintenance, HttpStatus.OK);

    }

    @GetMapping("/carservice/maintenance/get/{id}")
    public ResponseEntity<MaintenanceDto> getMaintenanceById(@PathVariable Long id) {
        Optional<MaintenanceDto> maintenanceInBox = maintenanceService.getMaintenanceById(id);

        return maintenanceInBox
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/carservice/maintenance/update/{id}")
    public ResponseEntity<MaintenanceDto> updateMaintenance(
            @PathVariable Long id,
            @RequestBody MaintenanceDto mDto) {
        Optional<MaintenanceDto> maintenanceInBox = maintenanceService.updateMaintenance(id, mDto);

        return maintenanceInBox.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/carservice/maintenance/delete/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}