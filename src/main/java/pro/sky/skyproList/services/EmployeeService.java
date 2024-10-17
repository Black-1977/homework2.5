package pro.sky.skyproList.services;

import org.springframework.stereotype.Service;
import pro.sky.skyproList.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyproList.exceptions.EmployeeNotFoundException;
import pro.sky.skyproList.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service

public class EmployeeService {
    Map<String, Employee> employees;
    private final int maxEmployees = 1;
    private int currentEmployees;

    public EmployeeService() {
        currentEmployees = 0;
        employees = new HashMap<>();
    }

    public String add(String firstName, String lastName) {
        String s = firstName + " " + lastName;
        if (currentEmployees > 0) {
            if (employees.containsKey(s)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        if (currentEmployees == maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        employees.put(s, new Employee(firstName, lastName));
        currentEmployees++;
        return s;
    }

    public String remove(String firstName, String lastName) {
        String s = firstName + " " + lastName;
        if (employees.containsKey(s)) {
            employees.remove(s);
            currentEmployees--;
            return s;
        }
        throw new EmployeeNotFoundException();
    }

    public String find(String firstName, String lastName) {
        String s = firstName + " " + lastName;
         if (employees.containsKey(s)) {
                return s;
            }
        throw new EmployeeNotFoundException();
    }
}
