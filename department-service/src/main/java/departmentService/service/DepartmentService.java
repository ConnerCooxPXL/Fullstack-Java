package departmentService.service;

import departmentService.api.response.DepartmentResponse;
import departmentService.api.request.DepartmentRequest;
import departmentService.api.response.DepartmentWithEmployeesResponse;
import departmentService.domain.Department;
import departmentService.exception.ResourceNotFoundException;
import departmentService.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department(departmentRequest.name(), departmentRequest.organizationId());
        departmentRepository.save(department);
    }

    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department", "department", String.valueOf(id)));
        return DepartmentResponse.toResponse(department);
    }

    public List<DepartmentResponse> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return toResponse(departments);
    }

    public List<DepartmentResponse> getDepartmentsByOrganizationId(Long organizationId) {
        List<Department> departments = departmentRepository.findDepartmentsByOrganizationId(organizationId);
        return toResponse(departments);
    }

    public List<DepartmentWithEmployeesResponse> getDepartmentsWithEmployeesByOrganizationId(Long organizationId) {
        List<Department> departments = departmentRepository.findDepartmentsByOrganizationId(organizationId);
        return toResponseWithEmployees(departments);
    }

    private List<DepartmentResponse> toResponse(List<Department> departments) {
        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        for (Department d:  departments) {
            departmentResponses.add(DepartmentResponse.toResponse(d));
        }
        return departmentResponses;
    }

    private List<DepartmentWithEmployeesResponse> toResponseWithEmployees(List<Department> departments) {
        List<DepartmentWithEmployeesResponse> responses = new ArrayList<>();
        for (Department d : departments) {
            responses.add(DepartmentWithEmployeesResponse.toResponse(d));
        }
        return responses;
    }
}
