package lt.ca.javau11.gr.carservice.dto;

public class VehicleDto {

    private Long id;
    private String licensePlate;
    private String make;
    private String model;
    private Integer year;
    private Double engine;
    private String fuel;
    private String transmission;
    private String wheelDrive;
    private String body;
    private Long clientId;

    public VehicleDto() {}

    public VehicleDto(Long id,
                      String licensePlate, String make, String model,
                      Integer year, Double engine, String fuel,
                      String transmission, String wheelDrive, String body,
                      Long clientId) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.fuel = fuel;
        this.transmission = transmission;
        this.wheelDrive = wheelDrive;
        this.body = body;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getEngine() {
        return engine;
    }

    public void setEngine(Double engine) {
        this.engine = engine;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getWheelDrive() {
        return wheelDrive;
    }

    public void setWheelDrive(String wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "VehicleDto{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", engine=" + engine +
                ", fuel='" + fuel + '\'' +
                ", transmission='" + transmission + '\'' +
                ", wheelDrive='" + wheelDrive + '\'' +
                ", body='" + body + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
