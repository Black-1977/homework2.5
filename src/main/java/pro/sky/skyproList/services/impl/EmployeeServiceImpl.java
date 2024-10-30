package pro.sky.skyproList.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.skyproList.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyproList.exceptions.EmployeeNotFoundException;
import pro.sky.skyproList.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyproList.services.Employee;
import pro.sky.skyproList.services.EmployeeService;

import java.util.*;

@Service

public class EmployeeServiceImpl implements EmployeeService{
    Map<String, Employee> employees;
    private final int maxEmployees;

    {
        maxEmployees = 10;
    }

    private int currentEmployees;

    public EmployeeServiceImpl() {
        currentEmployees = 0;
        employees = new HashMap<>();
    }

    @Override
    public String add(String firstName, String lastName, int salary, int departmentId) {
        String s = firstName + " " + lastName;
        if (currentEmployees > 0) {
            if (employees.containsKey(s)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        if (currentEmployees == maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        employees.put(s, new Employee(firstName, lastName, salary, departmentId));
        currentEmployees++;
        return s;
    }

    @Override
    public String remove(String firstName, String lastName) {
        String s = firstName + " " + lastName;
        if (employees.containsKey(s)) {
            employees.remove(s);
            currentEmployees--;
            return s;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public String find(String firstName, String lastName) {
        String s = firstName + " " + lastName;
        if (employees.containsKey(s)) {
            return s;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Map<String, Employee> getAll() {
        return employees;
    }
}
