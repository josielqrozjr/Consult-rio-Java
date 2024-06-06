package application;

import java.time.LocalDate;
import java.util.*;

import entities.*;
import readers.*;

public class InterfaceUser{
    public static void main(String[] args){

        List<Consulta> consultas = DadosCSVReaderConsulta.lerConsultasDoCSV();
        // Passo 2: Ler pacientes e associar consultas
        List<Paciente> pacientes = DadosCSVReaderPaciente.lerPacientesDoCSV(consultas);
        // Passo 3: Ler médicos e associar pacientes
        List<Medico> medicos = DadosCSVReaderMedico.lerMedicosDoCSV(pacientes);
        
        boolean continuar = true;

        try{
            while (continuar){
                mostrarOpcoes();
                int escolha = readerInputs.read_int("Digite o número correspondente à opção desejada: ");

                int txt = 2;
                String file_name;

                switch (escolha){
                    case 0:
                        System.out.println("Encerrando o programa...");
                        continuar = false;
                        break;
                    case 1:
                        int codigoMedico = readerInputs.read_int("Digite o código do médico: ");
                        
                        Medico medicoSelecionado = Medico.findMedicoByCodigo(medicos, codigoMedico);

                        while (medicoSelecionado == null){
                            codigoMedico = readerInputs.read_int("Digite o código do médico: ");
                            medicoSelecionado = Medico.findMedicoByCodigo(medicos, codigoMedico);
                        }

                        List<String> resultLines = new ArrayList<>();

                        List<Paciente> pacientesMedico = medicoSelecionado.getPacientes();
                        resultLines.add("Pacientes do Médico " + medicoSelecionado.getNome() + ":");
                        
                        //adiciona os pacientes a saída para o txt
                        for (Paciente paciente : pacientesMedico) 
                            resultLines.add("- " + paciente.getNome() + " (" + paciente.getCpf() + ")");
                        
                            //mostra os pacientes do médico na tela
                        System.out.println("Pacientes do Médico " + medicoSelecionado.getNome() + ":");
                        for (Paciente paciente : pacientesMedico) 
                            System.out.println("- " + paciente.getNome() + " (" + paciente.getCpf() + ")");
                        
                        txt = readerInputs.read_int("\nGostaria de imprimir os resultados em um arquivo texto?\nSe sim digite 1, do contrário 0 para encerrar: ");
                        if (txt == 1) {
                            // Escrever o resultado em um arquivo
                            file_name = readerInputs.read_string("Type the name of your file: ");
                            FileUtil.writeLinesToFile(resultLines,file_name + ".txt");
                            break;
                        }
                        
                    break;
                    case 2:
                        int codigoMedicoConsulta = readerInputs.read_int("Digite o código do médico: ");
                        Medico medicoConsulta = Medico.findMedicoByCodigo(medicos, codigoMedicoConsulta);
                        List<String> resultLines2 = new ArrayList<>();

                        if (medicoConsulta != null) {
                            LocalDate dataInicial = LocalDate.parse(readerInputs.read_string("Digite a data inicial (YYYY-MM-DD): "));
                            LocalDate dataFinal = LocalDate.parse(readerInputs.read_string("Digite a data final (YYYY-MM-DD): "));

                            resultLines2.add("Consultas do Médico " + medicoConsulta.getNome() + " no período de "
                                    + dataInicial + " a " + dataFinal + ":");

                            for (Consulta consulta : consultas) {
                                if (consulta.getMedico() == codigoMedicoConsulta) {
                                    LocalDate consultaDate = consulta.getData();

                                    if (consultaDate.compareTo(dataInicial) >= 0 && consultaDate.compareTo(dataFinal) <= 0) {
                                        resultLines2.add("- Data: " + consulta.getData() + ", Horário: " + consulta.getHorario() +
                                                ", Paciente: " + Paciente.getPacienteNomePorCPF(consulta.getCpfPaciente()));
                                    }
                                }
                            }System.out.println("Consultas do Médico " + medicoConsulta.getNome() + " no período de "
                            + dataInicial + " a " + dataFinal + ":");
                        
                            for (Consulta consulta : consultas) {
                                if (consulta.getMedico() == codigoMedicoConsulta) {
                                    LocalDate consultaDate = consulta.getData();
                                
                                    if (consultaDate.compareTo(dataInicial) >= 0 && consultaDate.compareTo(dataFinal) <= 0) {
                                        System.out.println("- Data: " + consulta.getData() + ", Horário: " + consulta.getHorario() +
                                                ", Paciente: " + Paciente.getPacienteNomePorCPF(consulta.getCpfPaciente()));
                                    }
                                }
                            }
                            txt = readerInputs.read_int("\nGostaria de imprimir os resultados em um arquivo texto?\nSe sim digite 1, do contrário 0 para encerrar: ");
                            if (txt == 1) {
                                // Escrever o resultado em um arquivo
                                file_name = readerInputs.read_string("Type the name of your file: ");
                                FileUtil.writeLinesToFile(resultLines2,file_name + ".txt");
                                break;
                            }
                        }
                        else {
                            resultLines2.add("Médico não encontrado.");
                            break;
                        }
                    break;
                    case 3:
                        String cpfPacienteConsulta = readerInputs.read_string("Digite o CPF do paciente: ");
                        
                        Paciente pacienteConsulta =Paciente.findPacienteByCPF(pacientes, cpfPacienteConsulta);
                        //verifica se o paciente realmente existe
                        while (pacienteConsulta == null){
                            cpfPacienteConsulta = readerInputs.read_string("Digite o CPD do paciente: ");
                            pacienteConsulta = Paciente.findPacienteByCPF(pacientes, cpfPacienteConsulta);
                        }

                        List<String> resultLines3 = new ArrayList<>();

                        if (pacienteConsulta != null) {
                            List<Medico> medicosConsultados = pacienteConsulta.getMedicosConsultados(medicos);
                            resultLines3.add("Médicos consultados pelo Paciente " + pacienteConsulta.getNome() + ":");
                            for (Medico medico : medicosConsultados) {
                                resultLines3.add("- " + medico.getNome() + " (Código: " + medico.getCodigo() + ")");
                            }System.out.println("Médicos consultados pelo Paciente " + pacienteConsulta.getNome() + ":");
                            for (Medico medico : medicosConsultados) {
                                System.out.println("- " + medico.getNome() + " (Código: " + medico.getCodigo() + ")");
                            }

                            txt = readerInputs.read_int("\nGostaria de imprimir os resultados em um arquivo texto?\nSe sim digite 1, do contrário 0 para encerrar: ");
                            if (txt == 1) {
                                // Escrever o resultado em um arquivo
                                file_name = readerInputs.read_string("Type the name of your file: ");
                                FileUtil.writeLinesToFile(resultLines3,file_name + ".txt");
                                break;
                            }
                        }
                    break;
                    case 4:
                        String cpfPacienteConsulta2 = readerInputs.read_string("Digite o CPF do paciente: ");
                        int codigoMedicoConsulta2 = readerInputs.read_int("Digite o código do médico: ");
                        
                        Paciente pacienteConsulta2 = Paciente.findPacienteByCPF(pacientes, cpfPacienteConsulta2);
                        //verifica se o paciente realmente existe
                        while (pacienteConsulta2 == null){
                            cpfPacienteConsulta2 = readerInputs.read_string("Digite o CPD do paciente: ");
                            pacienteConsulta2 = Paciente.findPacienteByCPF(pacientes, cpfPacienteConsulta2);
                        }

                        Medico medicoConsulta2 = Medico. findMedicoByCodigo(medicos, codigoMedicoConsulta2);
                        
                        //check if the doctor really exists
                        while (medicoConsulta2 == null){
                            codigoMedico = readerInputs.read_int("Digite o código do médico: ");
                            medicoSelecionado = Medico.findMedicoByCodigo(medicos, codigoMedicoConsulta2);
                        }

                        List<String> resultLines4 = new ArrayList<>();

                        if (pacienteConsulta2 != null && medicoConsulta2 != null) {
                            List<Consulta> consultasRealizadas = pacienteConsulta2.getConsultasComMedico(medicoConsulta2);
                            resultLines4.add("Consultas realizadas pelo Paciente " + pacienteConsulta2.getNome() +
                                    " com o Médico " + medicoConsulta2.getNome() + ":");
                            for (Consulta consulta : consultasRealizadas) {
                                resultLines4.add("- Data: " + consulta.getData() + ", Horário: " + consulta.getHorario());
                            }
                            System.out.println("Consultas realizadas pelo Paciente " + pacienteConsulta2.getNome() +
                                    " com o Médico " + medicoConsulta2.getNome() + ":");
                            for (Consulta consulta : consultasRealizadas) {
                                System.out.println("- Data: " + consulta.getData() + ", Horário: " + consulta.getHorario());
                            }
                            txt = readerInputs.read_int("\nGostaria de imprimir os resultados em um arquivo texto?\nSe sim digite 1, do contrário 0 para encerrar: ");
                            if (txt == 1) {
                                // Escrever o resultado em um arquivo
                                file_name = readerInputs.read_string("Type the name of your file: ");
                                FileUtil.writeLinesToFile(resultLines4,file_name + ".txt");
                                break;
                            }
                        } else {
                            resultLines4.add("Paciente ou Médico não encontrado.");
                            break;
                        }
                    break;
                    case 5:
                        String cpfPaciente3 = readerInputs.read_string("Digite o CPF do paciente: ");
                        List<String> resultLines5 = new ArrayList<>();
                        resultLines5.add("Consultas agendadas para o Paciente com CPF " + cpfPaciente3 + ":");

                        for (Consulta consulta : consultas) {
                            if (consulta.getCpfPaciente().equals(cpfPaciente3) && consulta.getData().isAfter(LocalDate.now())) {
                                String nomeMedico = Medico.getMedicoNomePorCodigo(medicos, consulta);
                                if (nomeMedico != null) {
                                    resultLines5.add("- Data: " + consulta.getData() + ", Horário: " + consulta.getHorario() +
                                            ", Médico: " + nomeMedico);
                                }
                            }
                        }
                        Consulta.imprimirConsultasFuturasPorPaciente(consultas, medicos, cpfPaciente3);

                        txt = readerInputs.read_int("\nGostaria de imprimir os resultados em um arquivo texto?\nSe sim digite 1, do contrário 0 para encerrar: ");
                        if (txt == 1) {
                            // Escrever o resultado em um arquivo
                            file_name = readerInputs.read_string("Type the name of your file: ");
                            FileUtil.writeLinesToFile(resultLines5,file_name + ".txt");
                            break;
                        }
                        else{
                            break;
                        }
                    case 6:
                        int codigoMedicoConsulta4 = readerInputs.read_int("Digite o código do médico: ");
                        
                        Medico medicoConsulta4 = Medico.findMedicoByCodigo(medicos, codigoMedicoConsulta4);
                        
                        //verifica se o médico realmente existe
                        while (medicoConsulta4 == null){
                            codigoMedicoConsulta4 = readerInputs.read_int("Digite o código do médico: ");
                            medicoConsulta4 = Medico.findMedicoByCodigo(medicos, codigoMedicoConsulta4);
                        }

                        List<String> resultLines6 = new ArrayList<>();

                    
                        if (medicoConsulta4 != null) {
                            int mesesInatividade = readerInputs.read_int("Digite o número de meses de inatividade permitidos: ");
                            
                            List<Paciente> pacientesInativos = medicoConsulta4.getPacientesInativos(mesesInatividade);

                            for(Paciente paciente : pacientesInativos){
                                System.out.println("- " + paciente.getNome() + " (" + paciente.getCpf() + ")");
                            }
                            //escreve para o txt os pacientes inativos
                            resultLines6.add("Pacientes do Médico " + medicoConsulta4.getNome() + " inativos há mais de " + mesesInatividade + " meses:");

                            for (Paciente paciente : pacientesInativos) {
                                resultLines6.add("- " + paciente.getNome() + " (" + paciente.getCpf() + ")");
                            }
                            txt = readerInputs.read_int("\nGostaria de imprimir os resultados em um arquivo texto?\nSe sim digite 1, do contrário 0 para encerrar: ");
                            if (txt == 1) {
                                // Escrever o resultado em um arquivo
                                file_name = readerInputs.read_string("Type the name of your file: ");
                                FileUtil.writeLinesToFile(resultLines6,file_name + ".txt");
                                break;
                            }
                        }
                    break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void mostrarOpcoes() {
        System.out.println("Olá, digite o número correspondente à opção desejada abaixo para continuar!\n\n" +
                "0- Para encerrar o programa.\n" +
                "1- Para saber quais são todos os pacientes de um determinado médico.\n" +
                "2- Para saber quais são todas as consultas agendadas para um determinado médico em determinado período.\n" +
                "3- Para saber quais são todos os médicos que um determinado paciente já consultou ou tem consulta agendada.\n" +
                "4- Para saber quais são todas as consultas que um determinado paciente realizou com determinado médico.\n" +
                "5- Para saber quais são todas as consultas agendadas que um determinado paciente possui.\n" +
                "6- Para saber quais são os pacientes de um determinado médico que não o consulta há mais que um determinado tempo (em meses).\n");
    }
}