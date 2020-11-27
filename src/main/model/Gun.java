package model;

/**
 * Represents the player's gun
 */

public class Gun {


    private static final int MAX_CLIP_AMMO = 8;
    private static final int MAX_RESERVE_AMMO = 800;
    private int currentAmmo;
    private int reserveAmmo;


    // EFFECTS: constructs a new Gun object with a full clip of ammo
    public Gun() {
        currentAmmo = MAX_CLIP_AMMO;
        reserveAmmo = MAX_RESERVE_AMMO;
    }

    public static int getMaxClipAmmo() {
        return MAX_CLIP_AMMO;
    }

    public static int getMaxReserveAmmo() {
        return MAX_RESERVE_AMMO;
    }

    public int getCurrentAmmo() {
        return currentAmmo;
    }

    public void setCurrentAmmo(int currentAmmo) {
        this.currentAmmo = currentAmmo;
    }

    public int getReserveAmmo() {
        return reserveAmmo;
    }

    public void setReserveAmmo(int reserveAmmo) {
        this.reserveAmmo = reserveAmmo;
    }

    // REQUIRES: currentAmmo < MAX_CLIP_AMMO
    // MODIFIES: this
    // EFFECTS: if there are enough bullets in reserveAmmo, add bullets to
    //          currentAmmo such that currentAmmo is equal to MAX_CLIP_AMMO and
    //          subtracts number of bullets needed to achieve this from reserveAmmo, otherwise add all bullets from
    //          reserveAmmo to currentAmmo
    public void reload() {
        if ((MAX_CLIP_AMMO - currentAmmo) < reserveAmmo) {
            reserveAmmo -= MAX_CLIP_AMMO - currentAmmo;
            currentAmmo = MAX_CLIP_AMMO;
        } else {
            currentAmmo += reserveAmmo;
            reserveAmmo = 0;
        }
    }

    // REQUIRES: currentAmmo >= 0
    // MODIFIES: this
    // EFFECTS: subtracts 1 bullet from currentAmmo if currentAmmo is not zero, otherwise reload
    public void shoot() {
        if (currentAmmo != 0) {
            currentAmmo -= 1;
        } else {
            reload();
        }
    }
}
