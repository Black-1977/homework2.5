package pro.sky.skyproList.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skyproList.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyproList.exceptions.EmployeeNotFoundException;
import pro.sky.skyproList.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyproList.services.EmployeeService;

@RestController
@RequestMapping("employee")

public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String welcome() {
        return  "Добро пожаловать";
    }

    @GetMapping("add")
    public String add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.add(firstName, lastName, 0, 0) + " добавлен";
        } catch (EmployeeStorageIsFullException e) {
            return "Добавлено максимальное количество сотрудников";
        } catch (EmployeeAlreadyAddedException e) {
            return "Такой сотрудник уже существует";
        }

    }

    @GetMapping("remove")
    public String remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.remove(firstName, lastName) + " удалён";
        } catch (EmployeeNotFoundException e) {
            return "Сотрудник не найден";
        }
    }

    @GetMapping("find")
    public String find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.find(firstName, lastName) + " найден";
        } catch (EmployeeNotFoundException e) {
            return "Сотрудник не найден";
        }
    }

}
