package lt.ca.javau11.gr.carservice.controller;

import lt.ca.javau11.gr.carservice.dto.MaintenanceDto;
import lt.ca.javau11.gr.carservice.service.MaintenanceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<MaintenanceDto>> getAllMaintenance() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenance());
    }

    @GetMapping("/vehicle/{vehicleId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<MaintenanceDto>> getMaintenanceByVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceByVehicle(vehicleId));
    }

    

    @GetMapping("/carservice/maintenance/get/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MaintenanceDto> getMaintenanceById(@PathVariable Long id) {
        return maintenanceService.getMaintenanceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/carservice/maintenance/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MaintenanceDto> createMaintenance(@RequestBody MaintenanceDto maintenanceDto) {
        try {
            return ResponseEntity.ok(maintenanceService.createMaintenance(maintenanceDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/carservice/maintenance/update/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MaintenanceDto> updateMaintenance(@PathVariable Long id, @RequestBody MaintenanceDto maintenanceDto) {
        try {
            return maintenanceService.updateMaintenance(id, maintenanceDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/carservice/maintenance/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}