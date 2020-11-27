package model;

/**
 * Defines behaviour that all living entities must support  - living entities must be able to
 * move in different directions
 * living entities must be able to support base entity behavior
 */
public interface LivingEntity extends Entity {


    // EFFECTS: moves entity in a direction based on a criteria given the subclass's implementation (with a type int)
    void move(int directionCriteria);

    // EFFECTS: returns true if the entity collided with the entity passed in as a parameter
    boolean collidedWith(Entity e);

}
