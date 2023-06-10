package pl.edu.agh.internetshop.order;

import pl.edu.agh.internetshop.*;

import java.math.BigDecimal;
import java.util.UUID;


public class Order {
    private static final BigDecimal TAX_VALUE = BigDecimal.valueOf(1.23);
	private final UUID id;
    private final Buyer buyer;
    private final OrderEntryAggregator orderEntries;
    private boolean paid;
    private Shipment shipment;
    private ShipmentMethod shipmentMethod;
    private PaymentMethod paymentMethod;

    public Order(OrderEntryAggregator orderEntries, Buyer buyer) {
        this.orderEntries = orderEntries;
        this.buyer = buyer;
        this.id = UUID.randomUUID();
        this.paid = false;
    }


    public UUID getId() {
        return id;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isSent() {
        return shipment != null && shipment.isShipped();
    }

    public boolean isPaid() { return paid; }

    public Shipment getShipment() {
        return shipment;
    }

    public BigDecimal getPrice() {
        return this.orderEntries.getPrice();
    }

    public BigDecimal getPriceWithTaxes() {
        return PriceUtilities.scaleBigDecimal(getPrice().multiply(TAX_VALUE));
    }

    public OrderEntryAggregator getOrderEntries() {
        return this.orderEntries;
    }

    public ShipmentMethod getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public void send() {
        boolean sentSuccessful = getShipmentMethod().send(shipment, shipment.getSenderAddress(), shipment.getRecipientAddress());
        shipment.setShipped(sentSuccessful);
    }

    public void pay(MoneyTransfer moneyTransfer) {
        moneyTransfer.setCommitted(getPaymentMethod().commit(moneyTransfer));
        paid = moneyTransfer.isCommitted();
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Buyer getBuyer() {
        return buyer;
    }
}
