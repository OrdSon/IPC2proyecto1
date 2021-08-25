/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import javax.persistence.AttributeConverter;

/**
 *
 * @author OrdSon
 */
public class DateManager {


    public Date convertirADate(LocalDate localDate) {
        Date date = Date.valueOf(localDate);
        return date;
    }

    public LocalDate convertirALocalDate(Date date){
        LocalDate localDate = date.toLocalDate();
        return localDate;
    }
}
