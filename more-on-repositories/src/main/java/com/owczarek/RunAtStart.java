package com.owczarek;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;
    private final EmployeeRepositoryWithAnnotation employeeRepositoryWithAnnotation;
//    private final EmployeeBaseRepository employeeBaseRepository;
    private final EmployeeRepositoryFromBaseRepository employeeRepositoryFromBaseRepository;
    private final Logger logger = Logger.getLogger(RunAtStart.class);

    @Autowired
    public RunAtStart(
            EmployeeRepository employeeRepository,
            EmployeeGenerator employeeGenerator,
            EmployeeRepositoryWithAnnotation employeeRepositoryWithAnnotation,
//            EmployeeBaseRepository employeeBaseRepository,
            EmployeeRepositoryFromBaseRepository employeeRepositoryFromBaseRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
        this.employeeRepositoryWithAnnotation = employeeRepositoryWithAnnotation;
//        this.employeeBaseRepository = employeeBaseRepository;
        this.employeeRepositoryFromBaseRepository = employeeRepositoryFromBaseRepository;
    }

    @PostConstruct
    public void runAtStart() {
        generateManyEmployees();

        printAll(employeeRepositoryWithAnnotation.findAll());
    }

     private void generateManyEmployees() {
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(
                    employeeGenerator.generate());
        }
    }
    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(logger::info);
    }

}

