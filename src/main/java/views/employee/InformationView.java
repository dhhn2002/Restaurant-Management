package views.employee;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationView extends javax.swing.JPanel {

    /**
     * Creates new form menuView
     */
    public InformationView() {
        initComponents();

        btnHistoryLogin.putClientProperty("JButton.buttonType", "roundRect");
        btnChangePass.putClientProperty("JButton.buttonType", "roundRect");

    }

    public JPanel getPanelCalendar() {
        return panelCalendar;
    }

    public JButton getBtnHistoryLogin() {
        return btnHistoryLogin;
    }

    public JButton getBtnChangePass() {
        return btnChangePass;
    }

    public JLabel getLbTimeWorking() {
        return lbTimeWorking;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        btnChangePass = new javax.swing.JButton();
        btnHistoryLogin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelCalendar = new javax.swing.JPanel();
        lbTimeWorking = new javax.swing.JLabel();

        setBackground(new java.awt.Color(225, 203, 138));
        setMinimumSize(new java.awt.Dimension(1008, 680));
        setPreferredSize(new java.awt.Dimension(1008, 680));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(251, 133, 95));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
        jPanel1.setPreferredSize(new java.awt.Dimension(170, 680));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnChangePass.setText("Đổi mật khẩu");
        btnChangePass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(btnChangePass, gridBagConstraints);

        btnHistoryLogin.setText("Lịch sử đăng nhập");
        btnHistoryLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(btnHistoryLogin, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.EAST);

        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thời gian làm việc:");

        panelCalendar.setPreferredSize(new java.awt.Dimension(480, 578));

        javax.swing.GroupLayout panelCalendarLayout = new javax.swing.GroupLayout(panelCalendar);
        panelCalendar.setLayout(panelCalendarLayout);
        panelCalendarLayout.setHorizontalGroup(
            panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 726, Short.MAX_VALUE)
        );
        panelCalendarLayout.setVerticalGroup(
            panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        lbTimeWorking.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTimeWorking.setForeground(new java.awt.Color(255, 0, 0));
        lbTimeWorking.setText("00:00:00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbTimeWorking)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbTimeWorking))
                .addGap(28, 28, 28)
                .addComponent(panelCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnHistoryLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbTimeWorking;
    private javax.swing.JPanel panelCalendar;
    // End of variables declaration//GEN-END:variables
}