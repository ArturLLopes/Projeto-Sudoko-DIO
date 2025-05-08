import model.Board;
import model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    private static Board board;

    private final static int BOARD_LIMIT = 9;


    public static void main(String[]args){
        final var positions = Stream.of(args)
                .collect(Collectors.toMap(
                        k -> k.split(";")[0], // chave: parte antes do ';'
                        v -> v.split(";")[1]  // valor: parte depois do ';'
                //Transforma os argumentos passados pela linha de comando (args) em um mapa (Map<String, String>),
                // onde a chave e o valor de cada entrada são separados por ponto e vírgula (;).
                ));
        var option = -1;
        while (true){
            System.out.println("""
                \nMENU
                1. Iniciar novo jogo
                2. Colocar número
                3. Remover número
                4. Verificar jogo
                5. Verificar status
                6. Limpar
                7. Finalizar jogo
                8. Sair
                """);
            option = scanner.nextInt();

            switch (option) {
                case 1 ->startGame(positions);
                case 2 ->inputNumber();
                case 3 ->removeNumber();
                case 4 ->showCurrentGame();
                case 5 ->showGameStatus();
                case 6 ->clearGame();
                case 7 ->finishGame();
                case 8 ->System.exit(8);
                default -> System.out.println("Opção inválida.");


            }
        }
    }

    private static void finishGame() {
    }

    private static void clearGame() {
    }

    private static void showGameStatus() {
    }

    private static void showCurrentGame() {
    }

    private static void removeNumber() {if (isNull(board)){
        System.out.println("O jogo ainda não foi iniciado iniciado");
        return;
    }

        System.out.println("Informe a coluna que em que o número será inserido");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que em que o número será inserido");
        var row = runUntilGetValidNumber(0, 8);
        if (!board.clearValue(col, row)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }

    }

    private static void inputNumber() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado iniciado");
            return;
        }

        System.out.println("Informe a coluna que em que o número será inserido");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que em que o número será inserido");
        var row = runUntilGetValidNumber(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%s,%s]\n", col, row);
        var value = runUntilGetValidNumber(1, 9);
        if (!board.chargeValue(col, row, value)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }

    }

    private static int runUntilGetValidNumber(int min, int max) {
        var current = scanner.nextInt();
        while (current < min || current > max){
            System.out.printf("Informe um número entre %s e %s\n", min, max);
            current = scanner.nextInt();
        }
        return current;

    }

    private static void startGame(Map<String, String> positions) {
        if(nonNull(board)){
            System.out.println("O jogo ja foi iniciado");
            return;
        }
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = positions.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar");
    }

}
