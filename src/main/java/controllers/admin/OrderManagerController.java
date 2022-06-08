package controllers.admin;

import controllers.ManagerController;
import controllers.popup.OrderPopupController;
import dao.OrderDao;
import dao.ShipmentDao;
import dao.TableDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import main.SessionManager;
import models.Employee;
import models.Order;
import models.Table;
import utils.EmployeePermission;
import utils.TableStatus;
import views.admin.EmployeeManagerView;
import views.popup.AddOrderPopupView;
import views.popup.EditOrderPopupView;

public class OrderManagerController extends ManagerController {

    OrderDao orderDao = new OrderDao();
    TableDao tableDao = new TableDao();
    ShipmentDao shipmentDao = new ShipmentDao();
    OrderPopupController popupController = new OrderPopupController();
    Employee session = SessionManager.getSession().getEmployee();

    public OrderManagerController() {
        super();
    }

    public void setView(EmployeeManagerView view) {
        super.setView(view);
    }

    @Override
    public void actionAdd() {
//        popupController.add(this, new AddOrderPopupView());
        popupController.add(new AddOrderPopupView(), this::updateData, view::showError);
    }

    @Override
    public void actionEdit() {
        try {
            int selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn hóa đơn cần edit");
            }
            Order order = orderDao.get(selectedId);
            if (order == null) {
                throw new Exception("Hóa đơn bạn chọn không hợp lệ");
            }
//            popupController.edit(this, new EditOrderPopupView(), order);
            popupController.edit(new EditOrderPopupView(), order, this::updateData, view::showError);

        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionDelete() {
        int selectedIds[] = view.getSelectedIds();
        try {
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa?", "Xóa", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedIds.length; i++) {
                int id = selectedIds[i];
                Order o = orderDao.get(id);
                Table t = o.getTable();
                t.setStatus(TableStatus.FREE);
                shipmentDao.deleteById(id); //Xóa shipment order
                orderDao.deleteItems(id); // Xóa item trong order 
                tableDao.update(t); // Trả bàn
                orderDao.deleteById(id); // Xóa order
            }
            updateData();
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            Employee employee = SessionManager.getSession().getEmployee();
            ArrayList<Order> orders;
            if (employee.getPermission() == EmployeePermission.MANAGER) {
                orders = orderDao.getAll();
            } else {
//                orders = orderDao.getAll(employee.getId());
                orders = orderDao.getAll();
            }
            view.setTableData(orders);
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionSearch() {
        try {
            Employee employee = SessionManager.getSession().getEmployee();
            ArrayList<Order> orders;
            String searchField = view.getCboSearchField().getSelectedItem().toString(),
                    txtSearch = view.getTxtSearch().getText();
            if (employee.getPermission() == EmployeePermission.MANAGER) {
                orders = orderDao.searchByKey(searchField, txtSearch);
            } else {
//              orders = orderDao.searchByKey(employee.getId(), searchField, txtSearch);
                orders = orderDao.searchByKey(searchField, txtSearch);

            }
            view.setTableData(orders);
        } catch (Exception e) {
            view.showError(e);
        }
    }

}
