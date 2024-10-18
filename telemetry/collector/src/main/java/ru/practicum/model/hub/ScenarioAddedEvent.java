package ru.practicum.model.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.model.hub.enums.HubEventType;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenarioAddedEvent extends HubEvent {
    @NotBlank
    @Size(min = 3)
    String name;
    @NotNull
    List<ScenarioCondition> conditions;
    @NotNull
    List<DeviceAction> actions;

    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_ADDED;
    }
}
