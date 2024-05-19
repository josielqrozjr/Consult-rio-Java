package entities;

import java.time.LocalDate;
import java.util.*;

import readers.*;

public class Medico {
    private String nome;
    private int codigo;
    private List<Paciente> pacientes;

    public Medico(String nome, int codigo){
        this.nome  = nome;
        this.codigo = codigo;
        this.pacientes = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
    
    public void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }
    public List<Consulta> getConsultasNoPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        List<Consulta> consultasNoPeriodo = new ArrayList<>();
        List<Consulta> consultas = DadosCSVReaderConsulta.lerConsultasDoCSV();
        
        for (Consulta consulta : consultas) {
            if (consulta.getData().isAfter(dataInicial) && consulta.getData().isBefore(dataFinal)) {
                consultasNoPeriodo.add(consulta);
            }
        }
        consultasNoPeriodo.sort(Comparator.comparing(Consulta::getHorario));
        return consultasNoPeriodo;
    }
    public List<Paciente> getPacientesInativos(int mesesInatividade) {
        List<Consulta> consultas = DadosCSVReaderConsulta.lerConsultasDoCSV();
        List<Paciente> pacientes = DadosCSVReaderPaciente.lerPacientesDoCSV(consultas);
        
        List<Paciente> pacientesInativos = new ArrayList<>();
        LocalDate dataAtual = LocalDate.now();
    
        for (Paciente paciente : pacientes) {
            if (isPacienteInativo(paciente, consultas, mesesInatividade, dataAtual)) {
                pacientesInativos.add(paciente);
            }
        }
    
        return pacientesInativos;
    }
    
    private boolean isPacienteInativo(Paciente paciente, List<Consulta> consultas, int mesesInatividade, LocalDate dataAtual) {
        for (Consulta consulta : consultas) {
            if (consulta.getCpfPaciente().equals(paciente.getCpf())) {
                LocalDate dataConsulta = consulta.getData();
                // Verifica se a consulta foi há mais de 'mesesInatividade' meses
                if (dataConsulta.isBefore(dataAtual.minusMonths(mesesInatividade))) {
                    return true; // Paciente está inativo há mais tempo do que o especificado
                }
            }
        }
        return false; // Paciente não está inativo há mais de 'mesesInatividade' meses
    }
    
}