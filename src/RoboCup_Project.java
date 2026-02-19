import java.util.Scanner;

public class RoboCup_Project {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite a quantidade de equipes: ");

       //variáveis
        int qtdEquipes = entrada.nextInt();
        int[] numeroEquipe = new int[qtdEquipes];
        double[] notaDesign = new double[qtdEquipes];
        int[] qtdCombates = new int[qtdEquipes];
        int[] pontos = new int[qtdEquipes];
        int numero = 0, maiorNum, maiorPontos;
        double nota = 0, maiorNota;
        char resultado = ' ';



        //primeiro for
        for (int i = 0; i < qtdEquipes; i++) {
            boolean numeroInvalido = true;
            //coletar o número da equipe
            while (numeroInvalido) {
                System.out.println();
                System.out.print("Digite o número da equipe " + (i + 1) + " (10 a 99): ");
                numero = entrada.nextInt();
                numeroInvalido = false;

                // verificar se o número é válido
                if (numero < 10 || numero > 99) {
                    System.out.println("Número inválido! Digite entre 10 e 99.");
                    numeroInvalido = true;
                }

                // verificar número já cadastrado
                for (int j = 0; j < i; j++) {
                    if (numeroEquipe[j] == numero) {
                        System.out.println("Número já cadastrado, escolha outro!");
                        numeroInvalido = true;
                        break;
                    }
                }
            }// fim while número da equipe

            numeroEquipe[i] = numero;

            // coletar a nota de design
            boolean notaInvalida = true;
            while (notaInvalida) {
                System.out.print("Digite a nota de design da equipe " + numeroEquipe[i] + " (0 a 10): ");
                nota = entrada.nextDouble();
                if (nota >= 0 && nota <= 10) {
                    notaInvalida = false;
                } else {
                    System.out.println("Nota inválida! A nota deve ser entre 0 e 10.");
                }
            }// fim while nota

            notaDesign[i] = nota;

            System.out.print("Digite a quantidade de combates da equipe " + numeroEquipe[i] + ": ");
            qtdCombates[i] = entrada.nextInt();

            System.out.println("Informe os resultados dos combates (V=Vitória, E=Empate, D=Derrota):");
            pontos[i] = 0;

            //for combates
            for (int c = 0; c < qtdCombates[i]; c++) {
                boolean resultadoInvalido = true;

                // verificar resultado
                while (resultadoInvalido) {
                    System.out.print("Combate " + (c + 1) + ": ");
                    resultado = entrada.next().toUpperCase().charAt(0);
                    if (resultado == 'V' || resultado == 'E' || resultado == 'D') {
                        resultadoInvalido = false;
                    } else {
                        System.out.println("Entrada inválida! Use apenas V, E ou D.");
                    }
                }

                // soma de pontos
                if (resultado == 'V') {
                    pontos[i] += 7;
                } else if (resultado == 'E') {
                    pontos[i] += 4;
                }
            }// fim for combates
        }// fim primeiro for

        // maior ranking final
        for (int i = 0; i < qtdEquipes - 1; i++) {
            for (int j = i + 1; j < qtdEquipes; j++) {
                if (pontos[j] > pontos[i] || (pontos[j] == pontos[i] && notaDesign[j] > notaDesign[i])) {

                    maiorNum = numeroEquipe[i];
                    numeroEquipe[i] = numeroEquipe[j];
                    numeroEquipe[j] = maiorNum;

                    maiorNota = notaDesign[i];
                    notaDesign[i] = notaDesign[j];
                    notaDesign[j] = maiorNota;

                    maiorPontos = pontos[i];
                    pontos[i] = pontos[j];
                    pontos[j] = maiorPontos;
                }
            }// fim for2 ranking
        }// fim for1 ranking

        // ranking final (saída)
        System.out.println();
        System.out.println("===== CLASSIFICAÇÃO FINAL =====");
        System.out.println();
        for (int i = 0; i < qtdEquipes; i++) {
            System.out.println("Posição:" + (i + 1) + "\t Equipe:" + numeroEquipe[i] + "\t Pontos:" + pontos[i]
                    + "\t Nota de Design:" + notaDesign[i]);
        }

    }
}