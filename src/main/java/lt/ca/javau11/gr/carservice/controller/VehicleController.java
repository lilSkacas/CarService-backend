package lt.ca.javau11.gr.carservice.controller;

import lt.ca.javau11.gr.carservice.dto.VehicleDto;
import lt.ca.javau11.gr.carservice.entity.VehicleEntity;
import lt.ca.javau11.gr.carservice.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;

    }

    @PostMapping("carservice/vehicle/create")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vDto) {
        VehicleDto createdVehicle = vehicleService.createVehicle(vDto);

        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @GetMapping("carservice/vehicle/get/all")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> cars = vehicleService.getAllVehicles();
        return new ResponseEntity<>(cars, HttpStatus.OK);

    }

    @GetMapping("/carservice/vehicle/get/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Long id) {
        Optional<VehicleDto> carInBox = vehicleService.getVehicleById(id);
        return carInBox
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/carservice/vehicle/update/{id}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable Long id, @RequestBody VehicleDto vDto) {

        Optional<VehicleDto> carInBox = vehicleService.updateVehicle(id, vDto);
        return carInBox
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/carservice/vehicle/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}