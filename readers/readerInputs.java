package readers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class readerInputs{
    private static Scanner scanner = new Scanner(System.in);

    public static String read_string(String prompt) {
        while (true) {
            try {
                scanner.nextLine(); // Limpar qualquer entrada anterior
                System.out.print(prompt);
                String value = scanner.nextLine().trim(); // Ler a linha de entrada e remover espaços extras
    
                // Verificar se a entrada não está vazia
                if (!value.isEmpty()) {
                    return value.toUpperCase(); // Retornar a string convertida para letras maiúsculas
                } else {
                    System.out.println("Entrada inválida. Por favor, insira uma string não vazia.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira uma string válida.");
                scanner.next(); // Limpar o buffer do Scanner
            }
        }
        
    }
    public static int read_int(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro válido.");
                scanner.next(); // Limpar o buffer do Scanner
            }
        }
    }
}