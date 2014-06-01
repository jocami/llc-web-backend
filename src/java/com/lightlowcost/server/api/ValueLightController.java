/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lightlowcost.server.api;

import com.lightlowcost.server.dao.CarRateDAO;
import com.lightlowcost.server.dao.NightRateDAO;
import com.lightlowcost.server.dao.NormalRateDAO;
import com.lightlowcost.server.dao.ValuesDayDAO;
import com.lightlowcost.server.domain.CarRate;
import com.lightlowcost.server.domain.NightRate;
import com.lightlowcost.server.domain.NormalRate;
import com.lightlowcost.server.domain.ValuesDay;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jxl.*;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Carlos
 */
@Controller
public class ValueLightController {
    
    @Autowired
    ValuesDayDAO valuesDayDAO;
    
    @Autowired
    CarRateDAO carRateDAO;
    
    @Autowired
    NightRateDAO nightRateDAO;
    
    @Autowired
    NormalRateDAO normalRateDAO;
    
    
     @RequestMapping(value = {"/valueLight/{fecha}"}, method = RequestMethod.GET)
    public void valueLight(HttpServletRequest request, HttpServletResponse response,@PathVariable("fecha") String fechaStr) {
        
        int date;
        String hours[] = {"00:00-01:00","01:00-02:00","02:00-03:00","03:00-04:00","04:00-05:00","05:00-06:00",
            "06:00-07:00","07:00-08:00","08:00-09:00","09:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00",
            "13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00","18:00-19:00","19:00-20:00",
            "20:00-21:00","21:00-22:00","22:00-23:00","23:00-00:00"};
        date = Integer.parseInt(fechaStr);
        
        try {
             if(!valuesDayDAO.findByDateBool(date)){ 
                ValuesDay valuesDay = valuesDayDAO.findByDate(date);
                downloadExcel(fechaStr);//Formato yyyymmdd
                Workbook workbook = Workbook.getWorkbook(new File("C:/Users/Carlos/Desktop/Pruebas/excelDay.xls"));
                Sheet sheet = workbook.getSheet(0);
                ValuesDay day = new ValuesDay();
                day.setId_valuesday(date);
                List<NormalRate> normals = new ArrayList();
                List<CarRate> cars = new ArrayList();
                List<NightRate> nights = new ArrayList();

                for(int i=5;i<29;i++){
                   NormalRate normal = new NormalRate();
                   normal.setDay(day);
                   normal.setHour(hours[i-5]);
                   Cell normalCell = sheet.getCell(4,i);
                   String val = normalCell.getContents();
                   normal.setValue(parseInt(val));
                   normals.add(normal);
                   normalRateDAO.insert(normal);       
                }
                for(int i=29;i<53;i++){
                   NightRate night = new NightRate();
                   night.setDay(day);
                   night.setHour(hours[i-29]);
                   Cell nightCell = sheet.getCell(4,i);
                   String val = nightCell.getContents();
                   night.setValue(parseInt(val));
                   nights.add(night);
                   nightRateDAO.insert(night);       
                }
                for(int i=53;i<77;i++){
                   CarRate car = new CarRate();
                   car.setDay(day);
                   car.setHour(hours[i-53]);
                   Cell carCell = sheet.getCell(4,i);
                   String val = carCell.getContents();
                   car.setValue(parseInt(val));
                   cars.add(car);
                   carRateDAO.insert(car);                
                }
                day.setCarRate(cars);
                day.setNightRate(nights);
                day.setNormalRate(normals);
                valuesDayDAO.insert(day);
                workbook.close();

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; chaset=UTF-8");
                response.getWriter().println("Values save like id-valueday = " + String.valueOf(date));
             }
        else{
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                response.setContentType("application/json; chaset=UTF-8");
                response.getWriter().println("Already exists id-valueday = " + String.valueOf(date));
        
        }    
         } catch (IOException ex) {
            Logger.getLogger(ValueLightController.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8;");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
         } catch (BiffException ex) {
            Logger.getLogger(ValueLightController.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8;");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
         }
   
    }


    private Double parseInt(String val) {
        String num = val.replace(',', '.');
        return Double.parseDouble(num);
    }
    
    private void downloadExcel(String fechaStr) throws MalformedURLException, IOException{
        URL url;
        String dir = "C:/Users/Carlos/Desktop/Pruebas/excelDay.xls";

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
                }

    private int parseDate(String fechaStr){
        String year = fechaStr.substring(0, 4);
        String month = fechaStr.substring(4, 6);
        String day = fechaStr.substring(6, 8);
        String resString = day+month+year;
        int resInt = Integer.parseInt(resString);
        return resInt;
    }

                
			
			
 
			
 
		
	
}
