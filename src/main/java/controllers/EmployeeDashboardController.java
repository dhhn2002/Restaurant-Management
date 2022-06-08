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
        MenuItem menuQLHH = new MenuItem("QLHH", im.getIcon("cardboard_box_25px.png"), "Quản lý hàng hóa");
        MenuItem menuQLDH = new MenuItem("QLDH", im.getIcon("shopping_cart_25px.png"), "Quản lý đặt hàng");
        MenuItem menuTL = new MenuItem("TL", im.getIcon("settings_25px.png"), "Thiết lập");
        menuQLHH.addSubMenu(new MenuItem("QLLM", null, "Quản lý loại món"));
        menuQLHH.addSubMenu(new MenuItem("QLMA", im.getIcon("food_25px.png"), "Quản lý món ăn"));
        menuQLDH.addSubMenu(new MenuItem("QLB", im.getIcon("table_25px.png"), "Quản lý bàn"));
        menuQLDH.addSubMenu(new MenuItem("QLKH", im.getIcon("technical_support_25px.png"), "Quản lý khách hàng"));
        menuQLDH.addSubMenu(new MenuItem("QLDDH", im.getIcon("purchase_order_25px.png"), "Quản lý đơn đặt hàng"));
        menuQLDH.addSubMenu(new MenuItem("QLGH", im.getIcon("truck_25px.png"), "Quản lý giao hàng"));
        menuTL.addSubMenu(new MenuItem("TTCN", im.getIcon("about_25px.png"), "Thông tin cá nhân"));
        menuTL.addSubMenu(new MenuItem("TLGD", im.getIcon("contrast_25px.png"), "Giao diện"));
        menuTL.addSubMenu(new MenuItem("TT", im.getIcon("help_25px.png"), "About us"));
        sideBarController.addMenu(menuQLHH, menuQLDH, menuTL);
        sideBarController.addMenuEvent(this::onMenuChange);
    }

    private void addEvent() {
        view.getBtnLogout().addActionListener(evt -> {
            int confirm = JOptionPane.showConfirmDialog(view, "Bạn muốn đăng xuất?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                SessionManager.update();// Đăng xuất
            } catch (SQLException ex) {
                view.showError(ex);
            }
            view.dispose();
            new LoginController(new LoginView());
        });
    }

    public void onMenuChange(MenuItem item) {
        switch (item.getId()) {
            case "QLDDH"://Đơn đặt hàng
                view.setPanel(orderManagerView);
                orderManagerController.setView(orderManagerView);
                orderManagerController.updateData();
                break;
            case "QLB"://Quản lý bàn
                view.setPanel(tableManagerView);
                tableManagerController.setView(tableManagerView);
                tableManagerController.updateData();
                break;
            case "QLKH"://Quản lý khách hàng
                view.setPanel(customerManagerView);
                customerManagerController.setView(customerManagerView);
                customerManagerController.updateData();
                break;
            case "QLLM"://Quản lý loại món
                view.setPanel(foodCategoryManagerView);
                foodCategoryManagerController.setView(foodCategoryManagerView);
                foodCategoryManagerController.updateData();
                break;
            case "QLMA"://Quản lý món ăn
                view.setPanel(foodItemManagerView);
                foodItemManagerController.setView(foodItemManagerView);
                foodItemManagerController.updateData();
                break;
            case "QLGH"://Quản lý giao hàng
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
            case "TTCN": // Thống tin cá nhân
                view.setPanel(informationView);
                informationController.setView(informationView);
                break;
            default:
                view.setPanel(homeView);
                break;
        }
    }
}
