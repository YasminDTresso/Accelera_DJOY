package com.djoy.accelera.Service.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class parseData {

 public static LocalDateTime formatarData(String entrada) {
    try {
        // Tenta interpretar como data + hora
        return LocalDateTime.parse(entrada);
    } catch (Exception e1) {
        try {
            // Se falhar, tenta como apenas data (e adiciona hora padrão, ex: meio-dia)
            LocalDate data = LocalDate.parse(entrada);
            return data.atTime(18, 0); // ou .atStartOfDay()
        } catch (Exception e2) {
            throw new IllegalArgumentException("Formato de data inválido: " + entrada);
        }
    }
}

}
