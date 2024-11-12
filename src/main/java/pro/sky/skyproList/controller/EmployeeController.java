package pro.sky.skyproList.controller;

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
        String s = "Добро пожаловать \n";
        s += "Добавлен: " + employeeService.add("Ivan", "Ivanov", 40000, 1) + "\n";
        s += "Добавлен: " + employeeService.add("Petr", "Sidorov", 70000, 2) + "\n";
        s += "Добавлен: " + employeeService.add("Sidor", "Karpov", 60000, 1) + "\n";
        s += "Добавлен: " + employeeService.add("Karp", "Petrov", 30000, 1) + "\n";
        return s;
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
