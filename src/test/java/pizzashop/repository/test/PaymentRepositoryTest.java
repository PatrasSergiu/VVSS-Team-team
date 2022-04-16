package pizzashop.repository.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository = new PaymentRepository();

    @BeforeEach
    void setUp() {
    }

    @Test
    public void add() {
        Payment p1 = Mockito.mock(Payment.class);
        Mockito.when(p1.getTableNumber()).thenReturn(1);
        Mockito.when(p1.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(p1.getAmount()).thenReturn(15.0);
        Mockito.when(p1.toString()).thenReturn("1, Cash, 15.0");

        Payment p2 = Mockito.mock(Payment.class);
        Mockito.when(p2.getTableNumber()).thenReturn(2);
        Mockito.when(p2.getType()).thenReturn(PaymentType.Card);
        Mockito.when(p2.getAmount()).thenReturn(10.0);
        Mockito.when(p2.toString()).thenReturn("2, Cash, 10.0");

        paymentRepository.add(p1);
        paymentRepository.add(p2);

        assertEquals(2, paymentRepository.getAll().size());
        assertEquals(10.0, paymentRepository.getAll().get(1).getAmount());
        assertEquals(PaymentType.Cash, paymentRepository.getAll().get(0).getType());

        Mockito.verify(p1, Mockito.times(1)).getType();
        Mockito.verify(p2, Mockito.times(1)).getAmount();
    }

    @Test
    void getAll() {
        Payment p1 = Mockito.mock(Payment.class);
        Payment p2 = Mockito.mock(Payment.class);
        Payment p3 = Mockito.mock(Payment.class);

        paymentRepository.add(p1);
        paymentRepository.add(p2);
        paymentRepository.add(p3);

        assertEquals(p1, paymentRepository.getAll().get(0));
        assertEquals(p2, paymentRepository.getAll().get(1));
        assertEquals(p3, paymentRepository.getAll().get(2));
    }

}