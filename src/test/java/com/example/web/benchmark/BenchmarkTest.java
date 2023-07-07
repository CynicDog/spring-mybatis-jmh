package com.example.web.benchmark;

import com.example.web.SpringBootWebApplication;
import com.example.web.service.HumanResourceService;
import com.example.web.util.FetchType;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@Threads(1)
public class BenchmarkTest {
    private HumanResourceService humanResourceService;
    private ConfigurableApplicationContext context;
    @Param({"3", "5"})
    public int iterations;

    @Setup(Level.Invocation)
    public void setupInvokation() throws Exception {
    }

    @Setup(Level.Iteration)
    public void setupIteration() throws Exception {
        if (context != null) {
            context.close();
        }
        context = SpringApplication.run(SpringBootWebApplication.class);
        context.registerShutdownHook();

        humanResourceService = context.getBean(HumanResourceService.class);
    }

    @Benchmark @BenchmarkMode(Mode.AverageTime)
    @Fork(warmups = 1, value = 1)
    @Warmup(batchSize = -1, iterations = 2, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(batchSize = -1, iterations = 5, time = 50, timeUnit = TimeUnit.MILLISECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void test() throws Exception {
        humanResourceService.getEmployeesByJobId("ST_CLERK", FetchType.EAGER, FetchType.EAGER, FetchType.EAGER);
    }

    @Test
    public void benchmark() throws Exception {
        String[] argv = {};
        org.openjdk.jmh.Main.main(argv);
    }

    @TearDown
    public void closeContext(){
        context.close();
    }
}