package pizzashop.service.test;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Payment payment;
    @Before
    public void setUp() {
        payment = Mockito.mock(Payment.class);
    }

    @Test
    void getAmount() {
        //Mockito.when(payment.getAmount()).thenReturn(0D);
        //System.out.println(payment.getAmount());
    }

    @Test
    void setAmount() {
    }
}