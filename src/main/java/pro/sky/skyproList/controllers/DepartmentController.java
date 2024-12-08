package pro.sky.skyproList.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.skyproList.services.DepartmentService;
import pro.sky.skyproList.model.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departments")

public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{departmentId}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable int departmentId) {

        return departmentService.getEmployeesByDepartment(departmentId);
    }

    @GetMapping("{departmentId}/employees/sum")
    public int getEmployeeSalarySum(@PathVariable int departmentId) {

        return departmentService.getEmployeeSalarySum(departmentId);
    }

    @GetMapping("{departmentId}/employees/max")
    public Employee getEmployeeWithMaxSalary(@PathVariable int departmentId) {

        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("{departmentId}/employees/min")
    public Employee getEmployeeWithMinSalary(@PathVariable int departmentId) {

        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("employees")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {

        return departmentService.getEmployeesGroupedByDepartment();
    }
}
