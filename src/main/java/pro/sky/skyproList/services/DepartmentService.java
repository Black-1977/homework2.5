package pro.sky.skyproList.services;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMaxSalary(@RequestParam int departmentId);

    Employee getEmployeeWithMinSalary(@RequestParam int departmentId);

    List<Employee> getEmployeesByDepartment(@RequestParam int departmentId);

    Map<Integer, List<Employee>> getEmployeesGroupedByDepartment();
}
