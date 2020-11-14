package com.senac.assister.backend.rest.dto.specialNeedType;

import com.senac.assister.backend.domain.entity.SpecialNeedType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateSpecialNeedTypeRequest {

    private static ModelMapper mapper = new ModelMapper();

    @NotNull(message = "name must be sent.")
    @Size(min = 1, max = 255, message = "name must be between 1 and 255 characters")
    private String name;

    @NotNull(message = "price must be sent.")
    @Min(value = 0, message = "price must be greater than 0")
    @Max(value = 10, message = "price must be lower than 10")
    private double price;

    public static SpecialNeedType convertToEntity(CreateSpecialNeedTypeRequest request) {
        return mapper.map(request, SpecialNeedType.class);
    }
}
