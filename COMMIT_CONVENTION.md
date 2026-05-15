# 📝 Convención de Commits — POO-Segundo

Este repositorio sigue la convención **Conventional Commits** adaptada al contexto académico de Programación Orientada a Objetos.

---

## 📐 Formato general

```
<tipo>(<alcance>): <descripción corta>

[cuerpo opcional]

[pie opcional]
```

### Reglas básicas
- La **descripción corta** debe estar en **español**
- Usar **tiempo presente** e **infinitivo**: "agregar", "corregir", "eliminar"
- **Máximo 72 caracteres** en la primera línea
- Sin punto final en el título

---

## 🏷️ Tipos de commit

| Tipo       | Cuándo usarlo                                                         |
|------------|-----------------------------------------------------------------------|
| `feat`     | Añadir una nueva funcionalidad o ejercicio                            |
| `fix`      | Corregir un error en el código                                        |
| `refactor` | Reestructurar código sin cambiar su comportamiento externo            |
| `docs`     | Crear o modificar documentación (README, diagramas UML, convenciones) |
| `style`    | Cambios de formato, indentación, nombres — sin cambios de lógica      |
| `test`     | Agregar o modificar pruebas                                           |
| `chore`    | Tareas de mantenimiento: configs, .gitignore, dependencias            |
| `delete`   | Eliminar archivos o directorios innecesarios                          |
| `merge`    | Integración de ramas o pull requests                                  |

---

## 📦 Alcances (scopes)

El alcance indica qué parte del proyecto se ve afectada. Usar el nombre del TP y/o ejercicio:

```
tp1, tp2, tp3, tp4
tp3/ej1, tp3/ej2, tp3/ej3, tp3/ej4
global
```

---

## ✅ Ejemplos correctos

```
feat(tp3/ej3): agregar clase Continente con gestión de países

fix(tp2/ej1): corregir cálculo de herencia en clase Persona

refactor(tp3/ej3): extraer inicialización de datos a clase Seeder

docs(global): agregar diagrama UML del TP3

style(tp2/ej2): aplicar convenciones de nombres a variables

delete(global): eliminar archivos de prueba y carpeta .vscode

chore(global): ignorar carpeta out en .gitignore
```

---

## ❌ Ejemplos incorrectos

```
# Sin tipo ni alcance
correciones ejercicio 2

# Typo y sin convención
Mjeoras a las vistas del ejercicio 3

# Demasiado vago
Mucha IA

# Primera letra mayúscula sin tipo
Add Ejercicio4 and other project files
```

---

## 🔗 Recursos

- [Conventional Commits](https://www.conventionalcommits.org/es/v1.0.0/)
- [Guía de git rebase](https://git-scm.com/docs/git-rebase)
