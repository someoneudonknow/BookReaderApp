/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author trang
 */
public class formatDate {

    public static String formatDate(String unFormatDate) throws ParseException {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = inputFormat.parse(unFormatDate);

        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        String outputDate = outputFormat.format(date);
        return outputDate;
    }
}
