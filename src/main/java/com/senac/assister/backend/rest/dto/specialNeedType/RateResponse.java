package com.senac.assister.backend.rest.dto.specialNeedType;

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
public class RateResponse {

    private static ModelMapper mapper = new ModelMapper();

    private UUID id;

    private double score;

    private String description;

    public static RateResponse convertToResponse(Rate rate) {
        return mapper.map(rate, RateResponse.class);
    }
}
