package pro.sky.skyproList.services.impl;

import org.junit.jupiter.api.*;
import pro.sky.skyproList.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyproList.exceptions.EmployeeNotFoundException;
import pro.sky.skyproList.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyproList.model.Employee;
import pro.sky.skyproList.services.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void clearEmployees() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    public void testAddNewEmployee() {
        Employee employeeToAdd = new Employee("Павел", "Дуров", 50_000, 1);

        employeeService.add(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartmentId());
        Employee addedEmployee = employeeService.getAll().get(employeeToAdd.getFirstName() + " " + employeeToAdd.getLastName());

        Assertions.assertEquals(employeeToAdd, addedEmployee);
    }

    @Test
    public void testAddExistingEmployee() {
        Employee employeeToAdd = new Employee("Павел", "Дуров", 50_000, 1);
        employeeService.add(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartmentId());

        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                () -> {
                    employeeService.add(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartmentId());
                });
    }

    @Test
    public void testStorageIsFull() {
        for (int i = 0; i < employeeService.getMaxEmployees(); i++) {
            employeeService.add("Павел" + "a".repeat(i + 1), "Дуров", 50_000, 1);
        }
        Assertions.assertThrows(EmployeeStorageIsFullException.class,
                () -> {
                    employeeService.add("Павел", "Дуров", 50_000, 1);
                });
    }

    @Test
    void testRemoveMissingEmployee() {
        employeeService.add("Павел", "Дуров", 50_000, 1);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> {
                    employeeService.remove("Паша", "Дуров");
                });
    }

    @Test
    void testRemoveExistingEmployee() {
        employeeService.add("Павел", "Дуров", 50_000, 1);
        Assertions.assertEquals(employeeService.remove("Павел", "Дуров"), "Павел Дуров");
    }

    @Test
    void testFindMissingEmployee() {
        employeeService.add("Павел", "Дуров", 50_000, 1);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> {
                    employeeService.find("Паша", "Дуров");
                });
    }

    @Test
    void testFindExistingEmployee() {
        employeeService.add("Павел", "Дуров", 50_000, 1);
        Assertions.assertEquals(employeeService.find("Павел", "Дуров"), "Павел Дуров");
    }
}
