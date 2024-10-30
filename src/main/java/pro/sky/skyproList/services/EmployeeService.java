package pro.sky.skyproList.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service

public interface EmployeeService {

    String add(String firstName, String lastName, int salary, int departmentId);

    String remove(String firstName, String lastName);

    String find(String firstName, String lastName);

    Map<String, Employee> getAll();
}
