/**
 * TXTSaver
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

package utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Manage a {@link TXTSaver}
 * @author Sh1fT
 */
public class TXTSaver {
    private String filename;
    private List<String> infos;

    /**
     * Create a new {@link TXTSaver} instance
     * @param filename
     * @param infos 
     */
    public TXTSaver(String filename, List<String> infos) {
        this.setFilename(filename);
        this.setInfos(infos);
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String> getInfos() {
        return this.infos;
    }

    public void setInfos(List<String> infos) {
        this.infos = infos;
    }

    /**
     * Save the file
     */
    public void saveFile(List<String> infos) {
        try {
            FileOutputStream fos = new FileOutputStream(this.getFilename()); 
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            for (String info : infos) {
                bw.write(info);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            System.out.println("Aïe Aïe Aïe !\n" + ex.getLocalizedMessage());
            System.exit(1);
        }
    }
}