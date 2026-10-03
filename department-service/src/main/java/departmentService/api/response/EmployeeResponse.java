package departmentService.api.response;

public record EmployeeResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {}
