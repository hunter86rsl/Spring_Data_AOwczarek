package com.owczarek;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;
    private final Logger logger = Logger.getLogger(RunAtStart.class);

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
    }

    @PostConstruct
    public void runAtStart() {
        generateManyEmployees();
// 1
//        logger.info("ALL EMPLOYEES");
//        printAll(employeeRepository.findByFirstNameIgnoreCase("jOHN"));

// 2
        printAll(employeeRepository.findByLastNameOrderByFirstNameDesc("Smith"));

// 3
//        printAll(employeeRepository.findBySalaryBetween(
//                new BigDecimal("1000.0"),
//                new BigDecimal("2000.0")));

// 4
//        logger.info("FIRST JOHN: " + employeeRepository.findFirstByFirstName("John"));
//        logger.info("FIRST ARTHUR: " + employeeRepository.findFirstByFirstName("Arthur"));

// 5
//        printAll(employeeRepository.findTop3ByFirstName("John"));
//        printAll(employeeRepository.findFirst3ByFirstName("John"));

// 6
//        logger.info(
//                String.format(
//                        "Number of John Smith's: %d",
//                        employeeRepository.countByFirstNameAndLastNameIgnoreCase(
//                                "John",
//                                "Smith")));

//  7
//        Page<Employee> johnPage = employeeRepository.findByFirstName(
//                "John",
//                new PageRequest(1, 3));
//        printAll(johnPage.getContent());
//        logger.info("Total number of pages: " + johnPage.getTotalPages());

//  8
//        Stream<Employee> johnStream = employeeRepository.findTop10ByFirstName("John");
//        List<String> johnsLastName = johnStream
//                .map(Employee::getLastName)
//                .collect(Collectors.toList());
//        logger.info(johnsLastName);

        employeeRepository
                .findFirstByFirstNameIgnoreCase("John")
                .thenAccept(john -> {
                    logger.info ("John: " + john);
        });

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

