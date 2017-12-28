package com.owczarek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void runAtStart() {
        Employee employee = new Employee();
        employee.setFirstName("Marcin");
        employee.setLasName("Karolak");
        employee.setSalary(new BigDecimal("3000.0"));

        employeeRepository.save(employee);

        Iterable<Employee> marcins = employeeRepository.findByFirstName("Marcin");
        Employee marcin = marcins.iterator().next();
        System.out.println("Marcin: " + marcin);
    }
}

