package ru.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.model.hub.enums.ScenarioConditionOperation;
import ru.practicum.model.hub.enums.ScenarioConditionType;

@Entity
@Table(name = "conditions")
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    ScenarioConditionType type;
    @Enumerated(EnumType.STRING)
    ScenarioConditionOperation operation;
    Integer value;
}
