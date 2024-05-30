package com.fresh.coding.carshow.mappers;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.entities.Car;
import com.fresh.coding.carshow.entities.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CarMapper {


    public Car toEntity(CarRequest carRequest) {
        return Car.builder()
                .id(carRequest.id())
                .name(carRequest.name())
                .description(carRequest.description())
                .brand(carRequest.brand())
                .model(carRequest.model())
                .price(carRequest.price())
                .color(carRequest.color())
                .motorType(carRequest.motorType())
                .type(carRequest.type())
                .power(carRequest.power())
                .placeNumber(carRequest.placeNumber())
                .status(carRequest.status())
                .build();
    }

    public CarResponse toResponse(Car car) {
        return new CarResponse(
                car.getId(),
                car.getName(),
                car.getDescription(),
                car.getBrand(),
                car.getModel(),
                car.getPrice(),
                car.getColor(),
                car.getMotorType(),
                car.getType(),
                car.getPower(),
                car.getPlaceNumber(),
                car.getStatus(),
                car.getImages() == null ? null : car.getImages().stream()
                        .map(Image::getUrl)
                        .collect(Collectors.toList())

        );
    }
}
