package com.contravenciones.utility;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author ing_jefreypadilla
 */
public class DateUtility {

    /**
     *
     * @param fechaFormato
     * @return
     */
    public static XMLGregorianCalendar fechaRunt(Date fechaFormato) {
        if (fechaFormato == null) {
            return null;
        }
        XMLGregorianCalendar fecha = null;
        GregorianCalendar f = new GregorianCalendar();
        f.setTime(new java.sql.Date(fechaFormato.getTime()));
        try {
            fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(f);
        } catch (DatatypeConfigurationException ex) {
        }
        return fecha;
    }

    /**
     * Convierte texto a objeto Date en base a formato yyyy-dd-MM HH:mm:ss
     *
     * @param fecha Text La fecha en el formato espcificado
     * @return Objeto Date con la fecha
     * @throws java.lang.Exception
     */
    

    /**
     * Calcula la diferencia entre dos fechas
     *
     * @param date1 Fecha final
     * @param date2 Fecha inicial
     * @param timeUnit Unidad de tiempo con que se retorna la diferencia
     * @return
     * @throws java.lang.Exception
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) throws Exception {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Convierte una fecha Date a Calendario Calendar
     *
     * @param date Fecha a convertir
     * @return Objeto Calendar resultante
     * @throws java.lang.Exception
     */
    public static Calendar convertirDateToCalendar(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public synchronized Calendar formatearFecha(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public static synchronized String consultarFechaActualSincronizadaString() throws Exception {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
    
    public static synchronized Date consultarFechaActualSincronizadaDate() throws Exception {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Date(date.format(formatter));
    }
    
    public static synchronized String convertirFechaSincronizadaString(Date fecha) throws Exception {
        LocalDate localDate = fecha.toInstant()
                              .atZone(ZoneId.systemDefault())
                              .toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
        return formatter.format(localDate);
    }
    
    public static synchronized Date convertirFechaSincronizadaDate(Date fecha) throws Exception {
        LocalDate localDate = fecha.toInstant()
                              .atZone(ZoneId.systemDefault())
                              .toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
        return new Date(formatter.format(localDate));
    }
    
    public static synchronized Date convertirFechaSincronizadaDate(String fecha) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(fecha, formatter);
        return new Date(date.format(formatter));
    }
    

    private DateUtility() {
    }
}
