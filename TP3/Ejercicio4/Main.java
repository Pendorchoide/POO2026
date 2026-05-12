package TP3.Ejercicio4;

import TP3.Ejercicio4.domain.entities.Cancha;
import TP3.Ejercicio4.domain.entities.Turno;
import TP3.Ejercicio4.domain.ports.Repository;
import TP3.Ejercicio4.application.CanchaService;
import TP3.Ejercicio4.application.TurnoService;
import TP3.Ejercicio4.application.ReservaService;
import TP3.Ejercicio4.infrastructure.InMemoryCanchaRepository;
import TP3.Ejercicio4.infrastructure.InMemoryTurnoRepository;
import TP3.Ejercicio4.Presentation.Menu;

public class Main {
    public static void main(String[] args) {
        Repository<Cancha> repoCancha = new InMemoryCanchaRepository();
        Repository<Turno> repoTurno = new InMemoryTurnoRepository();

        TurnoService turnoService = new TurnoService(repoTurno);
        CanchaService canchaService = new CanchaService(repoCancha);
        ReservaService reservaService = new ReservaService(canchaService, turnoService);

        Menu menu = new Menu(reservaService);

        canchaService.createCancha();
        canchaService.createCancha();
        canchaService.createCancha();

        menu.showMenu();
    }
}
