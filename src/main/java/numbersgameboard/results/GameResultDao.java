package numbersgameboard.results;

import util.jpa.GenericJpaDao;

import javax.persistence.Persistence;
import java.util.List;

/**
 * DAO class for the {@link GameResultGAL7RQ} entity.
 */
public class GameResultDao extends GenericJpaDao<GameResultGAL7RQ> {

    private static GameResultDao instance;

    private GameResultDao() {
        super(GameResultGAL7RQ.class);
    }

    public static GameResultDao getInstance() {
        if (instance == null) {
            instance = new GameResultDao();
            instance.setEntityManager(Persistence.createEntityManagerFactory("jpa-persistence-unit-1").createEntityManager());
        }
        return instance;
    }

    /**
     * Returns the list of {@code n} best results with respect to the time
     * spent for solving the puzzle.
     *
     * @param n the maximum number of results to be returned
     * @return the list of {@code n} best results with respect to the time
     * spent for solving the puzzle
     */
    public List<GameResultGAL7RQ> findBest(int n) {
        return entityManager.createQuery("SELECT r FROM GameResultGAL7RQ r WHERE r.solved = true ORDER BY r.duration ASC, r.created DESC", GameResultGAL7RQ.class)
                .setMaxResults(n)
                .getResultList();
    }

}
