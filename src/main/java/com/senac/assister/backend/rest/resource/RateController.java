package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.service.RateService;
import com.senac.assister.backend.rest.dto.rate.CreateRateRequest;
import com.senac.assister.backend.rest.dto.rate.CreateRateResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequestMapping("/api/v1/rate")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService){
        this.rateService = rateService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get rate per id")
    public ResponseEntity<CreateRateResponse> getRateById(@PathVariable UUID id) {
        Rate rate = rateService.findById(id);
        CreateRateResponse rateResponse = CreateRateResponse.convertToResponse(rate);
        return new ResponseEntity<CreateRateResponse>(rateResponse, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("Get list whith rates")
    public ResponseEntity<List<CreateRateResponse>> getAllRates() {
        List<CreateRateResponse> list = rateService.findAll()
                .stream()
                .map(CreateRateResponse::convertToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<List<CreateRateResponse>>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update rate")
    public ResponseEntity<CreateRateResponse> updateRate(@PathVariable UUID id, @Valid  @RequestBody CreateRateRequest request) {
        Rate rate = CreateRateRequest.convertToEntity(request);
        rate.setId(id);
        rate = rateService.update(rate);
        return new ResponseEntity<CreateRateResponse>(CreateRateResponse.convertToResponse(rate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete rate")
    public ResponseEntity<Void> deleteRate(@PathVariable UUID id) {
        rateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
