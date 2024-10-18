package ru.practicum.model.hub;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.model.hub.enums.HubEventType;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class HubEvent {
    @NotBlank
    String hubId;
    Instant timestamp = Instant.now();

    public abstract HubEventType getType();
}
