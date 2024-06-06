package entities;

import java.time.LocalDate;
import java.util.*;

import readers.*;

public class Medico {
    private String nome;
    private int codigo;
    private List<Paciente> pacientes;

    //constructor
    public Medico(String nome, int codigo){
        this.nome  = nome;
        this.codigo = codigo;
        this.pacientes = new ArrayList<>();
    }
    //getters
    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
    
    //setter
    public void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    //method to return consults in a given period of time
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
    //methos to find a doctor through it's code
    public static Medico findMedicoByCodigo(List<Medico> medicos, int codigo) {
        for (Medico medico : medicos) {
            if (medico.getCodigo() == codigo) {
                return medico;
            }
        }
        System.out.println("Médico não encontrado, redirecionando de volta...");
        return null; // Médico não encontrado
 
    }
    // Method to return doctor name based on appointment code (for future appointments only)
    public static String getMedicoNomePorCodigo(List<Medico> medicos, Consulta consulta) {
        int codigoMedico = consulta.getMedico();

        for (Medico medico : medicos) {
            if (medico.getCodigo() == codigoMedico) {
                return medico.getNome();
            }
        }
        return null; // Retorna null se o médico não for encontrado
    }

    //method to get the inative pacients
    public List<Paciente> getPacientesInativos(int mesesInatividade) {
        List<Consulta> consultas = DadosCSVReaderConsulta.lerConsultasDoCSV();
        List<Paciente> pacientes = DadosCSVReaderPaciente.lerPacientesDoCSV(consultas);
        
        List<Paciente> pacientesInativos = new ArrayList<>();
        LocalDate dataAtual = LocalDate.now();
    
        for (Paciente paciente : pacientes) {
            if (Paciente.isPacienteInativo(paciente, consultas, mesesInatividade, dataAtual)) {
                pacientesInativos.add(paciente);
            }
        }
    
        return pacientesInativos;
    }
}