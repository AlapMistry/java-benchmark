package com.alap.benchmark;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

@Fork(value = 1, warmups = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(Main.COUNT)
@Warmup(iterations = 1)
public class Main {
    public static final int COUNT = 10;

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    static List<Integer> sourceList = new ArrayList<>();
    static {
        for (int i = 0; i < COUNT; i++) {
            sourceList.add(i);
        }
    }

    @Benchmark
    public List<String> list() {
        List<String> strings = new ArrayList<>();
        for (Integer i : sourceList) {
            strings.add(i.toString());
        }

        return strings;
    }

    @Benchmark
    public List<String> stream() {
        List<String> strings = new ArrayList<>();
        sourceList.stream().forEach(i -> strings.add(Integer.toString(COUNT)));

        return strings;
    }
}
