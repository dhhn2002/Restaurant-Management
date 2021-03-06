package controllers;

import controllers.employee.InformationController;
import controllers.admin.CustomerManagerController;
import controllers.admin.EmployeeManagerController;
import controllers.admin.FoodCategoryManagerController;
import controllers.admin.FoodItemManagerController;
import controllers.admin.OrderManagerController;
import controllers.admin.ShipmentManagerController;
import controllers.admin.TableManagerController;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.SessionManager;
import models.Employee;
import utils.IconManager;
import views.EmployeeDashboardView;
import views.LoginView;
import views.admin.AboutView;
import views.admin.CustomerManagerView;
import views.admin.EmployeeManagerView;
import views.admin.FoodCategoryManagerView;
import views.admin.FoodItemManagerView;
import views.admin.HomeView;
import views.admin.ManagerPaneView;
import views.admin.MenuItem;
import views.admin.OrderManagerView;
import views.admin.ShipmentManagerView;
import views.admin.TableManagerView;
import views.employee.InformationView;

public class EmployeeDashboardController {

    private EmployeeDashboardView view;
      // Controller
    ManagerController tableManagerController = new TableManagerController(),
            foodCategoryManagerController = new FoodCategoryManagerController(),
            foodItemManagerController = new FoodItemManagerController(),
            orderManagerController = new OrderManagerController(),
            shipmentManagerController = new ShipmentManagerController(),
            customerManagerController = new CustomerManagerController();
    InformationController informationController = new InformationController();
    ManagerPaneView tableManagerView = new TableManagerView(),
            foodCategoryManagerView = new FoodCategoryManagerView(),
            foodItemManagerView = new FoodItemManagerView(),
            orderManagerView = new OrderManagerView(),
            shipmentManagerView = new ShipmentManagerView(),
            customerManagerView = new CustomerManagerView();
    HomeView homeView = new HomeView();
    AboutView aboutView = new AboutView();
    InformationView informationView = new InformationView();

    SideBarController sideBarController = new SideBarController();
    JPanel[] cards = {
        homeView, tableManagerView, customerManagerView,
        foodCategoryManagerView, orderManagerView, foodItemManagerView, shipmentManagerView,
        aboutView, informationView
    };

    public EmployeeDashboardController(EmployeeDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
        view.setVisible(true);
        initMenu();
        addEvent();
        Employee session = SessionManager.getSession().getEmployee();
        if (session != null) {
            view.getLbName().setText(session.getName());
        }
        view.setCards(cards);
        view.setPanel(homeView);
    }

    public EmployeeDashboardView getView() {
        return view;
    }

    public void setView(EmployeeDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
    }

    private void initMenu() {
        IconManager im = new IconManager();
        MenuItem menuQLHH = new MenuItem("QLHH", im.getIcon("cardboard_box_25px.png"), "Qu???n l?? h??ng h??a");
        MenuItem menuQLDH = new MenuItem("QLDH", im.getIcon("shopping_cart_25px.png"), "Qu???n l?? ?????t h??ng");
        MenuItem menuTL = new MenuItem("TL", im.getIcon("settings_25px.png"), "Thi???t l???p");
        menuQLHH.addSubMenu(new MenuItem("QLLM", null, "Qu???n l?? lo???i m??n"));
        menuQLHH.addSubMenu(new MenuItem("QLMA", im.getIcon("food_25px.png"), "Qu???n l?? m??n ??n"));
        menuQLDH.addSubMenu(new MenuItem("QLB", im.getIcon("table_25px.png"), "Qu???n l?? b??n"));
        menuQLDH.addSubMenu(new MenuItem("QLKH", im.getIcon("technical_support_25px.png"), "Qu???n l?? kh??ch h??ng"));
        menuQLDH.addSubMenu(new MenuItem("QLDDH", im.getIcon("purchase_order_25px.png"), "Qu???n l?? ????n ?????t h??ng"));
        menuQLDH.addSubMenu(new MenuItem("QLGH", im.getIcon("truck_25px.png"), "Qu???n l?? giao h??ng"));
        menuTL.addSubMenu(new MenuItem("TTCN", im.getIcon("about_25px.png"), "Th??ng tin c?? nh??n"));
        menuTL.addSubMenu(new MenuItem("TLGD", im.getIcon("contrast_25px.png"), "Giao di???n"));
        menuTL.addSubMenu(new MenuItem("TT", im.getIcon("help_25px.png"), "About us"));
        sideBarController.addMenu(menuQLHH, menuQLDH, menuTL);
        sideBarController.addMenuEvent(this::onMenuChange);
    }

    private void addEvent() {
        view.getBtnLogout().addActionListener(evt -> {
            int confirm = JOptionPane.showConfirmDialog(view, "B???n mu???n ????ng xu???t?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                SessionManager.update();// ????ng xu???t
            } catch (SQLException ex) {
                view.showError(ex);
            }
            view.dispose();
            new LoginController(new LoginView());
        });
    }

    public void onMenuChange(MenuItem item) {
        switch (item.getId()) {
            case "QLDDH"://????n ?????t h??ng
                view.setPanel(orderManagerView);
                orderManagerController.setView(orderManagerView);
                orderManagerController.updateData();
                break;
            case "QLB"://Qu???n l?? b??n
                view.setPanel(tableManagerView);
                tableManagerController.setView(tableManagerView);
                tableManagerController.updateData();
                break;
            case "QLKH"://Qu???n l?? kh??ch h??ng
                view.setPanel(customerManagerView);
                customerManagerController.setView(customerManagerView);
                customerManagerController.updateData();
                break;
            case "QLLM"://Qu???n l?? lo???i m??n
                view.setPanel(foodCategoryManagerView);
                foodCategoryManagerController.setView(foodCategoryManagerView);
                foodCategoryManagerController.updateData();
                break;
            case "QLMA"://Qu???n l?? m??n ??n
                view.setPanel(foodItemManagerView);
                foodItemManagerController.setView(foodItemManagerView);
                foodItemManagerController.updateData();
                break;
            case "QLGH"://Qu???n l?? giao h??ng
                view.setPanel(shipmentManagerView);
                shipmentManagerController.setView(shipmentManagerView);
                shipmentManagerController.updateData();
                break;
            case "QLHH":
            case "QLDH":
            case "TL":
                break;
            case "TT":
                view.setPanel(aboutView);
                break;
            case "TTCN": // Th???ng tin c?? nh??n
                view.setPanel(informationView);
                informationController.setView(informationView);
                break;
            default:
                view.setPanel(homeView);
                break;
        }
    }
}
