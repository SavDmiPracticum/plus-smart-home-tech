package ru.practicum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "scenarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"hub_id", "name"})})
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String hubId;
    @NotBlank
    String name;
}