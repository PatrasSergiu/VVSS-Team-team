package pizzashop.service.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.PizzaService;
import pizzashop.service.test.mocks.MockPaymentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTestF02 {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTotalAmount_P01() {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository(null);
        PizzaService pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        double result = pizzaService.getTotalAmount(PaymentType.Cash);

        //Assert
        assertEquals(0, result);
    }


    @Test
    void getTotalAmount_P02() {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository(new ArrayList<>());
        PizzaService pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        double result = pizzaService.getTotalAmount(PaymentType.Cash);

        //Assert
        assertEquals(0, result);
    }


    @Test
    void getTotalAmount_P03() {
        //Arrange
        List<Payment> mockGetPaymentsResult = new ArrayList<>(Arrays.asList(
                new Payment(1, PaymentType.Cash, 10),
                new Payment(2, PaymentType.Cash, 20),
                new Payment(3, PaymentType.Cash, 4)
        ));
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository(mockGetPaymentsResult);
        PizzaService pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        double result = pizzaService.getTotalAmount(PaymentType.Card);

        //Assert
        assertEquals(0, result);
    }


    @Test
    void getTotalAmount_P04() {
        //Arrange
        List<Payment> mockGetPaymentsResult = new ArrayList<>(Arrays.asList(
                new Payment(1, PaymentType.Cash, 10),
                new Payment(2, PaymentType.Card, 20),
                new Payment(3, PaymentType.Cash, 4)
        ));
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository(mockGetPaymentsResult);
        PizzaService pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        double result = pizzaService.getTotalAmount(PaymentType.Cash);

        //Assert
        assertEquals(14, result);
    }
}