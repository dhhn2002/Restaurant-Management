package controllers.popup;

import dao.EmployeeDao;
import java.sql.SQLException;
import javax.swing.JFrame;
import models.Employee;
import views.popup.SelectCustomerPopupView;
import views.popup.SelectShipperPopupView;

public class SelectShipperPopupController {

    EmployeeDao employeeDao = new EmployeeDao();
    JFrame previousView;

    public interface Callback {

        public abstract void run(Employee employee);
    }

    public void select(SelectShipperPopupView view, Callback callback) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        try {
            view.renderEmployee(employeeDao.getAll());
        } catch (SQLException e) {
            view.showError(e);
        }
        view.getBtnOK().addActionListener(evt -> {
            Employee c = view.getListEmployee().getSelectedValue();
            if (c == null) {
                view.showError("Bạn chưa chọn shipper");
                return;
            }
            callback.run(c);
            view.dispose();
        });
        view.getBtnSearch().addActionListener(evt -> {
            String txtSearch = view.getTxtEmployeeName().getText();
            try {
                view.renderEmployee(employeeDao.searchByKey("name", txtSearch));
            } catch (Exception e) {
                view.showError(e);
            }
        });
        view.getBtnCancel().addActionListener(evt -> {
            view.dispose();
        });
    }
}
