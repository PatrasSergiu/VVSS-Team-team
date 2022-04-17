package pizzashop.integrationTesting.step1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;
import pizzashop.service.test.mocks.MockPaymentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PizzaServiceTestF02 {

    private PaymentRepository mockitoRepo;
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        mockitoRepo = Mockito.mock(PaymentRepository.class);
        pizzaService = new PizzaService(null, mockitoRepo);
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
                new Payment(2, PaymentType.Card, 20),
                new Payment(3, PaymentType.Cash, 4)
        ));
        PizzaService pizzaService = new PizzaService(null, mockitoRepo);

        //Act
        Mockito.when(mockitoRepo.getAll()).thenReturn(mockGetPaymentsResult);
        double result = pizzaService.getTotalAmount(PaymentType.Card);

        //Assert
        Mockito.verify(mockitoRepo, Mockito.times(1)).getAll();
        assertEquals(20, result);
    }


    @Test
    void getTotalAmount_P04() {
        //Arrange
        List<Payment> mockGetPaymentsResult = new ArrayList<>(Arrays.asList(
                new Payment(1, PaymentType.Card, 10),
                new Payment(2, PaymentType.Cash, 20),
                new Payment(3, PaymentType.Card, 4)
        ));
        PizzaService pizzaService = new PizzaService(null, mockitoRepo);

        //Actpizzashop.service.test.PizzaServiceTestF02
        Mockito.when(mockitoRepo.getAll()).thenReturn(mockGetPaymentsResult);
        double result = pizzaService.getTotalAmount(PaymentType.Card);

        //Assert
        assertEquals(14, result);
    }

    @Test
    void add_P05() throws Exception {
        Payment p1 = new Payment(1, PaymentType.Card, 10);
        Mockito.doNothing().when(mockitoRepo).add(p1);
        pizzaService.addPayment(p1);
        Mockito.verify(mockitoRepo, Mockito.times(1)).add(p1);
    }
}