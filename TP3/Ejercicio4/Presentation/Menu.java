package TP3.Ejercicio4.Presentation;

import java.util.List;
import java.util.Scanner;

import TP3.Ejercicio4.application.ReservaService;
import TP3.Ejercicio4.domain.entities.Cancha;
import TP3.Ejercicio4.domain.entities.Turno;
import TP3.Ejercicio4.domain.enums.Hora;

class Colores {
    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
}

public class Menu {
    private ReservaService reservaService;
    private Scanner sc;

    public Menu(ReservaService reservaService){
        this.reservaService = reservaService;
        this.sc = new Scanner(System.in);
    }

    private void drawColumn(int xStart, List<Turno> turnos) {
        int startRow = 1;
        for (Hora h : Hora.values()) {
            gotoxy(xStart, startRow + (h.getHoraInt() - 14));
            Turno turnoEncontrado = turnos.stream()
                .filter(t -> t.getHour().equals(h))
                .findFirst()
                .orElse(null);

            if (turnoEncontrado != null) {
                System.out.print("[" + h.getValue() + "] " + Colores.ROJO + turnoEncontrado.getNombrePersona() + Colores.RESET);
            } else {
                System.out.print("[" + h.getValue() + "] " + Colores.VERDE + "Disponible" + Colores.RESET);
            }
        }
    }

    private void showCanchas() {
        clrscr();
        List<Cancha> canchas = this.reservaService.getCanchas();

        canchas.forEach(c -> {
            int xPos = (c.getId() - 1) * 30;
            
            gotoxy(xPos, 0);
            System.out.print(Colores.AZUL + "==== CANCHA " + c.getId() + " ====" + Colores.RESET);
            
            List<Turno> turnos = this.reservaService.getTurnosByCancha(c.getId());
            
            drawColumn(xPos, turnos);
        });
        
        gotoxy(0, 12); 
    }

    private void showRegistrarReservas() {
        clrscr();
        
        System.out.println(Colores.AZUL + "=== REGISTRAR NUEVA RESERVA ===" + Colores.RESET);
        System.out.println();

        System.out.print("Ingrese Cancha (1 - 2 - 3): ");
        int canchaId;
        try {
            canchaId = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Colores.ROJO + "\nError: Debe ingresar un número válido." + Colores.RESET);
            System.out.println("\nPresione ENTER para continuar...");
            sc.nextLine();
            return;
        }

        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese la hora (formato 14:00, 15:00, etc.): ");
        String horaStr = sc.nextLine();

        System.out.println("\nProcesando reserva...");
        
        try {
            Hora hora = Hora.fromString(horaStr);
            boolean saved = reservaService.reservar(canchaId, nombre, hora);
            
            if (saved) {
                System.out.println(Colores.VERDE + "\n¡Reserva registrada con éxito!" + Colores.RESET);
            } else {
                System.out.println(Colores.ROJO + "\n[ERROR] No se pudo realizar la reserva." + Colores.RESET);
                System.out.println(Colores.AMARILLO + "Motivo: La cancha ya tiene una reserva para las " + hora.getValue() + Colores.RESET);
            }
        } catch (Exception e) {
            System.out.println(Colores.ROJO + "\nError: " + e.getMessage() + Colores.RESET);
        }
    }

    private void showCancelarReserva() {
        clrscr();
        
        System.out.println(Colores.ROJO + "=== CANCELAR RESERVA ===" + Colores.RESET);
        System.out.println();

        System.out.print("Ingrese Cancha (1 - 2 - 3): ");
        int canchaId;
        try {
            canchaId = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Colores.ROJO + "Error: Debe ingresar un número válido." + Colores.RESET);
            System.out.println("\nPresione ENTER para continuar...");
            sc.nextLine();
            return;
        }

        System.out.print("Ingrese la hora del turno a cancelar (ej: 15:00): ");
        String horaStr = sc.nextLine();

        System.out.print("\n¿Está seguro que desea cancelar el turno de las " + horaStr + "? (S/N): ");
        String confirmar = sc.nextLine();

        if (confirmar.equalsIgnoreCase("S")) {
            try {
                Hora hora = Hora.fromString(horaStr);
                boolean exito = reservaService.cancelarReserva(canchaId, hora);
                
                if (exito) {
                    System.out.println(Colores.VERDE + "\nTurno cancelado correctamente." + Colores.RESET);
                } else {
                    System.out.println(Colores.AMARILLO + "\nNo se encontró un turno en ese horario." + Colores.RESET);
                }
            } catch (Exception e) {
                System.out.println(Colores.ROJO + "\nError: " + e.getMessage() + Colores.RESET);
            }
        } else {
            System.out.println("\nOperación abortada.");
        }
    }

    public void showMenu(){
        while (true) {
            clrscr();
            System.out.print("\n1. Ver estado de las canchas\n2. Registrar reserva\n3. Cancelar reserva\n4. Salir\n");
            System.out.print("\nSeleccione una opción: ");
            
            int op;
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Colores.ROJO + "Error: Debe ingresar un número válido." + Colores.RESET);
                System.out.print("\nPresione Enter para continuar...");
                sc.nextLine();
                continue;
            }
            
            switch (op) {
                case 1:
                    this.showCanchas();
                    break;
                case 2:
                    this.showRegistrarReservas();
                    break;
                case 3:
                    this.showCancelarReserva();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            System.out.print("\n\nPresione Enter para continuar...");
            sc.nextLine();
            clrscr();
        }
    }

    private static void clrscr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void gotoxy(int x, int y) {
        System.out.print(String.format("\033[%d;%dH", y + 1, x + 1));
        System.out.flush();
    }
}
