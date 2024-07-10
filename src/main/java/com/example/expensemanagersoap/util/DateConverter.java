package com.example.expensemanagersoap.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class DateConverter {

    public static XMLGregorianCalendar convertLocalDateToXMLGregorianCalendar(LocalDate localDate) {
        try {
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace(); // Obsługa wyjątku
            return null;
        }
    }

    public static LocalDate convertXMLGregorianCalendarToLocalDate(XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar == null) {
            return null;
        }

        return xmlGregorianCalendar.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }
}