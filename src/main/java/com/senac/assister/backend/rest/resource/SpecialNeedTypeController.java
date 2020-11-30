package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.SpecialNeedType;
import com.senac.assister.backend.domain.service.SpecialNeedTypeService;
import com.senac.assister.backend.rest.dto.specialNeedType.CreateSpecialNeedTypeRequest;
import com.senac.assister.backend.rest.dto.specialNeedType.SpecialNeedTypeResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequestMapping("/api/v1/special-need-type")
public class SpecialNeedTypeController {

    SpecialNeedTypeService specialNeedTypeService;

    public SpecialNeedTypeController(SpecialNeedTypeService specialNeedTypeService) {
        this.specialNeedTypeService = specialNeedTypeService;
    }

    @PostMapping("")
    @ApiOperation("Add special need type")
    public ResponseEntity<SpecialNeedTypeResponse> addSpecialNeedType(@Valid @RequestBody CreateSpecialNeedTypeRequest createSpecialNeedTypeRequest) {
        SpecialNeedType specialNeedType = specialNeedTypeService.save(CreateSpecialNeedTypeRequest.convertToEntity(createSpecialNeedTypeRequest));

        SpecialNeedTypeResponse response = SpecialNeedTypeResponse.convertToDto(specialNeedType);

        return new ResponseEntity<SpecialNeedTypeResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get special need type per id")
    public ResponseEntity<SpecialNeedTypeResponse> getRateById(@PathVariable UUID id) {
        SpecialNeedType specialNeedType = specialNeedTypeService.findById(id);

        SpecialNeedTypeResponse response = SpecialNeedTypeResponse.convertToDto(specialNeedType);

        return new ResponseEntity<SpecialNeedTypeResponse>(response, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("List all special need type.")
    public ResponseEntity<List<SpecialNeedTypeResponse>> getAllRates() {
        List<SpecialNeedTypeResponse> list = specialNeedTypeService.findAll()
                .stream()
                .map(SpecialNeedTypeResponse::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<List<SpecialNeedTypeResponse>>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update special need type")
    public ResponseEntity<SpecialNeedTypeResponse> updateRate(@PathVariable UUID id, @Valid @RequestBody CreateSpecialNeedTypeRequest request) {
        SpecialNeedType specialNeedType = CreateSpecialNeedTypeRequest.convertToEntity(request);

        specialNeedType.setId(id);

        specialNeedType = specialNeedTypeService.update(specialNeedType);

        return new ResponseEntity<SpecialNeedTypeResponse>(SpecialNeedTypeResponse.convertToDto(specialNeedType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete special need type")
    public ResponseEntity<Void> deleteSpecialNeedType(@PathVariable UUID id) {

        specialNeedTypeService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
