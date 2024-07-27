package dev.patika.vet.api;

import dev.patika.vet.business.abstracts.IVaccineService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.result.Result;
import dev.patika.vet.core.result.ResultData;
import dev.patika.vet.core.utilities.ResultHelper;
import dev.patika.vet.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vet.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vet.dto.response.vaccine.VaccineGetAllResponse;
import dev.patika.vet.dto.response.vaccine.VaccineResponse;
import dev.patika.vet.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/vaccines")


public class VaccineController {
    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;

    public VaccineController(IVaccineService vaccineService, IModelMapperService modelMapper) {
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        this.vaccineService.save(saveVaccine);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveVaccine, VaccineResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") int id) {
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
    }

    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineGetAllResponse>> getAnimalVaccineById(@PathVariable("id") int id) {
        return ResultHelper.success(vaccineService.getAnimalVaccineById(id).stream().map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineGetAllResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-date") // 1
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineGetAllResponse>> findVaccinesByDateRange(@RequestParam("firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firsDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResultHelper.success(vaccineService.findVaccinesByDateRange(firsDate, endDate).stream().map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineGetAllResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping
    public List<Vaccine> findAll(){
        return vaccineService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.vaccineService.delete(id);
        return ResultHelper.successResult();
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        this.vaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class));
    }
}
