package com.senac.assister.backend.rest.dto.rate;

import com.senac.assister.backend.domain.entity.Rate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateRateResponse {

    private static ModelMapper mapper = new ModelMapper();

    private UUID id;

    private double score;

    private String description;

    public static CreateRateResponse convertToResponse(Rate rate) {
        return mapper.map(rate, CreateRateResponse.class);
    }
}
