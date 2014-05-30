/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lightlowcost.server.api;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jxl.*;
import jxl.read.biff.BiffException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Carlos
 */
@Controller
public class ValueLightController {
     @RequestMapping(value = {"/ValueLight"}, method = RequestMethod.GET)
    public void valueLight(HttpServletRequest request, HttpServletResponse response) {
         try {
             File fil = downloadExcel2();
             Workbook workbook = Workbook.getWorkbook(new File("C:/Users/Carlos/Desktop/Pruebas/cipote.xls"));
             Sheet sheet = workbook.getSheet(0);
             Cell a1 = sheet.getCell(0,0);
             Cell b2 = sheet.getCell(1,1);
             Cell c2 = sheet.getCell(2,1);
             String stringa1 = a1.getContents(); 
             System.out.println(stringa1);
         } catch (IOException ex) {
             Logger.getLogger(ValueLightController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (BiffException ex) {
             Logger.getLogger(ValueLightController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

private File downloadExcel(){
URL url;
 
		try {
			// get URL content
			url = new URL("http://www.esios.ree.es/Solicitar?fileName=PVPC_DETALLE_DD_20140529&fileType=xls&idioma=es");
			URLConnection conn = url.openConnection();
 
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
 
			String inputLine;
 
			//save to this filename
			String fileName = "C:/Users/Carlos/Desktop/Pruebas/cipote30.xls";
			File file = new File(fileName);
 
			if (!file.exists()) {
				file.createNewFile();
			}
 
			//use FileWriter to write file
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
 
			while ((inputLine = br.readLine()) != null) {
				bw.write(inputLine);
			}
 
			bw.close();
			br.close();
 
			System.out.println("Done");
                        fw.close();
                        return file;
 
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
                
                return null;
	}

private File downloadExcel2(){
URL url;
 
		try {
			// get URL content
			url = new URL("http://www.esios.ree.es/Solicitar?fileName=PVPC_DETALLE_DD_20140527&fileType=xls&idioma=es");
			URLConnection conn = url.openConnection();
                        InputStream is = conn.getInputStream();

                        // Fichero en el que queremos guardar el contenido
                        File salida = new File("C:/Users/Carlos/Desktop/Pruebas/cipote27.xls");
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
                        return salida;
                
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ValueLightController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ValueLightController.class.getName()).log(Level.SEVERE, null, ex);
         }
                return null;
                }

                
			
			
 
			
 
		
	
}
