package model;

/**
 * Marks subclasses as an entity that can be passed into collision detection methods.  It must have a position
 * In the future collidedWith may be a necessary method for all entities (not just living entities) but for now
 * there is nothing else for bullet to collide with (and bullet is the only class currently implementing entity).
 */
public interface Entity {

    // EFFECTS: returns position of entity
    int getPosX();

}
