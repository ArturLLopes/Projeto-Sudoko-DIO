import model.Board;
import model.Space;
import service.ServiceMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static service.ServiceMenu.*;
import static util.BoardTemplate.BOARD_TEMPLATE;

public class Main {


    public static void main(String[]args){
        final var positions = Stream.of(args)
                .collect(Collectors.toMap(
                        k -> k.split(";")[0], // chave: parte antes do ';'
                        v -> v.split(";")[1]  // valor: parte depois do ';'
                //Transforma os argumentos passados pela linha de comando (args) em um mapa (Map<String, String>),
                // onde a chave e o valor de cada entrada são separados por ponto e vírgula (;).
                ));
        ServiceMenu menu = new ServiceMenu(positions);
       Scanner scanner = new Scanner(System.in);

        while (true) {
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

            int option = scanner.nextInt();
            switch (option) {
                case 1 -> menu.startGame();
                case 2 -> menu.inputNumber();
                case 3 -> menu.removeNumber();
                case 4 -> menu.showCurrentGame();
                case 5 -> menu.showGameStatus();
                case 6 -> menu.clearGame();
                case 7 -> menu.finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}