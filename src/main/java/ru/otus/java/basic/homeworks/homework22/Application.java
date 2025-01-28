package ru.otus.java.basic.homeworks.homework22;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Application {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public int[] returnArrayAfterOne(int[] array) {
        LOGGER.debug("Попали в метод returnArrayAfterOne");
        int lastOneindex = -1;
        for (int i = array.length - 1; i > 0 ; i--) {
            if (array[i] == 1) {
                lastOneindex = i;
                break;
            }
        }
        if(lastOneindex == -1) {
            LOGGER.error("Ошибка! Исключение RuntimeException в методе returnArrayAfterOne");
            throw new RuntimeException();
        }
        int[] result = new int[array.length - lastOneindex - 1];
        System.arraycopy(array, lastOneindex + 1, result, 0, result.length);
        LOGGER.info("Мы расчитали результат = {}", Arrays.toString(result));
        return result;
    }

    public boolean isArrayContainsOnlyOneAndTwo(int[] array) {
        LOGGER.debug("Попали в метод isArrayContainsOnlyOneAndTwo");
        int OneCount = 0;
        int TwoCount = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] != 1 && array[i] != 2) {
                LOGGER.debug("Массив содержит значение отличное от 1 и 2");
                return false;
            }
            if(array[i] == 1) {
                OneCount++;
            }
            if(array[i] == 2) {
                TwoCount++;
            }
        }
        LOGGER.info("Мы расчитали результат = {}", OneCount > 0 && TwoCount > 0);
        return OneCount > 0 && TwoCount > 0;
    }

}
