package numbersgameboard.results;


import java.time.Duration;

public class GameResultExample {

    public static void main(String[] args) {
        GameResultDao gameResultDao = GameResultDao.getInstance();
        GameResultGAL7RQ gameResultGAL7RQ = GameResultGAL7RQ.builder()
                .player("erol")
                .solved(true)
                .steps(36)
                .duration(Duration.ofMinutes(3))
                .build();
        gameResultDao.persist(gameResultGAL7RQ);
        System.out.println(gameResultGAL7RQ);
        System.out.println(gameResultDao.findBest(5));
    }

}
