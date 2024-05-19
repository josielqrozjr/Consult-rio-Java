package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Paciente implements Serializable{
    private static final long serialVersionUID = 1L;

    private String nome;
    private String cpf;
    private List<Consulta> consultas;
    
    public Paciente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.consultas = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }
    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }
    public List<Medico> getMedicosConsultados(List<Medico> medicos) {
        List<Medico> medicosConsultados = new ArrayList<>();

        for (Consulta consulta : consultas) {
            int codigoMedico = consulta.getMedico();
            for (Medico medico : medicos) {
                if (medico.getCodigo() == codigoMedico && !medicosConsultados.contains(medico)) {
                    medicosConsultados.add(medico);
                    break; // Para evitar adicionar o mesmo médico mais de uma vez
                }
            }
        }

        return medicosConsultados;
    }
    public List<Consulta> getConsultasComMedico(Medico medico) {
        List<Consulta> consultasComMedico = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getMedico() == medico.getCodigo()) {
                consultasComMedico.add(consulta);
            }
        }
        return consultasComMedico;
    }
    public boolean ultimaConsultaAntesDe(LocalDate dataLimite) {
        if (consultas.isEmpty()) {
            return false; // Se não houver consultas, considerar como não ocorrida
        }

        LocalDate ultimaConsulta = consultas.get(consultas.size() - 1).getData();
        return ultimaConsulta.isBefore(dataLimite);
    }
}