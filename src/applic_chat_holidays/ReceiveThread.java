/**
 * ReceiveThread
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

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 * Manage a {@link ReceiveThread}
 * @author Sh1fT
 */
public class ReceiveThread extends Thread {
    private Applic_Chat_Holidays parent;
    private Boolean enabled;

    /**
     * Create a new {@link ReceiveThread} instance
     * @param parent 
     */
    public ReceiveThread(Applic_Chat_Holidays parent) {
        this.setParent(parent);
        this.setEnabled(false);
    }

    public Applic_Chat_Holidays getParent() {
        return this.parent;
    }

    public void setParent(Applic_Chat_Holidays parent) {
        this.parent = parent;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void run() {
        this.setEnabled(true);
        while (this.getEnabled()) {
            try {
                byte[] buf = new byte[1024];
                DatagramPacket dtg = new DatagramPacket(buf, buf.length);
                this.getParent().getGroupSocket().receive(dtg);
                String info = new String(buf).trim();
                StringTokenizer st = new StringTokenizer(info, "#");
                String nick = st.nextToken();
                if (info.contains("#JOINGRP#")) {
                    if (!this.getParent().getNickListModel().contains(nick)) {
                        if (this.getParent().getLogin().getUsername().compareTo(nick) != 0) {
                            this.getParent().getNickListModel().addElement(nick);
                            this.getParent().getReceiveTextArea().append(nick + " a rejoint la conversation.\n");
                        }
                    }
                } else if (info.contains("#LEAVEGRP#")) {
                    this.getParent().getNickListModel().removeElement(nick);
                    this.getParent().getReceiveTextArea().append(nick + " a quitté la conversation.\n");
                } else
                    this.getParent().getReceiveTextArea().append(info + "\n");
            } catch (IOException ex ) {
                this.setEnabled(false);
                JOptionPane.showMessageDialog(this.getParent(), ex.getLocalizedMessage(),
                        "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    }
}