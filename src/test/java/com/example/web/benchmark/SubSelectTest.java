//package com.example.web.benchmark;
//
//import com.example.web.mapper.DepartmentDao;
//import com.example.web.mapper.EmployeeDao;
//import com.example.web.mapper.JobDao;
//import com.example.web.service.HumanResourceService;
//import com.example.web.vo.Employee;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openjdk.jmh.annotations.*;
//import org.openjdk.jmh.runner.Runner;
//import org.openjdk.jmh.runner.RunnerException;
//import org.openjdk.jmh.runner.options.Options;
//import org.openjdk.jmh.runner.options.OptionsBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//@SpringBootTest
//@BenchmarkMode(Mode.AverageTime)
//@State(Scope.Benchmark)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
//public class SubSelectTest {
//
//    @Test
//    public void runBenchmark() throws RunnerException {
//
//        Options options = new OptionsBuilder()
//                .include(SubSelectTest.class.getSimpleName())
//                .forks(1)
//                .build();
//
//        new Runner(options).run();
//    }
//
//    @Autowired DepartmentDao departmentDao;
//    @Autowired EmployeeDao employeeDao;
//    @Autowired JobDao jobDao;
//    HumanResourceService humanResourceService;
//
//    @Setup
//    public void setup() {
//        this.humanResourceService = new HumanResourceService(departmentDao, employeeDao, jobDao);
//    }
//
//    @Benchmark
//    public List<Employee> testGetEmployeesByJobIdBenchmark() {
//        return humanResourceService.getEmployeesByJobId("ST_CLERK");
//    }
//}
