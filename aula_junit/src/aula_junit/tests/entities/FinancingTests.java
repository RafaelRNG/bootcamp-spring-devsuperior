package aula_junit.tests.entities;

import aula_junit.entities.Financing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinancingTests {

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