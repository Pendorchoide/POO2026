import java.util.Scanner;

public class Main {
    private static Cancha cancha1;
    private static Cancha cancha2;
    private static Cancha cancha3;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Instanciar las 3 canchas
        cancha1 = new Cancha(1);
        cancha2 = new Cancha(2);
        cancha3 = new Cancha(3);
        scanner = new Scanner(System.in);

        // Mostrar menú interactivo
        int opcion;
        do {
            mostrarMenu();
            System.out.print("\nSeleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    verEstadoCanchas();
                    break;
                case 2:
                    registrarReserva();
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    // Mostrar menú principal
    private static void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE RESERVAS - COMPLEJO DEPORTIVO");
        System.out.println("========================================");
        System.out.println("1. Ver estado de las 3 canchas");
        System.out.println("2. Registrar una nueva reserva");
        System.out.println("3. Cancelar una reserva");
        System.out.println("4. Salir");
        System.out.println("========================================");
    }

    // Ver estado de las 3 canchas
    private static void verEstadoCanchas() {
        System.out.println("\n");
        cancha1.verHorarios();
        cancha2.verHorarios();
        cancha3.verHorarios();
    }

    // Registrar una nueva reserva
    private static void registrarReserva() {
        System.out.print("\nSeleccione la cancha (1, 2 o 3): ");
        int numeroCancha = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Validar que el número de cancha sea válido
        if (numeroCancha < 1 || numeroCancha > 3) {
            System.out.println("Error: Cancha inválida.");
            return;
        }

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la hora (14-22): ");
        int hora = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Validar que la hora sea válida
        if (hora < 14 || hora >= 23) {
            System.out.println("Error: Hora inválida. El complejo abre de 14:00 a 23:00 hs.");
            return;
        }

        // Crear el turno
        Turno turno = new Turno(nombre, hora);

        // Obtener la cancha correspondiente y realizar la reserva
        Cancha cancha = obtenerCancha(numeroCancha);
        if (cancha != null) {
            if (cancha.reservarTurno(turno)) {
                System.out.println("\nReserva exitosa en cancha " + numeroCancha + " a las " + hora + ":00");
            } else {
                System.out.println("\nError: Turno ocupado. La cancha " + numeroCancha + " ya tiene una reserva a las " + hora + ":00");
            }
        }
    }

    // Cancelar una reserva existente
    private static void cancelarReserva() {
        System.out.print("\nSeleccione la cancha (1, 2 o 3): ");
        int numeroCancha = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Validar que el número de cancha sea válido
        if (numeroCancha < 1 || numeroCancha > 3) {
            System.out.println("Error: Cancha inválida.");
            return;
        }

        System.out.print("Ingrese la hora a cancelar (14-22): ");
        int hora = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Validar que la hora sea válida
        if (hora < 14 || hora >= 23) {
            System.out.println("Error: Hora inválida.");
            return;
        }

        // Obtener la cancha correspondiente y cancelar la reserva
        Cancha cancha = obtenerCancha(numeroCancha);
        if (cancha != null) {
            if (cancha.cancelarReserva(hora)) {
                System.out.println("\nReserva cancelada exitosamente en cancha " + numeroCancha + " a las " + hora + ":00");
            } else {
                System.out.println("\nError: No existe una reserva en cancha " + numeroCancha + " a las " + hora + ":00");
            }
        }
    }

    // Obtener la cancha por su número
    private static Cancha obtenerCancha(int numeroCancha) {
        switch (numeroCancha) {
            case 1:
                return cancha1;
            case 2:
                return cancha2;
            case 3:
                return cancha3;
            default:
                return null;
        }
    }
}
