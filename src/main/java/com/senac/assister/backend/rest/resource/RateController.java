package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.service.RateService;
import com.senac.assister.backend.rest.dto.rate.CreateRateRequest;
import com.senac.assister.backend.rest.dto.rate.RateResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequestMapping("/api/v1/rate")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get rate per id")
    public ResponseEntity<RateResponse> getRateById(@PathVariable UUID id) {
        Rate rate = rateService.findById(id);
        RateResponse rateResponse = RateResponse.convertToResponse(rate);
        return new ResponseEntity<RateResponse>(rateResponse, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("Get list whith rates")
    public ResponseEntity<List<RateResponse>> getAllRates() {
        List<RateResponse> list = rateService.findAll()
                .stream()
                .map(RateResponse::convertToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<List<RateResponse>>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update rate")
    public ResponseEntity<RateResponse> updateRate(@PathVariable UUID id, @Valid  @RequestBody CreateRateRequest request) {
        Rate rate = CreateRateRequest.convertToEntity(request);
        rate.setId(id);
        rate = rateService.update(rate);
        return new ResponseEntity<RateResponse>(RateResponse.convertToResponse(rate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete rate")
    public ResponseEntity<Void> deleteRate(@PathVariable UUID id) {
        rateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
