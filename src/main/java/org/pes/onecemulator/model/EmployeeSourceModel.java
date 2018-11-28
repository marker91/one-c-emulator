package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.pes.onecemulator.model.api.LocalDateDeserializer;
import org.pes.onecemulator.model.api.LocalDateSerializer;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeeSourceModel {

    @JsonProperty(value = "startDate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @JsonProperty(value = "endDate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    @JsonProperty("source")
    private String source;
}
