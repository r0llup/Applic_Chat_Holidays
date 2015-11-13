/**
 * TXTLoader
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Manage a {@link TXTLoader}
 * @author Sh1fT
 */
public class TXTLoader {
    private String filename;
    private BufferedReader bufferedReader;

    /**
     * Create a new {@link TXTLoader} instance
     * @param filename
     * @throws FileNotFoundException 
     */
    public TXTLoader(String filename) throws FileNotFoundException {
        this.setFilename(filename);
        this.setBufferedReader(null);
        this.loadFile();
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    /**
     * Load the file
     * @throws FileNotFoundException 
     */
    public void loadFile() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(this.getFilename()); 
        InputStreamReader isr = new InputStreamReader(fis);
        this.setBufferedReader(new BufferedReader(isr));
    }

    /**
     * Retrieve the date
     * @return 
     */
    public List<String> retrieveData() {
        try {
            List<String> listeString = new LinkedList<>();
            String ligne;
            while ((ligne = this.getBufferedReader().readLine()) != null)
                listeString.add(ligne);
            return listeString;
        } catch (IOException ex) {
            System.out.println("Aïe Aïe Aïe !\n" + ex.getLocalizedMessage());
            System.exit(1);
        }
        return null;
    }

    @Override
    public void finalize() {
        try {
            this.getBufferedReader().close();
            super.finalize();
        } catch (Throwable ex) {
            System.out.println("Aïe Aïe Aïe !\n" + ex.getLocalizedMessage());
            System.exit(1);
        }
    }
}