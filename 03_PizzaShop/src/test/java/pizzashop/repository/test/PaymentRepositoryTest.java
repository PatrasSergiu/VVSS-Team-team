package pizzashop.repository.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);

    @BeforeEach
    void setUp() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
    }

    @Test
    void add() {
        Payment p1 = new Payment(1, PaymentType.Cash, 10);
        Payment p2 = new Payment(2, PaymentType.Cash, 20);
        Mockito.doNothing().when(paymentRepository).add(p1);
        Mockito.doNothing().when(paymentRepository).add(p1);
    }

    @Test
    void getAll() {
        Payment p1 = new Payment(1, PaymentType.Cash, 10);
        Payment p2 = new Payment(2, PaymentType.Cash, 20);
        Mockito.when(paymentRepository.getAll()).thenReturn(Arrays.asList(p1, p2));

        assertEquals(p1, paymentRepository.getAll().get(0));
        assertEquals(p2, paymentRepository.getAll().get(1));
    }
}