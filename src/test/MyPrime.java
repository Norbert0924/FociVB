import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyPrime {
    @Test
    void isPrime() {
        boolean result = Prime.isPrime(11);

        assertEquals(true, result);
    }

    @Test
    void isPrime1() {
        boolean result = Prime.isPrime(11);

        assertEquals(false, result);
    }

    @Test
    void isPrime2() {
        boolean expected = false;
        boolean result = Prime.isPrime(9);

        assertEquals(expected, result);
    }

    @Test
    void isPrime3() {
        boolean expected = true;
        boolean result = Prime.isPrime(9);

        assertEquals(expected, result);
    }
}