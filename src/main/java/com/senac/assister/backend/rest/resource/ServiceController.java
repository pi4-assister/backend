package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import com.senac.assister.backend.domain.service.RateService;
import com.senac.assister.backend.domain.service.ServicesService;
import com.senac.assister.backend.rest.dto.customer.CustomerSQtd;
import com.senac.assister.backend.rest.dto.rate.CreateRateRequest;
import com.senac.assister.backend.rest.dto.rate.CreateRateResponse;
import com.senac.assister.backend.rest.dto.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/api/v1/service")
public class ServiceController {

    private final ServicesService servicesService;
    private final RateService rateService;

    public ServiceController(ServicesService service, RateService rateService) {
        this.servicesService = service;
        this.rateService = rateService;
    }

    @PostMapping("/quote")
    @ApiOperation("Quote assister service. Service will attach to customer sent on authentication.")
    public ResponseEntity<QuoteServiceResponse> createAndQuoteService(@RequestBody QuoteServiceRequest request) {
        Service service = servicesService.quoteService(QuoteServiceRequest.convertToEntity(request));

        QuoteServiceResponse response = QuoteServiceResponse.convertToDto(service);

        return new ResponseEntity<QuoteServiceResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/rate")
    @ApiOperation("Create rate in service")
    public ResponseEntity<CreateRateResponse> createRateInService(@PathVariable UUID id, @Valid @RequestBody CreateRateRequest rateRequest) {
        Service service = servicesService.findById(id);

        Rate rate = CreateRateRequest.convertToEntity(rateRequest);
        rate = rateService.save(rate);
        service.setRate(rate);

        servicesService.update(service);

        return new ResponseEntity<CreateRateResponse>(CreateRateResponse.convertToDto(rate), HttpStatus.OK);
    }

    @GetMapping("/assisters")
    @ApiOperation("List assisters in range date")
    public ResponseEntity<List<CustomerSQtd>> listAssisters(@RequestParam("dateI") String dateI,
                                                            @RequestParam("dateF") String dateF) {
        Instant dateInitial = LocalDate.parse(dateI).atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant dateFinal = LocalDate.parse(dateF).atStartOfDay().toInstant(ZoneOffset.UTC);
        List<CustomerSQtd> list = servicesService.findAllAssisterInRange(dateInitial, dateFinal);
        return new ResponseEntity<List<CustomerSQtd>>(list, HttpStatus.OK);
    }

    @PostMapping("/customer/accept/{id}")
    @ApiOperation("Customer accept service")
    public ResponseEntity<ServiceResponseAlterStatus> customerAcceptService(@PathVariable UUID id) {
        ServiceResponseAlterStatus service =
                ServiceResponseAlterStatus.
                        convertToResponse(
                                servicesService.alterStatusService(id, ServiceStatus.INITIATED)
                        );
        return new ResponseEntity<ServiceResponseAlterStatus>(service, HttpStatus.OK);
    }

    @PostMapping("/assister/accept/{id}")
    @ApiOperation("Assister accept service")
    public ResponseEntity<ServiceResponseAlterStatus> assisterAcceptService(@PathVariable UUID id) {
        ServiceResponseAlterStatus service =
                ServiceResponseAlterStatus
                        .convertToResponse(
                                servicesService.alterStatusService(id, ServiceStatus.IN_PROGRESS)
                        );
        servicesService.sendEmail(id);
        return new ResponseEntity<ServiceResponseAlterStatus>(service, HttpStatus.OK);
    }
}