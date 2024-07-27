package dev.patika.vet.api;

import dev.patika.vet.business.abstracts.ICustomerService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.result.Result;
import dev.patika.vet.core.result.ResultData;
import dev.patika.vet.core.utilities.ResultHelper;
import dev.patika.vet.dto.request.customer.CustomerSaveRequest;
import dev.patika.vet.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vet.dto.response.animal.AnimalResponse;
import dev.patika.vet.dto.response.customer.CustomerResponse;
import dev.patika.vet.entities.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")


public class CustomerController {
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") int id) {
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @GetMapping("/customer-by-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> getByCustomerName(@RequestParam String name) {
        return ResultHelper.success(customerService.getByCustomerName(name).stream().map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping("/customer-by-animal-id/{id}")
    public ResultData<List<AnimalResponse>> getByAnimalId(@PathVariable("id") int id) {
        return ResultHelper.success(customerService.getByAnimalId(id).stream().map(customer -> modelMapper.forResponse().map(customer, AnimalResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.customerService.delete(id);
        return ResultHelper.successResult();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.update(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }



}
