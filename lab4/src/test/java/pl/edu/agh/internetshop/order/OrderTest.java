package pl.edu.agh.internetshop.order;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.*;
import pl.edu.agh.internetshop.order.Order;
import pl.edu.agh.internetshop.order.OrderEntry;
import pl.edu.agh.internetshop.order.OrderEntryAggregator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

public class OrderTest {

	private Order getOrderWithMockedOrderEntries() {
		OrderEntryAggregator expectedOrderEntries = mock(OrderEntryAggregator.class);
		Buyer buyer = mock(Buyer.class);
		return new Order(expectedOrderEntries, buyer);
	}

	@Test
	public void testGetProductThroughOrder() {
		// given
		OrderEntryAggregator expectedOrderEntries = mock(OrderEntryAggregator.class);
		Order order = new Order(expectedOrderEntries, mock(Buyer.class));

		// when
		OrderEntryAggregator actualOrderEntries = order.getOrderEntries();

		// then
		assertSame(expectedOrderEntries, actualOrderEntries);
	}

	@Test
	public void testSetShipment() throws Exception {
		// given
		Order order = getOrderWithMockedOrderEntries();
		Shipment expectedShipment = mock(Shipment.class);

		// when
		order.setShipment(expectedShipment);

		// then
		assertSame(expectedShipment, order.getShipment());
	}

	@Test
	public void testShipmentWithoutSetting() throws Exception {
		// given
		Order order = getOrderWithMockedOrderEntries();

		// when

		// then
		assertNull(order.getShipment());
	}

	@Test
	public void testGetPrice() throws Exception {
		// given
		BigDecimal expectedOrderPrice = BigDecimal.valueOf(20.00);
		OrderEntry orderEntry = mock(OrderEntry.class);
		given(orderEntry.getPrice()).willReturn(expectedOrderPrice);
		Collection<OrderEntry> orderEntries = new ArrayList<>(Collections.singletonList(orderEntry));

		OrderEntryAggregator orderEntryAggregator = new OrderEntryAggregator(orderEntries);
		Order order = new Order(orderEntryAggregator, mock(Buyer.class));

		// when
		BigDecimal actualOrderPrice = order.getPrice();

		// then
		assertBigDecimalCompareValue(expectedOrderPrice, actualOrderPrice);
	}

	private Order getOrderWithCertainProductPrice(double productPriceValue) {
		BigDecimal productPrice = BigDecimal.valueOf(productPriceValue);
		OrderEntryAggregator orderEntryAggregator = mock(OrderEntryAggregator.class);
		given(orderEntryAggregator.getPrice()).willReturn(productPrice);
		return new Order(orderEntryAggregator, mock(Buyer.class));
	}

	@Test
	public void testPriceWithTaxesWithoutRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(2); // 2 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(2.46)); // 2.46 PLN
	}

	@Test
	public void testPriceWithTaxesWithRoundDown() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(0.01); // 0.01 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.01)); // 0.01 PLN
																							
	}

	@Test
	public void testPriceWithTaxesWithRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(0.03); // 0.03 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.04)); // 0.04 PLN
																							
	}

	@Test
	public void testSetShipmentMethod() {
		// given
		Order order = getOrderWithMockedOrderEntries();
		ShipmentMethod surface = mock(SurfaceMailBus.class);

		// when
		order.setShipmentMethod(surface);

		// then
		assertSame(surface, order.getShipmentMethod());
	}

	@Test
	public void testSending() {
		// given
		Order order = getOrderWithMockedOrderEntries();
		SurfaceMailBus surface = mock(SurfaceMailBus.class);
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// whenf
		order.setShipmentMethod(surface);
		order.setShipment(shipment);
		order.send();

		// then
		assertTrue(order.isSent());
	}

	@Test
	public void testIsSentWithoutSending() {
		// given
		Order order = getOrderWithMockedOrderEntries();
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when

		// then
		assertFalse(order.isSent());
	}

	@Test
	public void testWhetherIdExists() throws Exception {
		// given
		Order order = getOrderWithMockedOrderEntries();

		// when

		// then
		assertNotNull(order.getId());
	}

	@Test
	public void testSetPaymentMethod() throws Exception {
		// given
		Order order = getOrderWithMockedOrderEntries();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);

		// when
		order.setPaymentMethod(paymentMethod);

		// then
		assertSame(paymentMethod, order.getPaymentMethod());
	}

	@Test
	public void testPaying() throws Exception {
		// given
		Order order = getOrderWithMockedOrderEntries();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);
		given(paymentMethod.commit(any(MoneyTransfer.class))).willReturn(true);
		MoneyTransfer moneyTransfer = mock(MoneyTransfer.class);
		given(moneyTransfer.isCommitted()).willReturn(true);

		// when
		order.setPaymentMethod(paymentMethod);
		order.pay(moneyTransfer);

		// then
		assertTrue(order.isPaid());
	}

	@Test
	public void testIsPaidWithoutPaying() throws Exception {
		// given
		Order order = getOrderWithMockedOrderEntries();

		// when

		// then
		assertFalse(order.isPaid());
	}
}
