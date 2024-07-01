package logica;

import java.util.ArrayList;

public class Becas {
    private static final int MAX_ESTUDIANTES = 100;
    private ArrayList<Estudiantes> estudiantes;

    public Becas() {
        estudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiantes estudiante) {
        if (estudiantes.size() < MAX_ESTUDIANTES) {
            estudiantes.add(estudiante);
        } else {
            System.out.println("No se puede agregar más estudiantes. Límite alcanzado.");
        }
    }

    public ArrayList<String> obtenerEstudiantesBecados() {
        ArrayList<String> estudiantesBecados = new ArrayList<>();

        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getIndiceAcademico() >= 2.0) {
                estudiantesBecados.add(estudiante.getNombre());
            }
        }

        return estudiantesBecados;
    }

    public Estudiantes buscarPorCedula(String cedula) {
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                return estudiante;
            }
        }
        return null;
    }

    public ArrayList<Estudiantes> buscarPorCarrera(String carrera) {
        ArrayList<Estudiantes> resultado = new ArrayList<>();
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getCarrera().equals(carrera)) {
                resultado.add(estudiante);
            }
        }
        return resultado;
    }

    public ArrayList<Estudiantes> buscarPorSexo(String sexo) {
        ArrayList<Estudiantes> resultado = new ArrayList<>();
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getSexo().equals(sexo)) {
                resultado.add(estudiante);
            }
        }
        return resultado;
    }
}
