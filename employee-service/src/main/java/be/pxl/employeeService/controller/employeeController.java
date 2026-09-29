package be.pxl.employeeService.controller;

import be.pxl.employeeService.api.request.EmployeeRequest;
import be.pxl.employeeService.api.response.EmployeeResponse;
import be.pxl.employeeService.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class employeeController {

    private final EmployeeService employeeService;

    public employeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        employeeService.addEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse response = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        List<EmployeeResponse> response = employeeService.getEmployees();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByDepartmentId(@PathVariable Long departmentId) {
        List<EmployeeResponse> response = employeeService.getEmployeesByDepartmentId(departmentId);
        return ResponseEntity.ok(response);
    }

}
