package pizzashop.service.test.mocks;

import pizzashop.model.Payment;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class MockPaymentRepository extends PaymentRepository {
    List<Payment> getAllResult;
    public int addMethodCalledTimes = 0;

    public MockPaymentRepository() { }

    public MockPaymentRepository(List<Payment> getAllResult) {
        super();
        this.getAllResult = getAllResult;
    }

    public List<Payment> getAll() {
        return getAllResult;
    }

    public void add(Payment payment){
        addMethodCalledTimes++;
    }
}
