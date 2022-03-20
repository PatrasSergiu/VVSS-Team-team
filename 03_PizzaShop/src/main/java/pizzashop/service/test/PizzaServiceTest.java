package pizzashop.service.test;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.test.mocks.MockPaymentRepository;
import pizzashop.service.PizzaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(2)
@DisplayName("Test Class for Pizza Service")
class PizzaServiceTest {

    PizzaService pizzaService;
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    // ECP

    @Test
    @Tag("getTotalAmount")
    void ECP_getTotalAmount_When_CashPaymentAmount14_Returns14() {
        //Arrange
        List<Payment> mockGetPaymentsResult = new ArrayList<>(Arrays.asList(
                new Payment(1, PaymentType.Cash, 10),
                new Payment(2, PaymentType.Card, 20),
                new Payment(3, PaymentType.Cash, 4)
        ));
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository(mockGetPaymentsResult);
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        double result = pizzaService.getTotalAmount(PaymentType.Cash);

        //Assert
        assertEquals(14, result);
    }

    @Test
    @Tag("getTotalAmount")
    void ECP_getTotalAmount_When_EmptyPaymentsList_Returns0() {
        //Arrange
        List<Payment> mockGetPaymentsResult = new ArrayList<>();
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository(mockGetPaymentsResult);
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        double result = pizzaService.getTotalAmount(PaymentType.Cash);

        //Assert
        assertEquals(0, result);
    }

    @Test
    @Tag("addPayment")
    void ECP_addPayment_When_ValidInput_PayRepo_Add_IsCalledOnce() throws Exception {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository();
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        pizzaService.addPayment(4, PaymentType.Card, 10);

        //Assert
        assertEquals(1, mockPaymentRepository.addMethodCalledTimes);
    }

    @Test
    @Tag("addPayment")
    void ECP_addPayment_When_Table_LessThan1_ThrowsException() {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository();
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        Exception exception = assertThrows(Exception.class, () -> {
            pizzaService.addPayment(-2, PaymentType.Card, 10);
        });
        String expectedMessage = "Invalid table number";

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(0, mockPaymentRepository.addMethodCalledTimes);
    }

    @Test
    @Tag("addPayment")
    void ECP_addPayment_When_Table_GreaterThan8_ThrowsException() {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository();
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        Exception exception = assertThrows(Exception.class, () -> {
            pizzaService.addPayment(15, PaymentType.Card, 10);
        });
        String expectedMessage = "Invalid table number";

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(0, mockPaymentRepository.addMethodCalledTimes);
    }

    //BVA

    @Test
    @Tag("getTotalAmount")
    void BVA_getTotalAmount_When_CashPaymentAmount1_Returns1() {
        //Arrange
        List<Payment> mockGetPaymentsResult = new ArrayList<>(Arrays.asList(
                new Payment(1, PaymentType.Cash, 10),
                new Payment(2, PaymentType.Card, 20),
                new Payment(3, PaymentType.Cash, -9)
        ));
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository(mockGetPaymentsResult);
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        double result = pizzaService.getTotalAmount(PaymentType.Cash);

        //Assert
        assertEquals(1, result);
    }

    @Test
    @Tag("getTotalAmount")
    void BVA_getTotalAmount_When_NullPaymentsList_Returns0() {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository(null);
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        double result = pizzaService.getTotalAmount(PaymentType.Cash);

        //Assert
        assertEquals(0, result);
    }

    @Test
    @Tag("getTotalAmount")
    void BVA_getTotalAmount_When_CashPaymentAmountNegative1_ReturnsNegative1() {
        //Arrange
        List<Payment> mockGetPaymentsResult = new ArrayList<>(Arrays.asList(
                new Payment(1, PaymentType.Cash, 10),
                new Payment(2, PaymentType.Card, 20),
                new Payment(3, PaymentType.Cash, -11)
        ));
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository(mockGetPaymentsResult);
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        double result = pizzaService.getTotalAmount(PaymentType.Cash);

        //Assert
        assertEquals(-1, result);
    }

    @Test
    @Tag("addPayment")
    void BVA_addPayment_When_Table_Is0_ThrowsException() {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository();
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        Exception exception = assertThrows(Exception.class, () -> {
            pizzaService.addPayment(0, PaymentType.Card, 10);
        });
        String expectedMessage = "Invalid table number";

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(0, mockPaymentRepository.addMethodCalledTimes);
    }

    @Test
    @Tag("addPayment")
    void BVA_addPayment_When_Table_Is1_PayRepo_Add_IsCalledOnce() throws Exception {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository();
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        pizzaService.addPayment(1, PaymentType.Card, 10);

        //Assert
        assertEquals(1, mockPaymentRepository.addMethodCalledTimes);
    }

    @Test
    @Tag("addPayment")
    void BVA_addPayment_When_Table_Is8_PayRepo_Add_IsCalledOnce() throws Exception {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository();
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        pizzaService.addPayment(8, PaymentType.Card, 10);

        //Assert
        assertEquals(1, mockPaymentRepository.addMethodCalledTimes);
    }

    @Test
    @Tag("addPayment")
    void BVA_addPayment_When_Table_Is9_ThrowsException() {
        //Arrange
        MockPaymentRepository mockPaymentRepository = new MockPaymentRepository();
        pizzaService = new PizzaService(null, mockPaymentRepository);

        //Act
        Exception exception = assertThrows(Exception.class, () -> {
            pizzaService.addPayment(9, PaymentType.Card, 10);
        });
        String expectedMessage = "Invalid table number";

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(0, mockPaymentRepository.addMethodCalledTimes);
    }

}