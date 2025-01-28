package ru.otus.java.basic.homeworks.homework22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.stream.Stream;

class ApplicationTest {

    private Application app;

    @BeforeEach
    void setUp() {
        app = new Application();
    }

    @ParameterizedTest
    @CsvSource({
            "'3,6,1,7,1,4,8,9', '4,8,9'",
            "'3,6,1,7,1,3,2,5', '3,2,5'",
    })
    void testReturnArrayAfterOneTrue(@ConvertWith(ArrayConverter.class) int[] inputArray, @ConvertWith(ArrayConverter.class) int[] expectedArray) {
        int[] result = app.returnArrayAfterOne(inputArray);
        Assertions.assertTrue(isArraysEquals(result, expectedArray));
    }

    @ParameterizedTest
    @CsvSource({
            "'3,6,1,7,1,4,8,9', '1,6,2'",
            "'3,6,1,7,1,3,2,5', '5,4'",
    })
    void testReturnArrayAfterOneFalse(@ConvertWith(ArrayConverter.class) int[] inputArray, @ConvertWith(ArrayConverter.class) int[] expectedArray) {
        int[] result = app.returnArrayAfterOne(inputArray);
        Assertions.assertFalse(isArraysEquals(result, expectedArray));
    }

    @ParameterizedTest
    @CsvSource({
            "'3,6,0,7,3,4,8,9'",
            "'3,6,5,7,6,3,2,5'",
    })
    void testReturnArrayAfterOneThrowException(@ConvertWith(ArrayConverter.class) int[] inputArray) {
        Assertions.assertThrows(RuntimeException.class, () -> app.returnArrayAfterOne(inputArray));
    }

    @ParameterizedTest
    @CsvSource({
            "'3,6,1,7,3,4,8,9'",
            "'3,6,5,7,6,3,2,1'",
    })
    void testReturnArrayAfterOneDoesNotThrowException(@ConvertWith(ArrayConverter.class) int[] inputArray) {
        Assertions.assertDoesNotThrow(() -> app.returnArrayAfterOne(inputArray));
    }

    @ParameterizedTest
    @CsvSource({
            "'2,1,1,2,1,2,1'",
            "'1,1,1,2,2,2'",
    })
    void isArrayContainsOnlyOneAndTwoTrue(@ConvertWith(ArrayConverter.class) int[] inputArray) {
        Assertions.assertTrue(app.isArrayContainsOnlyOneAndTwo(inputArray));
    }

    @ParameterizedTest
    @CsvSource({
            "'2,1,1,2,1,2,1,3'",
            "'2,2,2,2'",
            "'1,1,1,1'",
    })
    void isArrayContainsOnlyOneAndTwoFalse(@ConvertWith(ArrayConverter.class) int[] inputArray) {
        Assertions.assertFalse(app.isArrayContainsOnlyOneAndTwo(inputArray));
    }

    static class ArrayConverter implements ArgumentConverter {
        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            String[] parts = ((String) source).split(",");
            return Stream.of(parts)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    static boolean isArraysEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

}