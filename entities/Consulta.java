package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
    private LocalDate data;
    private LocalTime horario;
    private int medico;
    private String cpfPaciente;

    public Consulta(LocalDate data, LocalTime horario, int medico, String cpfPaciente){
        this.data = data;
        this.horario = horario;
        this.medico = medico;
        this.cpfPaciente = cpfPaciente;
    }
    public LocalDate getData(){
        return data;
    }
    
    public LocalTime getHorario() {
        return horario;
    }

    public int getMedico() {
        return medico;
    }
    public String getCpfPaciente() {
        return cpfPaciente;
    }
}
