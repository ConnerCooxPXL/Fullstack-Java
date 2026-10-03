package departmentService.controller;

import departmentService.api.response.DepartmentResponse;
import departmentService.api.request.DepartmentRequest;
import departmentService.api.response.DepartmentWithEmployeesResponse;
import departmentService.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody @Valid DepartmentRequest  departmentRequest) {
        departmentService.addDepartment(departmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        DepartmentResponse response = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
        List<DepartmentResponse> response = departmentService.getDepartments();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<DepartmentResponse>> getDepartmentsByOrganizationId(@PathVariable Long organizationId) {
        List<DepartmentResponse> response = departmentService.getDepartmentsByOrganizationId(organizationId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public ResponseEntity<List<DepartmentWithEmployeesResponse>> getDepartmentsWithEmployeesByOrganizationId(@PathVariable Long organizationId) {
        List<DepartmentWithEmployeesResponse> response = departmentService.getDepartmentsWithEmployeesByOrganizationId(organizationId);
        return ResponseEntity.ok(response);
    }
}
