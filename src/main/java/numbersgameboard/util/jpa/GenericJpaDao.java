package util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Generic JPA DAO class that provides JPA support for the entity class
 * specified.
 *
 * @param <T> the type of the entity class
 */
public abstract class GenericJpaDao<T> {

    protected Class<T> entityClass;
    protected EntityManager entityManager;

    /**
     * Constructs a {@code GenericJpaDao} object.
     *
     * @param entityClass the {@link Class} object that represents the entity
     *                    class
     */
    public GenericJpaDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Returns the underlying {@link EntityManager} instance.
     *
     * @return the underlying {@link EntityManager} instance
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Sets the underlying {@link EntityManager} instance.
     *
     * @param entityManager the underlying {@link EntityManager} instance
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists the specified entity instance in the database.
     *
     * @param entity the entity instance to be persisted in the database
     */
    public void persist(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * Returns the entity instance with the specified primary key from the
     * database. The method returns an empty {@link Optional} object when
     * the instance does not exists.
     *
     * @param primaryKey the primary key to look for
     * @return an {@link Optional} object wrapping the entity instance with
     * the specified primary key
     */
    public Optional<T> find(Object primaryKey) {
        return Optional.ofNullable(entityManager.find(entityClass, primaryKey));
    }

    /**
     * Returns the list of all instances of the entity class from the database.
     *
     * @return the list of all instances of the entity class from the database
     */
    public List<T> findAll() {
        TypedQuery<T> typedQuery = entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
        return typedQuery.getResultList();
    }

    /**
     * Removes the specified entity instance from the database.
     *
     * @param entity the entity instance to be removed from the database
     */
    public void remove(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * Updates the specified entity instance in the database.
     *
     * @param entity the entity instance to be updated in the database
     */
    public void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

}
