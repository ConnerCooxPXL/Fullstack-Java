package be.pxl.employeeService.repository;

import be.pxl.employeeService.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByDepartmentId(Long departmentId);
    List<Employee> findEmployeesByOrganizationId(Long organizationId);
}
