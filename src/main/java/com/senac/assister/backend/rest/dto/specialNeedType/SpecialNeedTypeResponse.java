package com.senac.assister.backend.rest.dto.specialNeedType;

import com.senac.assister.backend.domain.entity.SpecialNeedType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecialNeedTypeResponse {

    private static ModelMapper mapper = new ModelMapper();

    private String name;

    private double price;

    private Instant createdAt;

    public static SpecialNeedTypeResponse convertToDto(SpecialNeedType specialNeedType) {
        return mapper.map(specialNeedType, SpecialNeedTypeResponse.class);
    }
}
