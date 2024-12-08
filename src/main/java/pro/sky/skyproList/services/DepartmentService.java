package pro.sky.skyproList.services;

import org.springframework.web.bind.annotation.RequestParam;
import pro.sky.skyproList.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    int getEmployeeSalarySum(int departmentId);

    Employee getEmployeeWithMaxSalary(int departmentId);

    Employee getEmployeeWithMinSalary(int departmentId);

    List<Employee> getEmployeesByDepartment(int departmentId);

    Map<Integer, List<Employee>> getEmployeesGroupedByDepartment();
}
