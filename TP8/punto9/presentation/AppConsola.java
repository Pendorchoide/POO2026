package presentation;

import java.util.List;
import java.util.Scanner;

import app.dto.LineaRequest;
import app.dto.NuevaFacturaRequest;
import domain.descuento.Descuento;
import domain.descuento.DescuentoJubilados;
import domain.descuento.DescuentoPorCantidad;
import domain.descuento.DescuentoPorCategoria;
import domain.descuento.DescuentoPorVolumen;
import domain.descuento.DescuentoTresPorDos;
import domain.factura.TipoFactura;
import presentation.controlador.ControladorFactura;

public final class AppConsola {
    private final ControladorFactura controlador;
    private final Scanner scanner = new Scanner(System.in);

    public AppConsola(ControladorFactura controlador) {
        this.controlador = controlador;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Emitir factura A");
            System.out.println("2. Emitir factura B");
            System.out.println("3. Emitir factura C (combinacion 3x2 + volumen + jubilados)");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            int opcion = scanner.nextInt();
            if (opcion == 0) {
                return;
            }

            switch (opcion) {
                case 1 -> emitir(TipoFactura.A, false);
                case 2 -> emitir(TipoFactura.B, true);
                case 3 -> emitirCombinada();
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private void emitir(TipoFactura tipo, boolean conDescuentoJubilados) {
        DescuentoPorCantidad tresPorDos = new DescuentoTresPorDos();

        LineaRequest teclados   = new LineaRequest("0", 3, List.of(), List.of(tresPorDos));
        LineaRequest monitores  = new LineaRequest("1", 4, List.of(), List.of());
        LineaRequest servicios  = new LineaRequest("2", 5, List.of(), List.of());
        LineaRequest leches     = new LineaRequest("3", 6, List.of(), List.of());

        List<Descuento> descuentosFactura = conDescuentoJubilados
            ? List.of(new DescuentoJubilados())
            : List.of();

        this.controlador.crearYMostrar(new NuevaFacturaRequest(
            tipo,
            List.of(teclados, monitores, servicios, leches),
            descuentosFactura)
        );
    }

    private void emitirCombinada() {
        DescuentoPorCantidad tresPorDos = new DescuentoTresPorDos();
        DescuentoPorCantidad volumen = new DescuentoPorVolumen(5, 0.10);

        LineaRequest teclados = new LineaRequest("0", 3, List.of(), List.of(tresPorDos));
        LineaRequest monitores = new LineaRequest("1", 4, List.of(), List.of());
        LineaRequest servicios = new LineaRequest("2", 5, List.of(), List.of());
        LineaRequest leches = new LineaRequest("3", 6, List.of(), List.of(volumen));

        List<Descuento> descuentosFactura = List.of(
            new DescuentoJubilados(),
            new DescuentoPorCategoria("VIP", 0.05));

        this.controlador.crearYMostrar(new NuevaFacturaRequest(
            TipoFactura.B,
            List.of(teclados, monitores, servicios, leches),
            descuentosFactura));
    }
}