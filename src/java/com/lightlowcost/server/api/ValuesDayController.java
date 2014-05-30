/*
* Project: LightLowCost
* Package: com.lightlowcost.server.api
* File: ValuesDayController.java
* Date: 30-may-2014
* Encoding: UTF-8
* License: default
*
* Copyright(c) Jocami 2014
* www.jocami.com
* admin@jocami.com
*
* This file is part of Jocami.
* Jocami is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* any later version.
*
* Jocami is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Jocami. If not, see <http://www.gnu.org/licenses/>.
*/

package com.lightlowcost.server.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @author JOCAMI
* @version 1.0
* @since 1.0
*/
@Controller
public class ValuesDayController {
    @RequestMapping(value = {"/downloadxls/{fecha}"}, method = RequestMethod.GET)
    public void valueLight(HttpServletRequest request, HttpServletResponse response, @PathVariable("fecha") String fechaStr) {
        String excelFile;
        try {
            excelFile = downloadExcel(fechaStr);
            //readExcel(excelFile);
            
        } catch (IOException ex) {
            Logger.getLogger(ValuesDayController.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8;");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
        }
    
    }
    
    private String downloadExcel(String fechaStr) throws MalformedURLException, IOException{
        URL url;
        String dir = "C:/Users/Carlos/Desktop/Pruebas/"+fechaStr+".xls";

			// get URL content
			url = new URL("http://www.esios.ree.es/Solicitar?fileName=PVPC_DETALLE_DD_"+fechaStr+ "&fileType=xls&idioma=es");
			URLConnection conn = url.openConnection();
                        InputStream is = conn.getInputStream();

                        // Fichero en el que queremos guardar el contenido
                        
                        File salida = new File(dir);
                        FileOutputStream fos;
    
                        fos = new FileOutputStream(salida);
    
                        // buffer para ir leyendo.
                        byte [] array = new byte[1000];

                        // Primera lectura y bucle hasta el final
                        int leido = is.read(array);
                        while (leido > 0) {
                           fos.write(array,0,leido);
                           leido=is.read(array);
                        }

                        // Cierre de conexion y fichero.
                        is.close();
                        fos.close();
                        return dir;                
                }
    @RequestMapping(value = {"/readexcel"}, method = RequestMethod.GET)
    public void readExcel(){
        String dir = "C:/Users/Carlos/Desktop/Pruebas/20140525.xls";
        try {
            Workbook workbook = Workbook.getWorkbook(new File(dir));
            Sheet sheet = workbook.getSheet(0); 
            
            Cell a1 = sheet.getCell(4,5);
            Cell b2 = sheet.getCell(4,6);
            Cell c2 = sheet.getCell(0,5);
            String stringa1 = a1.getContents();
            String string2 = b2.getContents();
            String string3 = c2.getContents();
            System.out.println(stringa1);
            System.out.println(string2);
            System.out.println(string3);
            workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ValuesDayController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(ValuesDayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
}

