package aula_junit.tests.entities;

import aula_junit.entities.Financing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinancingTests {

    @Test
    public void constructorShouldcreateAFinancingWhenInstanceANewObject() {
        Financing financing = new Financing(1l, 200000.00, 4000.00, 80);

        Assertions.assertInstanceOf(Financing.class, financing);
    }

    @Test
    public void constructorShouldDoesNotCreateAFinancingWhenInstanceANewObjectInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(1l, 20000000.00, 4000.00, 80);

            Assertions.assertInstanceOf(Financing.class, financing);
        });
    }

    @Test
    public void setTotalAmountShouldChangeTotalAmountOfFinancing() {
        Financing financing = new Financing(1l, 200000.00, 4000.00, 80);
        Double newValueTotalAmount = 10000.00;
        financing.setTotalAmount(newValueTotalAmount);

        Assertions.assertEquals(newValueTotalAmount, financing.getTotalAmount());
    }

    @Test
    public void setTotalAmountShuldThorwExceptionWhenRequestChangeTotalAmountNotValid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(1l, 200000.00, 4000.00, 80);

            Double newValueTotalAmount = 10000000.00;
            financing.setTotalAmount(newValueTotalAmount);

            Assertions.assertEquals(newValueTotalAmount, financing.getTotalAmount());
        });
    }

    @Test
    public void setIncomeShouldChangeIncomeOfFinancing() {
        Financing financing = new Financing(1l, 200000.00, 4000.00, 80);

        financing.setIncome(5000.00);

        Assertions.assertEquals(5000.00, financing.getIncome());
    }

    @Test
    public void setIncomeShuldThorwExceptionWhenRequestChangeIncomeNotValid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(1l, 200000.00, 4000.00, 80);

            financing.setIncome(300.00);

            Assertions.assertEquals(300.00, financing.getIncome());
        });
    }

    @Test
    public void setMonthsShouldChangeMonthOfFinancingWhenRequestChangeMonth() {
        Financing financing = new Financing(1l, 200000.00, 4000.00, 80);

        financing.setMonths(160);

        Assertions.assertEquals(160, financing.getMonths());
    }

    @Test
    public void setMonthsShouldThorwExceptionWhenRequestChangeMonthNotValid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(1l, 200000.00, 4000.00, 80);

            financing.setMonths(60);

            Assertions.assertEquals(60, financing.getMonths());
        });
    }

    @Test
    public void entryShouldInitialTotalAmount() {
        Financing financing = new Financing(1l, 200000.00, 4000.00, 80);

        Assertions.assertEquals( 40000.00, financing.entry());
    }

    @Test
    public void quotaShouldValueQuota() {
        Financing financing = new Financing(1l, 200000.00, 4000.00, 80);

        Assertions.assertEquals( 2000.00, financing.quota());
    }
}