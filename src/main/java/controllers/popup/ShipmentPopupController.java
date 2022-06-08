package controllers.popup;

import dao.CustomerDao;
import dao.EmployeeDao;
import dao.ShipmentDao;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JFrame;
import models.Employee;
import models.Shipment;
import utils.EmployeePermission;
import utils.ShipmentStatus;
import views.popup.SelectCustomerPopupView;
import views.popup.SelectShipperPopupView;
import views.popup.ShipmentPopupView;

public class ShipmentPopupController {
    
    EmployeeDao employeeDao = new EmployeeDao();
    ShipmentDao shipmentDao = new ShipmentDao();
    CustomerDao customerDao = new CustomerDao();
    JFrame previousView;

    public void add(ShipmentPopupView view, int idOrder, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        
        try {
            Shipment shipment = shipmentDao.get(idOrder);
            if (shipment != null) {
                edit(view, idOrder, sc, ec);
                return;
            }
            shipment = new Shipment();
            shipment.setShipCost(0);
            shipment.setShipper(employeeDao.getAll().get(0));
            shipment.setIdOrder(idOrder);
            shipment.setCustomer(customerDao.getAll().get(0));
            shipment.setStatus(ShipmentStatus.TORECIEVE);
            shipment.setStartDate(new Timestamp(System.currentTimeMillis()));
            shipmentDao.save(shipment);
            edit(view, idOrder, sc, ec);
        } catch (Exception e) {
            ec.onError(e);
            view.dispose();
        }
    }

    public void edit(ShipmentPopupView view, int idOrder, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            if (view != previousView) {

            }
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        
        for (ShipmentStatus value : ShipmentStatus.values()) {
            view.getCboStatus().addItem(value.getName());
        }
        
        try {
            Shipment shipment = shipmentDao.get(idOrder);
            if (shipment.getCustomer() != null) {
                view.getLbCustomerName().setText(shipment.getCustomer().getName());
            } else {
                view.getLbCustomerName().setText("<Chưa chọn>");
            }

            if (shipment.getShipper() != null && shipment.getShipper().getPermission()==EmployeePermission.SHIPPER) {
                view.getLbShipperName().setText(shipment.getShipper().getName());
            } else {
                view.getLbShipperName().setText("<Chưa chọn>");
            }
            
            view.getCboStatus().setSelectedItem(shipment.getStatus().getName());
            view.getSpnShipCost().setValue(shipment.getShipCost());
            //customer
            view.getBtnSelectCustomer().addActionListener(evt -> {
                SelectCustomerPopupController selectCustomerPopupController = new SelectCustomerPopupController();
                selectCustomerPopupController.select(new SelectCustomerPopupView(), (customer) -> {
                    shipment.setCustomer(customer);
                    view.getLbCustomerName().setText(customer.getName());
                });
            });
            //shipper
            view.getBtnSelectShipper().addActionListener(evt -> {
                SelectShipperPopupController selectShipperPopupController2 = new SelectShipperPopupController();
                selectShipperPopupController2.select(new SelectShipperPopupView(), (employee) -> {
                    try {
//                        for (Employee employee : employeeDao.getAll()) {
                            if (employee.getPermission()== EmployeePermission.SHIPPER) {
                                shipment.setShipper(employee);
                                view.getLbShipperName().setText(employee.getName());
                            }
//                        }
                    }catch (Exception e) {
                        view.dispose();
                        ec.onError(e);
                        return;
                    } 
                });
            });
            
            view.getBtnOK().addActionListener(evt -> {
                try {
                    editShipment(view, shipment);
                    view.dispose();
                    view.showMessage("Tạo / sửa đơn ship thành công!");
                    sc.onSuccess();
                } catch (SQLException e) {
                    ec.onError(e);
                }
            });
            
        } catch (Exception e) {
            ec.onError(e);
            view.dispose();
        }

    }
    
//    public void addShipment(ShipmentPopupView view,)

    public void editShipment(ShipmentPopupView view, Shipment shipment) throws SQLException {
        shipment.setStatus(ShipmentStatus.getByName(view.getCboStatus().getSelectedItem().toString()));
        shipment.setShipCost((int) view.getSpnShipCost().getValue());
        if (shipment.getStatus() == ShipmentStatus.COMPLETED) {
            shipment.setEndDate(new Timestamp(System.currentTimeMillis()));
        } else {
            shipment.setEndDate(null);
        }
        shipmentDao.update(shipment);
    }
}
