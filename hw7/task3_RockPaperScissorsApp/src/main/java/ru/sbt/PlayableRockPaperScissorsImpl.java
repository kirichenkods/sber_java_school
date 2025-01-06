package ru.sbt;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class PlayableRockPaperScissorsImpl implements PlayableRockPaperScissors {
    private int countWins;
    private String name;
    @Override
    /**
     * Рандомно выбрасывает один из вариантов
     */
    public RockPaperScissorsEnum play() {
        Random random = new Random();
        int randomNumber = random.nextInt(RockPaperScissorsEnum.values().length);
        return RockPaperScissorsEnum.values()[randomNumber];
    }
}