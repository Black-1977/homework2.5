package pro.sky.skyproList.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyproList.model.Employee;
import pro.sky.skyproList.services.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final Map<String, Employee> employees = new HashMap<>(){{
        put("Ivan Ivanov", new Employee("Ivan", "Ivanov", 40_000, 1));
        put("Sidor Karpov", new Employee("Sidor", "Karpov", 60_000, 1));
        put("Karp Petrov", new Employee("Karp", "Petrov", 30_000, 1));
    }};


    @Test
    void testGetEmployeeSalarySum() {
        int departmentId = 1;
        int expectedSalarySum = 130_000;

        Mockito.when(employeeService.getAll()).thenReturn(employees);

        int actualSalarySum = departmentService.getEmployeeSalarySum(departmentId);

        Assertions.assertEquals(expectedSalarySum, actualSalarySum);
    }

    @Test
    void testGetEmployeeWithMaxSalary() {
        int departmentId = 1;
        Employee expectedEmployee = employees.get("Sidor Karpov");

        Mockito.when(employeeService.getAll()).thenReturn(employees);

        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void testGetEmployeeWithMinSalary() {
        int departmentId = 1;
        Employee expectedEmployee = employees.get("Karp Petrov");

        Mockito.when(employeeService.getAll()).thenReturn(employees);

        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void testGetEmployeesByDepartment() {
        int departmentId = 1;
        List<Employee> expectedEmployees = new ArrayList<>(employees.values());

        Mockito.when(employeeService.getAll()).thenReturn(employees);

        List<Employee> actualEmployees = departmentService.getEmployeesByDepartment(departmentId);

        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void testGetEmployeesGroupedByDepartment() {
        Map<Integer, List<Employee>> expectedEmployees = new HashMap<>(){{
            put(1, new ArrayList<>(employees.values()));
        }};

        Mockito.when(employeeService.getAll()).thenReturn(employees);

        Map<Integer, List<Employee>> actualEmployees = departmentService.getEmployeesGroupedByDepartment();

        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }
}