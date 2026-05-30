# Diagrama de Clases UML - Sistema de Reservas de Complejo Deportivo

## Estructura de Clases

```
┌─────────────────────────────┐
│         Turno               │
├─────────────────────────────┤
│ - nombrePersona: String     │
│ - hora: int                 │
├─────────────────────────────┤
│ + Turno(String, int)        │
│ + getNombrePersona(): String│
│ + getHora(): int            │
│ + toString(): String        │
└─────────────────────────────┘
         ▲
         │
         │ utiliza
         │
┌─────────────────────────────────────┐         ┌──────────────────────┐
│          Cancha                     │◄────────│   (3 instancias)     │
├─────────────────────────────────────┤         └──────────────────────┘
│ - numeroCancha: int                 │
│ - turnos: ArrayList<Turno>          │
├─────────────────────────────────────┤
│ + Cancha(int)                       │
│ + getNumeroCancha(): int            │
│ + reservarTurno(Turno): boolean     │
│ + cancelarReserva(int): boolean     │
│ + verHorarios(): void               │
│ + getTurnos(): ArrayList<Turno>     │
└─────────────────────────────────────┘


┌─────────────────────────────────────┐
│          Main                       │
├─────────────────────────────────────┤
│ + main(String[]): void              │
│ - mostrarMenu(): void               │
│ - verEstadoCanchas(): void          │
│ - registrarReserva(): void          │
│ - cancelarReserva(): void           │
└─────────────────────────────────────┘
```

## Relaciones

- **Cancha contiene Turno**: Relación de composición (ArrayList<Turno>)
- **Main utiliza Cancha**: Relación de asociación (interactúa con las 3 canchas)
- **Main crea Turno**: Relación de uso (instancia Turnos a través de datos del usuario)

## Reglas de Negocio

1. Horario: 14:00 a 23:00 hs (turnos en punto)
2. Cancha valida no duplicados de hora antes de agregar
3. Separación de responsabilidades: Cancha valida y gestiona, Main muestra mensajes al usuario
