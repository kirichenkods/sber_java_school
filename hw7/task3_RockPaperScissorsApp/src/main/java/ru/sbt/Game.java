package ru.sbt;

import java.util.List;

public class Game {
    private static final int COUNT_ROUNDS = 3;

    public static void main(String[] args) {
        String pathName = ".\\hw7\\task3_RockPaperScissorsApp\\src\\plugins";
        PluginLoader loader = new PluginLoader(pathName);
        List<PlayableRockPaperScissorsImpl> players = loader.getPlayersFromPath(pathName);

        if (!players.isEmpty()) {
            PlayableRockPaperScissorsImpl player1 = players.get(0);
            PlayableRockPaperScissorsImpl player2;
            // Изначально player1 - первый плагин, в дальнейшем
            // в переменную player1 записывается победитель, в player2 - следующий по очереди
            for (int i = 1; i < players.size(); i++) {
                player2 = players.get(i);
                competitionBetweenPlayers(player1, player2);
                if (player1.getCountWins() < player2.getCountWins()) {
                    player1 = player2;
                }
                player1.setCountWins(0);
            }
            System.out.println("В турнире победу одержал " + player1.getName());
        }
    }

    /**
     * Проводит COUNT_ROUNDS раундов между двумя игроками
     * выводит в консоль результат поединка
     */
    private static void competitionBetweenPlayers(PlayableRockPaperScissorsImpl player1,
                                                  PlayableRockPaperScissorsImpl player2) {
        System.out.println("играют " + player1.getName() + " и " + player2.getName());

        for (int j = 0; j < COUNT_ROUNDS; j++) {
            round(player1, player2);
        }
        System.out.println("В поединке со счетом " +
                player1.getCountWins() + " : " + player2.getCountWins() +
                " победил: " +
                (player1.getCountWins() > player2.getCountWins() ?
                        player1.getName() :
                        player2.getName()));
    }

    /**
     * Проводит один раунд между игроками, записывает победу победителю
     * В случае, если игроки показали одинаковые фигуры, вызывается заново.
     * Выводит в консоль результат раунда
     */
    private static void round(PlayableRockPaperScissorsImpl player1,
                              PlayableRockPaperScissorsImpl player2) {
        RockPaperScissorsEnum rpsPlayer1 = player1.play();
        RockPaperScissorsEnum rpsPlayer2 = player2.play();

        System.out.print(player1.getName() + " показал " + rpsPlayer1 + " | ");
        System.out.println(player2.getName() + " показал " + rpsPlayer2);

        if (rpsPlayer1 == rpsPlayer2) {
            System.out.println("игроки показали одинаковые фигуры, повтор");
            round(player1, player2);
            return;
        }

        if (isPlayer1Win(rpsPlayer1, rpsPlayer2)) {
            System.out.println(player1.getName() + " win");
            player1.setCountWins(player1.getCountWins() + 1);
        } else {
            System.out.println(player2.getName() + " win");
            player2.setCountWins(player2.getCountWins() + 1);
        }
    }

    /**
     * Определяет победителя в раунде
     * вернет true если фигура Player1 бьет фигуру Player2
     */
    private static boolean isPlayer1Win(RockPaperScissorsEnum rpsPlayer1,
                                        RockPaperScissorsEnum rpsPlayer2) {
        return (rpsPlayer1 == RockPaperScissorsEnum.Rock &&
                rpsPlayer2 == RockPaperScissorsEnum.Scissors) ||
                (rpsPlayer1 == RockPaperScissorsEnum.Scissors &&
                        rpsPlayer2 == RockPaperScissorsEnum.Paper) ||
                (rpsPlayer1 == RockPaperScissorsEnum.Paper &&
                        rpsPlayer2 == RockPaperScissorsEnum.Rock);
    }
}