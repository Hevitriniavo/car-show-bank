package com.fresh.coding.carshow.entities;

import com.fresh.coding.carshow.enums.CarStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "\"cars\"")
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String brand;

    private String model;

    private Long price;

    private String color;

    private String motorType;

    private String type;

    private Integer power;

    private String placeNumber;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public void addImage(Image imageSaved) {
        if (!this.images.contains(imageSaved)) {
            this.images.add(imageSaved);
            imageSaved.setCar(this);
        }
    }
}
