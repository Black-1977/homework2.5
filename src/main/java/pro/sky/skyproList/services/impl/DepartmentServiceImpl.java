package pro.sky.skyproList.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.skyproList.services.DepartmentService;
import pro.sky.skyproList.model.Employee;
import pro.sky.skyproList.services.EmployeeService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public int getEmployeeSalarySum(int departmentId) {
        return employeeService.getAll()
                .values()
                .stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .mapToInt(Employee :: getSalary)
                .sum();
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {
        return employeeService.getAll()
                .values()
                .stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee getEmployeeWithMinSalary(int departmentId) {
        return employeeService.getAll()
                .values()
                .stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        return employeeService.getAll()
                .values()
                .stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .toList();
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return employeeService.getAll()
                .values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
