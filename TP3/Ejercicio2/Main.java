package TP3.Ejercicio2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorEmpleado gestor = new GestorEmpleado();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(Vistas.obtenerMenu());
            int opcion = leerOpcionValida(scanner);

            switch (opcion) {
                case 1:
                    registrarEmpleado(gestor, scanner);
                    break;
                case 2:
                    listarEmpleados(gestor);
                    break;
                case 3:
                    quitarEmpleado(gestor, scanner);
                    break;
                case 4:
                    mostrarPromedioSueldo(gestor);
                    break;
                case 5:
                    mostrarEmpleadoQueMasGana(gestor);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
            limpiarConsola();
        }
    }

    private static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static int leerOpcionValida(Scanner scanner) {
        while (true) {
            System.out.print("Seleccione una opción: ");
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();
                return opcion;
            }
            System.err.println("Error: opción inválida. Ingrese un número válido.");
            scanner.nextLine();
        }
    }

    private static int leerEnteroValido(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            }
            System.err.println("Error: debe ingresar un número entero válido.");
            scanner.nextLine();
        }
    }

    private static int leerDniValido(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEnteroValido(scanner, mensaje);
            if (valor > 0) {
                return valor;
            }
            System.err.println("Error: el DNI debe ser un número positivo.");
        }
    }

    private static double leerDoubleValido(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            }
            System.err.println("Error: debe ingresar un número válido (por ejemplo, 1200.50).");
            scanner.nextLine();
        }
    }

    private static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.err.println("Error: el texto no puede estar vacío.");
        }
    }

    private static void registrarEmpleado(GestorEmpleado gestor, Scanner scanner) {
        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del empleado: ");
        int dni = leerDniValido(scanner, "Ingrese el DNI del empleado: ");
        double sueldo = leerDoubleValido(scanner, "Ingrese el sueldo del empleado: ");

        Empleado empleado = new Empleado(nombre, dni, sueldo);
        
        if (!gestor.registrarEmpleado(empleado)) {
            System.out.println("Error: ya existe un empleado con el mismo DNI.");
            return;
        }
        
        if (empleado.fueAjustado()) {
            System.out.println("Advertencia: El sueldo se ajustó al salario mínimo de 300.00.");
        }
        System.out.println("Empleado registrado correctamente.");
    }

    private static void listarEmpleados(GestorEmpleado gestor) {
        System.out.println(Vistas.formatearListaEmpleados(gestor.getEmpleados()));
    }

    private static void quitarEmpleado(GestorEmpleado gestor, Scanner scanner) {
        int dni = leerDniValido(scanner, "Ingrese el DNI del empleado a quitar: ");

        if (!gestor.existeEmpleadoDNI(dni)) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        gestor.quitarEmpleado(dni);
        System.out.println("Empleado quitado correctamente.");
    }

    private static void mostrarPromedioSueldo(GestorEmpleado gestor) {
        if (gestor.getEmpleados().isEmpty()) {
            System.out.println("No hay empleados para calcular el promedio.");
        } else {
            double promedio = gestor.sueldoPromedio();
            System.out.println("El sueldo promedio es: " + promedio);
        }
    }

    private static void mostrarEmpleadoQueMasGana(GestorEmpleado gestor) {
        Empleado mejor = gestor.empleadoQueMasGana();
        if (mejor == null) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("Empleado que más gana:");
            System.out.println(Vistas.formatearEmpleado(mejor));
        }
    }
}
