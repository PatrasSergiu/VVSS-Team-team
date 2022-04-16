package pizzashop.integrationTesting.step3;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationStep3 {

    private PaymentRepository paymentRepository = new PaymentRepository();
    private PizzaService pizzaService = new PizzaService(null, paymentRepository);

    @Test
    void testAdd() throws Exception {
        Payment p1 = new Payment(1, PaymentType.Card, 10);
        Payment p2 = new Payment(2, PaymentType.Cash, 20);
        int size = pizzaService.getPayments().size();

        pizzaService.addPayment(p1);
        pizzaService.addPayment(p2);

        assertEquals(size + 2, pizzaService.getPayments().size());
        assertEquals(pizzaService.getPayments().get(size), p1);
        assertEquals(pizzaService.getPayments().get(size + 1), p2);
        assertEquals(pizzaService.getPayments().get(size).getTableNumber(), 1);
        assertEquals(pizzaService.getPayments().get(size).getType(), PaymentType.Card);
        assertEquals(pizzaService.getPayments().get(size + 1).getTableNumber(), 2);
        assertEquals(pizzaService.getPayments().get(size + 1).getType(), PaymentType.Cash);
    }

    @Test
    void testGetAll() throws Exception {
        Payment p1 = new Payment(1, PaymentType.Card, 10);
        Payment p2 = new Payment(2, PaymentType.Cash, 20);
        Payment p3 = new Payment(3, PaymentType.Card, 4);

        pizzaService.addPayment(p1);
        pizzaService.addPayment(p1);
        pizzaService.addPayment(p2);
        pizzaService.addPayment(p2);
        pizzaService.addPayment(p3);
        pizzaService.addPayment(p3);

        assertEquals(6, pizzaService.getPayments().size());
        assertEquals(pizzaService.getPayments().get(0), p1);
        assertEquals(pizzaService.getPayments().get(1), p1);
        assertEquals(pizzaService.getPayments().get(0).getTableNumber(), 1);
        assertEquals(pizzaService.getPayments().get(1).getTableNumber(), 1);
        assertEquals(pizzaService.getPayments().get(2), p2);
        assertEquals(pizzaService.getPayments().get(3), p2);
        assertEquals(pizzaService.getPayments().get(2).getTableNumber(), 2);
        assertEquals(pizzaService.getPayments().get(3).getTableNumber(), 2);
        assertEquals(pizzaService.getPayments().get(4), p3);
        assertEquals(pizzaService.getPayments().get(5), p3);
        assertEquals(pizzaService.getPayments().get(4).getTableNumber(), 3);
        assertEquals(pizzaService.getPayments().get(5).getTableNumber(), 3);
    }

}
