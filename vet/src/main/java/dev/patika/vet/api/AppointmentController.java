package dev.patika.vet.api;

import dev.patika.vet.business.abstracts.IAnimalService;
import dev.patika.vet.business.abstracts.IAppointmentService;
import dev.patika.vet.business.abstracts.IDoctorService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.result.Result;
import dev.patika.vet.core.result.ResultData;
import dev.patika.vet.core.utilities.ResultHelper;
import dev.patika.vet.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vet.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vet.dto.response.appointment.AppointmentGetAllResponse;
import dev.patika.vet.dto.response.appointment.AppointmentResponse;
import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Appointment;
import dev.patika.vet.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")


public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;

    public AppointmentController(IAppointmentService appointmentService, IModelMapperService modelMapper, IDoctorService doctorService, IAnimalService animalService) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
        this.doctorService = doctorService;
        this.animalService = animalService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") int id) {
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    @GetMapping("/appointments-by-date-by-doctor")
    public ResultData<List<AppointmentGetAllResponse>> findAppointmentsByDateRangeAndDoctor(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                                                            @RequestParam("doctorId") int id) {
        Doctor doctor = doctorService.get(id);
        return ResultHelper.success(appointmentService.findAppointmentsByDateRangeAndDoctor(startDate, endDate, doctor).stream().map(appointment -> modelMapper.forResponse().map(appointment, AppointmentGetAllResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping("/appointments-by-date-by-animal")
    public ResultData<List<AppointmentGetAllResponse>> findAppointmentsByDateRangeAndAnimal(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                                                            @RequestParam("animalId") int id) {
        Animal animal = animalService.get(id);
        return ResultHelper.success(appointmentService.findAppointmentsByDateRangeAndAnimal(startDate, endDate, animal).stream().map(appointment -> modelMapper.forResponse().map(appointment, AppointmentGetAllResponse.class)).collect(Collectors.toList()));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);
        this.appointmentService.update(updateAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.appointmentService.delete(id);
        return ResultHelper.successResult();
    }

    @GetMapping
    public List<Appointment> findAll(){
        return appointmentService.findAll();
    }
}
