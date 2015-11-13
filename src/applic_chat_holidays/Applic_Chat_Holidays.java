/**
 * Applic_Chat_Holidays
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import utils.PropertiesLauncher;

/**
 * Manage an {@link Applic_Chat_Holidays}
 * @author Sh1fT
 */
public class Applic_Chat_Holidays extends JFrame implements KeyListener, MouseListener {
    private PropertiesLauncher propertiesLauncher;
    private Login login;
    private String groupHost;
    private Integer groupPort;
    private InetAddress groupAddress;
    private MulticastSocket groupSocket;
    private ReceiveThread receiveThread;
    private DefaultListModel nickListModel;

    /**
     * Create a new {@link Applic_Chat_Holidays} instance
     */
    public Applic_Chat_Holidays() {
        this.initComponents();
        this.setPropertiesLauncher(new PropertiesLauncher(
                System.getProperty("file.separator") + "properties" +
                System.getProperty("file.separator") + "Applic_Chat_Holidays.properties"));
        this.setLogin(new Login(this, true));
        this.setGroupHost(null);
        this.setGroupPort(null);
        this.setGroupAddress(null);
        this.setGroupSocket(null);
        this.setReceiveThread(new ReceiveThread(this));
        this.setNickListModel(new DefaultListModel());
        this.nickList.setModel(this.getNickListModel());
        this.nickList.addMouseListener(this);
        this.sendTextArea.addKeyListener(this);
    }

    public PropertiesLauncher getPropertiesLauncher() {
        return this.propertiesLauncher;
    }

    public void setPropertiesLauncher(PropertiesLauncher propertiesLauncher) {
        this.propertiesLauncher = propertiesLauncher;
    }

    public Login getLogin() {
        return this.login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getGroupHost() {
        return this.groupHost;
    }

    public void setGroupHost(String groupHost) {
        this.groupHost = groupHost;
    }

    public Integer getGroupPort() {
        return this.groupPort;
    }

    public void setGroupPort(Integer groupPort) {
        this.groupPort = groupPort;
    }

    public InetAddress getGroupAddress() {
        return this.groupAddress;
    }

    public void setGroupAddress(InetAddress groupAddress) {
        this.groupAddress = groupAddress;
    }

    public MulticastSocket getGroupSocket() {
        return this.groupSocket;
    }

    public void setGroupSocket(MulticastSocket groupSocket) {
        this.groupSocket = groupSocket;
    }

    public ReceiveThread getReceiveThread() {
        return this.receiveThread;
    }

    public void setReceiveThread(ReceiveThread receiveThread) {
        this.receiveThread = receiveThread;
    }

    public DefaultListModel getNickListModel() {
        return this.nickListModel;
    }

    public void setNickListModel(DefaultListModel nickListModel) {
        this.nickListModel = nickListModel;
    }

    public Properties getProperties() {
        return this.getPropertiesLauncher().getProperties();
    }

    public String getServerHost() {
        return this.getProperties().getProperty("serverHost");
    }

    public Integer getServerPort() {
        return Integer.parseInt(this.getProperties().getProperty("serverPort"));
    }

    public String getSaveFilename() {
        return this.getProperties().getProperty("saveFilename");
    }

    public JTextArea getReceiveTextArea() {
        return this.receiveTextArea;
    }

    public JTextArea getSendTextArea() {
        return this.sendTextArea;
    }

    /**
     * Get the nicks
     */
    public void getNicks() {
        try {
            Socket socket = new Socket(this.getServerHost(), this.getServerPort());
            DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));
            String cmd = "GETNICKS";
            dos.write((cmd + "\n").getBytes());
            dos.flush();
            StringBuilder info = new StringBuilder();
            info.setLength(0);
            byte b = 0;
            while ((b=dis.readByte()) != (byte) '\n') {
                if (b != '\n')
                    info.append((char) b);
            }
            List<String> nicks = Arrays.asList(info.toString().split(":"));
            if (nicks != null) {
                for (String nick : nicks) {
                    if (this.getLogin().getUsername().compareTo(nick) != 0)
                        this.getNickListModel().addElement(nick);
                }
            }
            dos.close();
            dis.close();
            socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                    "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Get the nick
     * @param nick
     * @return Boolean
     */
    public Boolean getNick(String nick) {
        try {
            Socket socket = new Socket(this.getServerHost(), this.getServerPort());
            DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));
            String cmd = "GETNICK";
            dos.write((cmd + ";" + nick + "\n").getBytes());
            dos.flush();
            StringBuilder info = new StringBuilder();
            info.setLength(0);
            byte b = 0;
            while ((b=dis.readByte()) != (byte) '\n') {
                if (b != '\n')
                    info.append((char) b);
            }
            dos.close();
            dis.close();
            socket.close();
            return Boolean.parseBoolean(info.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                    "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return null;
    }

    /**
     * Delete the nick
     * @param nick 
     */
    public void delNick(String nick) {
        try {
            Socket socket = new Socket(this.getServerHost(), this.getServerPort());
            DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));
            String cmd = "DELNICK";
            dos.write((cmd + ";" + nick + "\n").getBytes());
            dos.flush();
            dos.close();
            socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                    "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.isShiftDown()) {
            if ((e.getKeyCode() == KeyEvent.VK_ENTER))
                this.sendTextArea.append("\n");
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                this.sendTextAreaActionPerformed(null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            if (!this.getNickListModel().isEmpty()) {
                Integer index = this.nickList.locationToIndex(e.getPoint());
                String nick = this.getNickListModel().getElementAt(index).toString();
                this.nickList.ensureIndexIsVisible(index);
                this.sendTextArea.insert(nick, this.sendTextArea.getCaretPosition());
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        receiveTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        sendTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        nickList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Applic_Chat_Holidays");
        setPreferredSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setBorder(null);

        receiveTextArea.setEditable(false);
        receiveTextArea.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        receiveTextArea.setLineWrap(true);
        receiveTextArea.setRows(5);
        receiveTextArea.setWrapStyleWord(true);
        receiveTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        receiveTextArea.setDoubleBuffered(true);
        jScrollPane1.setViewportView(receiveTextArea);

        jScrollPane2.setBorder(null);

        sendTextArea.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        sendTextArea.setLineWrap(true);
        sendTextArea.setWrapStyleWord(true);
        sendTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jScrollPane2.setViewportView(sendTextArea);

        jScrollPane3.setBorder(null);

        nickList.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        nickList.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        nickList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(nickList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            this.getLogin().setVisible(true);
            if (this.getLogin().getLogged()) {
                this.setTitle("Applic_Chat_Holidays - " + this.getLogin().getUsername());
                this.getNicks();
                this.setGroupAddress(InetAddress.getByName(this.getGroupHost()));
                this.setGroupSocket(new MulticastSocket(this.getGroupPort()));
                this.getGroupSocket().joinGroup(this.getGroupAddress());
                this.getReceiveThread().start();
                String msg = this.getLogin().getUsername() + "#JOINGRP#";
                DatagramPacket dtg = new DatagramPacket(msg.getBytes(), msg.length(),
                    this.getGroupAddress(), this.getGroupPort());
                this.getGroupSocket().send(dtg);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                    "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            if (this.getLogin().getLogged()) {
                this.delNick(this.getLogin().getUsername());
                this.getReceiveThread().setEnabled(false);
                String msg = this.getLogin().getUsername() + "#LEAVEGRP#";
                DatagramPacket dtg = new DatagramPacket(msg.getBytes(), msg.length(),
                this.getGroupAddress(), this.getGroupPort());
                this.getGroupSocket().send(dtg);
                this.getGroupSocket().leaveGroup(this.getGroupAddress());
                this.getGroupSocket().close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                    "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }//GEN-LAST:event_formWindowClosing

    private void sendTextAreaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (this.getLogin().getLogged()) {
                if (this.sendTextArea.getText().trim().compareTo("") != 0) {
                    String msg = this.getLogin().getUsername() + "> " +
                        this.sendTextArea.getText();
                    DatagramPacket dtg = new DatagramPacket(msg.getBytes(), msg.length(),
                        this.getGroupAddress(), this.getGroupPort());
                    this.getGroupSocket().send(dtg);
                    this.sendTextArea.setText(null);
                    this.sendTextArea.setCaretPosition(0);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                    "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Applic_Chat_Holidays().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList nickList;
    private javax.swing.JTextArea receiveTextArea;
    private javax.swing.JTextArea sendTextArea;
    // End of variables declaration//GEN-END:variables
}