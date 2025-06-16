package com.djoy.accelera.Service.Util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class parseData {

 public static String formatarLocalDateTimeSQL(String entrada) {
    try {
        // Se o usuário informar uma data completa com hora, formatamos normalmente
        LocalDateTime dataHora = LocalDateTime.parse(entrada);
        return dataHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    } catch (Exception e1) {
        try {
            // Se for apenas uma data, adicionamos um horário padrão ou 00:00:00
            LocalDate data = LocalDate.parse(entrada);
            LocalDateTime dataHora = data.atStartOfDay(); // Define para meia-noite
            return dataHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        } catch (Exception e2) {
            throw new IllegalArgumentException("Formato de data inválido: " + entrada);
        }
    }
}

public static Date formatarDateSQL(String entrada) {
    try {
        // Converter a String para LocalDate
        LocalDate data = LocalDate.parse(entrada, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Converter LocalDate para java.sql.Date
        return Date.valueOf(data);
    } catch (Exception e) {
        throw new IllegalArgumentException("Formato de data inválido: " + entrada);
    }
}

}
