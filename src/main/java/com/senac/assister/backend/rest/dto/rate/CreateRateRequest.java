package com.senac.assister.backend.rest.dto.rate;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.rest.dto.customer.CreateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateRateRequest {

    private static ModelMapper mapper = new ModelMapper();

    @NotNull(message = "nedd score associated with service")
    private double score;

    private String description;

    public static Rate convertToEntity(CreateRateRequest request) {
        return mapper.map(request, Rate.class);
    }
}
