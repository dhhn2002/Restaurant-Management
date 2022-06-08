package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.ShipmentStatus;

public class Shipment extends Model {

    private int idOrder, idCustomer, idShipper;
    private String shipperName, shipperPhoneNumber, notice;
    private int shipCost;
    private ShipmentStatus status;
    private Timestamp startDate, endDate;
    private Order order;
    private Customer customer;
    private Employee shipper;
    
    public Shipment() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(int idShipper) {
        this.idShipper = idShipper;
    }
    
    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        if (order != null) {
            this.idOrder = order.getId();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            this.idCustomer = customer.getId();
        }
    }

    public int getShipCost() {
        return shipCost;
    }

    public void setShipCost(int shipCost) {
        this.shipCost = shipCost;
    }

    public Employee getShipper() {
        return shipper;
    }

    public void setShipper(Employee shipper) {
        this.shipper = shipper;
        if (shipper != null) {
            this.idShipper = shipper.getId();
        }
    }
    
    public static Shipment getFromResultSet(ResultSet rs) throws SQLException {
        Shipment shipment = new Shipment();
        shipment.setIdOrder(rs.getInt("idOrder"));
        shipment.setIdCustomer(rs.getInt("idCustomer"));
        shipment.setIdShipper(rs.getInt("idShipper"));
        shipment.setShipCost(rs.getInt("shipCost"));
        shipment.setStatus(ShipmentStatus.getById(rs.getNString("status")));
        shipment.setNotice(rs.getNString("notice"));
        shipment.setStartDate(rs.getTimestamp("startDate"));
        shipment.setEndDate(rs.getTimestamp("endDate"));
        return shipment;
    }

    @Override
    public String toString() {
        return "Shipment{" + "idOrder=" + idOrder + ", idCustomer=" + idCustomer
                + "idShipper=" + idShipper +shipperPhoneNumber + ", notice=" + notice + ", shipCost=" 
                + shipCost + ", status=" + status + ", startDate=" + startDate 
                + ", endDate=" + endDate + ", order=" + order + ", customer=" 
                + customer + '}';
    }
    
    @Override
    public Object[] toRowTable() {
        return new Object[]{
            idOrder, customer.getName(), customer.getAddress(), shipper.getName(),
            shipper.getPhoneNumber(), shipCost, status.getName(), startDate, endDate
        };
    }

}
