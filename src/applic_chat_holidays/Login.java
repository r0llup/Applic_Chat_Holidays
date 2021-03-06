/**
 * Login
 *
 * Copyright (C) 2012 Sh1fT
 *
 * This file is part of Applic_Chat_Holidays.
 *
 * Applic_Chat_Holidays is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * Applic_Chat_Holidays is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Applic_Chat_Holidays; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package applic_chat_holidays;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import utils.TXTLoader;
import utils.TXTSaver;

/**
 * Manage a {@link Login}
 * @author Sh1fT
 */
public class Login extends JDialog implements KeyListener {
    private Applic_Chat_Holidays parent;
    private Boolean logged;

    /**
     * Create a new {@link Login} instance
     * @param parent
     * @param modal 
     */
    public Login(Applic_Chat_Holidays parent, Boolean modal) {
        super(parent, modal);
        this.initComponents();
        this.setParent(parent);
        this.setLogged(false);
        this.usernameTextField.addKeyListener(this);
        this.passwordPasswordField.addKeyListener(this);
    }

    public Applic_Chat_Holidays getParent() {
        return this.parent;
    }

    public void setParent(Applic_Chat_Holidays parent) {
        this.parent = parent;
    }

    public Boolean getLogged() {
        return this.logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getUsername() {
        return this.usernameTextField.getText();
    }

    public String getPassword() {
        return String.valueOf(this.passwordPasswordField.getPassword());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwordPasswordField = new javax.swing.JPasswordField();
        souvenirCheckBox = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Applic_Chat-Holidays - Login");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Merci d'entrer vos informations d'accès", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 10), java.awt.Color.darkGray)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel1.setText("Nom d'utilisateur :");

        usernameTextField.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        usernameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel2.setText("Mot de passe :");

        passwordPasswordField.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        passwordPasswordField.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        souvenirCheckBox.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        souvenirCheckBox.setSelected(true);
        souvenirCheckBox.setText("Se souvenir de moi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                    .addComponent(souvenirCheckBox))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(souvenirCheckBox)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        okButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        okButton.setText("Ok");
        okButton.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(okButton)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(okButton)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (this.getUsername().compareTo("") != 0) {
            try {
                String user = this.getUsername().replace(";", "_");
                if (!this.getParent().getNick(user)) {
                    Socket socket = new Socket(this.getParent().getServerHost(),
                            this.getParent().getServerPort());
                    DataInputStream dis = new DataInputStream(
                            new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream dos = new DataOutputStream(
                            new BufferedOutputStream(socket.getOutputStream()));
                    String cmd = "LOGIN";
                    String pass = String.valueOf(this.getPassword().replace(";", "_"));
                    dos.write((cmd + ";" + user + ";" + pass + "\n").getBytes());
                    dos.flush();
                    StringBuilder info = new StringBuilder();
                    info.setLength(0);
                    byte b = 0;
                    while ((b=dis.readByte()) != (byte) '\n') {
                        if (b != '\n')
                            info.append((char) b);
                    }
                    if (info.toString().compareTo("ko") != 0) {
                        this.setLogged(true);
                        StringTokenizer st  = new StringTokenizer(info.toString(), ":");
                        this.getParent().setGroupHost(st.nextToken());
                        this.getParent().setGroupPort(Integer.parseInt(st.nextToken()));
                        if (this.souvenirCheckBox.isSelected()) {
                            List<String> infos = new LinkedList<>();
                            infos.add(this.usernameTextField.getText());
                            infos.add(String.valueOf(this.passwordPasswordField.getPassword()));
                            TXTSaver ts = new TXTSaver(this.getParent().getSaveFilename(), infos);
                            ts.saveFile(infos);
                        }
                        this.setVisible(false);
                    } else
                        JOptionPane.showMessageDialog(this, "Informations d'accès invalides.",
                                "Oups !", JOptionPane.WARNING_MESSAGE);
                    dos.close();
                    dis.close();
                    socket.close();
                } else
                    JOptionPane.showMessageDialog(this, "Vous êtes déjà connecté.",
                            "Oups !", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalStateException | IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                        "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Merci de spécifier votre nom d'utilisateur.",
                    "Oups !", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_okButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            TXTLoader tl = new TXTLoader(this.getParent().getSaveFilename());
            List<String> infos = tl.retrieveData();
            this.usernameTextField.setText(infos.get(0));
            this.passwordPasswordField.setText(infos.get(1));
        } catch (FileNotFoundException ex) {
            // aucune informations à récupérer
        }
    }//GEN-LAST:event_formWindowOpened

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            this.okButtonActionPerformed(null);
     }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
            System.exit(1);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Login dialog = new Login(new Applic_Chat_Holidays(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField passwordPasswordField;
    private javax.swing.JCheckBox souvenirCheckBox;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}