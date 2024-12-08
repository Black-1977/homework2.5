package pro.sky.skyproList.services.impl;

import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.skyproList.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyproList.exceptions.EmployeeNamesNotValidation;
import pro.sky.skyproList.exceptions.EmployeeNotFoundException;
import pro.sky.skyproList.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyproList.model.Employee;
import pro.sky.skyproList.services.EmployeeService;

import java.util.*;

@Service

public class EmployeeServiceImpl implements EmployeeService{
    Map<String, Employee> employees;
    private final int maxEmployees = 10;

    private int currentEmployees;

    public EmployeeServiceImpl() {
        currentEmployees = 0;
        employees = new HashMap<>();
    }

@PostConstruct
private void initEmployees() {
//    add("Ivan", "Ivanov", 40000, 1);
//    add("Petr", "Sidorov", 70000, 2);
//    add("Sidor", "Karpov", 60000, 1);
//    add("Karp", "Petrov", 30000, 1);
}
    @Override
    public String add(String first, String last, int salary, int departmentId) {
        if (!StringUtils.isAlpha(first) || !StringUtils.isAlpha(last)) {
            throw new EmployeeNamesNotValidation();
        }
        String firstName = StringUtils.capitalize(first);
        String lastName = StringUtils.capitalize(last);
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

    @Override
    public int getMaxEmployees() {
        return maxEmployees;
    }
}
