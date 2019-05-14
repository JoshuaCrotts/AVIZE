/* 
 * Copyright 2019 Nancy Green
 * This file is part of AVIZE.
 *
 * AVIZE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AVIZE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AVIZE.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.uncg.save;

import com.uncg.save.controllers.SchemePaneController;
import com.uncg.save.models.SchemeModel;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * @author Joshua Crotts
 */
public class SaveArgScheme {

    private SchemeModel schemeModel;
    private String filePath;

    public SaveArgScheme(String path) {
        this.filePath = path;
        
        this.writeArgToFile(schemeModel);
    }

    private boolean writeArgToFile(Object serObj) {
        boolean success = false;
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object was succesfully written to a file");
            success = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return success;
    }

}
