package com.cta.pitest.tz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

class TestedClassTest {

    @Test
    void uselessTest() {
        TestedClass testedClass = new TestedClass();
        Assertions.assertThat(testedClass.testedMethod(List.of("thisIsAString", "thisIsAlsoAString"))).isNotNull();
        Assertions.assertThat(testedClass.getTotalStringLength()).isNotEqualTo(0);
    }

    @Test
    void should_return_KO() {
        TestedClass testedClass = new TestedClass();
        Assertions.assertThat(testedClass.testedMethod(List.of("thisIsAString", "thisIsAlsoAString"))).isEqualTo("KO");
    }

    @Test
    void should_return_OK() {
        TestedClass testedClass = new TestedClass();
        Assertions.assertThat(testedClass.testedMethod(List.of("Hello"))).isEqualTo("OK");
    }

    @Test
    void should_detect_poison() {
        TestedClass testedClass = new TestedClass();
        Assertions.assertThat(testedClass.testedMethod(List.of("Test", "Poison"))).isEqualTo("KO");
        Assertions.assertThat(testedClass.getTotalStringLength()).isEqualTo(4);
    }

    @Test
    void should_print() {
        TestedClass testedClass = new TestedClass();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream initialPrintStream = System.out;
        System.setOut(new PrintStream(out));

        Assertions.assertThat(testedClass.testedMethod(List.of("Test"))).isEqualTo("KO");
        Assertions.assertThat(out.toString(StandardCharsets.UTF_8)).isEqualTo("totalStringLength: 4");

        System.setOut(initialPrintStream);
    }

    @Test
    void should_reset_counter() {
        TestedClass testedClass = new TestedClass();

        Assertions.assertThat(testedClass.testedMethod(List.of("Test"))).isEqualTo("KO");
        Assertions.assertThat(testedClass.getTotalStringLength()).isEqualTo(4);

        Assertions.assertThat(testedClass.testedMethod(List.of("Test"))).isEqualTo("KO");
        Assertions.assertThat(testedClass.getTotalStringLength()).isEqualTo(4);
    }
}