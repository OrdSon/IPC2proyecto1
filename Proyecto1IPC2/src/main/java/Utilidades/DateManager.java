/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OrdSon
 */
public class DateManager {

    public Date convertirADate(LocalDate localDate) {
        Date date = Date.valueOf(localDate);
        return date;
    }

    public LocalDate convertirALocalDate(Date date) {
        LocalDate localDate = date.toLocalDate();
        return localDate;
    }

    public Date formatear(String fecha) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date temporalDate = format.parse(fecha);
            Date date = new Date(temporalDate.getTime());
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
