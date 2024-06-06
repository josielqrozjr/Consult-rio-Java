package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Consulta {
    private LocalDate data;
    private LocalTime horario;
    private int medico;
    private String cpfPaciente;

    //constructor
    public Consulta(LocalDate data, LocalTime horario, int medico, String cpfPaciente){
        this.data = data;
        this.horario = horario;
        this.medico = medico;
        this.cpfPaciente = cpfPaciente;
    }
    //getters
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
    //this method will print the followings appointments
    public static void imprimirConsultasFuturasPorPaciente(List<Consulta> consultas, List<Medico> medicos, String cpfPaciente) {
        LocalDate dataAtual = LocalDate.now();

        System.out.println("Consultas agendadas para o Paciente com CPF " + cpfPaciente + ":");

        for (Consulta consulta : consultas) {
            if (consulta.getCpfPaciente().equals(cpfPaciente) && consulta.getData().isAfter(dataAtual)) {
                String nomeMedico = Medico.getMedicoNomePorCodigo(medicos, consulta);
                if (nomeMedico != null) {
                    System.out.println("- Data: " + consulta.getData() + ", Horário: " + consulta.getHorario() +
                            ", Médico: " + nomeMedico);
                }
            }
        }
    }
}
