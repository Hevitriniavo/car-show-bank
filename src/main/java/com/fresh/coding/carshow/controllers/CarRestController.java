package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarSummarized;
import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import com.fresh.coding.carshow.enums.CarStatus;
import com.fresh.coding.carshow.services.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarRestController {

    private final CarService carService;

    @PostMapping
    public List<CarSummarized> createAllCars(
            @RequestBody
            List<@Valid CarRequest> carRequests) {
        return carService.createAllCars(carRequests);
    }

    @PutMapping("/{id}")
    public CarSummarized updateCar(@PathVariable Long id, @RequestBody @Valid CarRequest carRequest) {
        return carService.modifyCarById(id, carRequest);
    }

    @PatchMapping("/{id}/status/{status}")
    public CarSummarized patchStatusCar(@PathVariable Long id, @PathVariable String status) {
        return carService.modifyStatusCarById(id, status);
    }

    @DeleteMapping("/{id}")
    public CarSummarized deleteCar(@PathVariable Long id) {
        return carService.deleteCarById(id);
    }

    @GetMapping
    public Paginate<List<CarWithImageSummarized>> getCarWithImages(
            @RequestParam(defaultValue = "1") String page,
            @RequestParam(defaultValue = "10") String perPage
    ) {
        return carService.paginateCars(Integer.valueOf(page), Integer.valueOf(perPage));
    }

    @GetMapping("/brand")
    public List<String> getAllBrandOfCars(
            @RequestParam(defaultValue = "6") String limit
    ) {
        return carService.findAllBrandOfCars(Integer.valueOf(limit));
    }

    @GetMapping("/type-motor")
    public List<String> getAllMotorTypeOfCars() {
        return carService.findAllMotorTypeOfCars();
    }

    @GetMapping("/type")
    public List<String> getAllTypeOfCars() {
        return carService.findAllTypeOfCars();
    }


    @GetMapping("/pinned")
    public List<CarSummarized> getAllCarByStatusPinned(
            @RequestParam(defaultValue = "6") String limit
    ) {
        return carService.findAllCarByStatusPinned(Integer.valueOf(limit));
    }

    @GetMapping("/type/{type}/exclude/{id}")
    public Paginate<List<CarWithImageSummarized>> getCarsByTypeAndExcludeId(
            @PathVariable String type,
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") String page,
            @RequestParam(defaultValue = "10") String perPage) {
        return carService.findCarsByTypeAndExcludeId(type, id, Integer.valueOf(page), Integer.valueOf(perPage));
    }


    @PostMapping("/images")
    public CarWithImageSummarized createCarWithImage(
            @RequestParam @NotBlank String name,
            @RequestParam @NotBlank String description,
            @RequestParam @NotBlank String brand,
            @RequestParam @NotBlank String model,
            @RequestParam @NotNull String price,
            @RequestParam @NotBlank String color,
            @RequestParam @NotBlank String motorType,
            @RequestParam @NotBlank String type,
            @RequestParam @NotNull String power,
            @RequestParam @NotNull @Size(min = 1) String placeNumber,
            @RequestParam @NotNull String status,
            @RequestParam MultipartFile[] files
    ){
        var carRequest = new CarRequest(null, name, description, brand, model, Long.valueOf(price), color, motorType, type, Integer.valueOf(power), placeNumber,  CarStatus.valueOf(status));
        return carService.createCarWithImage(carRequest, files);
    }
}
