package be.pxl.employeeService.service;

import be.pxl.employeeService.api.request.EmployeeRequest;
import be.pxl.employeeService.api.response.EmployeeResponse;
import be.pxl.employeeService.domain.Employee;
import be.pxl.employeeService.exception.ResourceNotFoundException;
import be.pxl.employeeService.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository  employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.firstName(), employeeRequest.lastName(), employeeRequest.email(), employeeRequest.departmentId(), employeeRequest.organizationId());
        employeeRepository.save(employee);
    }

    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "employee", String.valueOf(id)));
        return EmployeeResponse.toResponse(employee);
    }

    public List<EmployeeResponse> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return toResponse(employees);
    }

    public List<EmployeeResponse> getEmployeesByDepartmentId(Long departmentId) {
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentId(departmentId);
        return toResponse(employees);
    }

    private List<EmployeeResponse> toResponse(List<Employee> employees) {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Employee e : employees) {
            employeeResponses.add(EmployeeResponse.toResponse(e));
        }
        return employeeResponses;
    }
}
