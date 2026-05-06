package TP3.Ejercicio4.domain.enums;

public enum Hora {
    H_14_00("14:00"),
    H_15_00("15:00"),
    H_16_00("16:00"),
    H_17_00("17:00"),
    H_18_00("18:00"),
    H_19_00("19:00"),
    H_20_00("20:00"),
    H_21_00("21:00"),
    H_22_00("22:00"),
    H_23_00("23:00");

    private final String value;

    Hora(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getHoraInt() {
        return Integer.parseInt(value.split(":")[0]);
    }

    public static Hora fromString(String horaStr) {
        if (horaStr == null || !horaStr.matches("^(1[4-9]|2[0-3]):00$")) {
            throw new IllegalArgumentException("Formato inválido. Use hh:00 entre 14:00 y 23:00");
        }
        for (Hora h : values()) {
            if (h.value.equals(horaStr)) {
                return h;
            }
        }
        throw new IllegalArgumentException("Hora no válida. Rango 14:00-23:00");
    }
}
