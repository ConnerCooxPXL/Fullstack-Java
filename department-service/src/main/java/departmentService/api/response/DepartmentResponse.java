package departmentService.api.response;

import departmentService.domain.Department;

import java.util.ArrayList;
import java.util.List;

public record DepartmentResponse(
        Long id,
        String name,
        Long organizationId
) {
    public static DepartmentResponse toResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getOrganizationId()
        );
    }
}