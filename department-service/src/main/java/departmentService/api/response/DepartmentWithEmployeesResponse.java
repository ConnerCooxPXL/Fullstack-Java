package departmentService.api.response;

import departmentService.domain.Department;

import java.util.ArrayList;
import java.util.List;

public record DepartmentWithEmployeesResponse(
        Long id,
        String name,
        Long organizationId,
        List<EmployeeResponse> employees
) {
    public static DepartmentWithEmployeesResponse toResponse(Department department) {
        return new DepartmentWithEmployeesResponse(
                department.getId(),
                department.getName(),
                department.getOrganizationId(),
                new ArrayList<>()
        );
    }
}