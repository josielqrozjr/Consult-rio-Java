package entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Consulta implements Serializable{
    private static final long serialVersionUID = 1L;
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
        //Medico name = name.findMedicoByCodigo(, this.medico);
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

    //from here on the persistance of the objects will be done
    public void salvarConsultas(String nome_arquivo) throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nome_arquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        
        gravador.writeObject(this);
        
        gravador.close();
        arquivo.close();
    }
     // Method to read a list of consultations from a file
     @SuppressWarnings("unchecked")
    public static List<Consulta> abrirConsultas(String nome_arquivo) throws IOException, ClassNotFoundException {
        List<Consulta> consultas = null;
        FileInputStream arquivo = new FileInputStream(nome_arquivo);
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);

        consultas = (List<Consulta>)restaurador.readObject();
        
        restaurador.close();
        arquivo.close();
        
        return consultas;
    }

    public static void salvarListaDeConsultas(List<Consulta> consultas, String nome_arquivo) throws IOException, Exception{

        FileOutputStream arquivo = new FileOutputStream(nome_arquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(consultas);
        
        gravador.close();
        arquivo.close();
    }
}

/* 

public class AtualizarJogador {
    public static void main(String[] args) {
        String nome_arquivo = "BlueJedi.ser";
        try {
        Jogador jogador = Jogador.abrir(nome_arquivo);
        System.out.println("Jogador recuperado com sucesso!");
        jogador.exibir();
        jogador.posicionar(20.0f);
        jogador.pontuar();
        jogador.exibir();
        jogador.salvar(nome_arquivo);
        System.out.println("Jogador salvo com sucesso!");
        } catch (IOException e) {
        System.out.println("Excecao de I/O");
        e.printStackTrace();
        } catch (ClassNotFoundException e) {
        System.out.println("Excecao de classe desconhecida");
        e.printStackTrace();
        }
    }
}

*/
