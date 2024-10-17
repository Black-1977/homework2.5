package pro.sky.skyproList.services;

import org.springframework.stereotype.Service;
import pro.sky.skyproList.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyproList.exceptions.EmployeeNotFoundException;
import pro.sky.skyproList.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service

public class EmployeeService {
    List<Employee> employees;
    private final int maxEmployees = 1;
    private int currentEmployees;

    public EmployeeService() {
        currentEmployees = 0;
        employees = new ArrayList<>();
    }

    public String add(String firstName, String lastName) {
        if (currentEmployees > 0) {
            for (Employee employee : employees) {
                if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                    throw new EmployeeAlreadyAddedException();
                }
            }
        }
        if (currentEmployees == maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        employees.add(new Employee(firstName, lastName));
        return employees.get(currentEmployees++).toString();
    }

    public String remove(String firstName, String lastName) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                String s = employees.get(i).toString();
                employees.remove(i);
                currentEmployees--;
                return s;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public String find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee.toString();
            }
        }
        throw new EmployeeNotFoundException();
    }
}
