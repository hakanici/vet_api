package dev.patika.vet.api;

import dev.patika.vet.business.abstracts.IAnimalService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.result.Result;
import dev.patika.vet.core.result.ResultData;
import dev.patika.vet.core.utilities.ResultHelper;
import dev.patika.vet.dto.request.animal.AnimalSaveRequest;
import dev.patika.vet.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vet.dto.response.animal.AnimalGetAllResponse;
import dev.patika.vet.dto.response.animal.AnimalResponse;
import dev.patika.vet.entities.Animal;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")

public class AnimalController {
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;

    public AnimalController(IAnimalService animalService, IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        this.animalService.save(saveAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") int id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @GetMapping("/customer-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalGetAllResponse> getCustomerById(@PathVariable("id") int id) {
        return animalService.getCustomerById(id).stream().map(animal -> modelMapper.forResponse().map(animal, AnimalGetAllResponse.class)).collect(Collectors.toList());
    }

    @GetMapping("/customer-by-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalGetAllResponse>> getCustomerByName(@RequestParam String name) {
        return ResultHelper.success(animalService.getCustomerByName(name).stream().map(animal -> modelMapper.forResponse().map(animal, AnimalGetAllResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping("/animal-by-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalGetAllResponse>> getAnimalByName(@RequestParam String name) {
        return ResultHelper.success(animalService.getAnimalByName(name).stream().map(animal -> modelMapper.forResponse().map(animal, AnimalGetAllResponse.class)).collect(Collectors.toList()));
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.animalService.delete(id);
        return ResultHelper.successResult();
    }

    @GetMapping
    public List<Animal> findAll(){
        return animalService.findAll();
    }

}
