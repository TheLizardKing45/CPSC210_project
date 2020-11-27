package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Gun class
 */

public class GunTest {

    Gun dg1;

    @BeforeEach
    public void runBefore() {
        dg1 = new Gun();
    }


    @Test
    public void testReloadHighReserveAmmo() {
        int initialReserve = dg1.getReserveAmmo();
        dg1.shoot();
        assertEquals(Gun.getMaxClipAmmo() - 1, dg1.getCurrentAmmo());
        dg1.reload();
        assertEquals(Gun.getMaxClipAmmo(), dg1.getCurrentAmmo());
        assertEquals(initialReserve - 1, dg1.getReserveAmmo());

        dg1.setCurrentAmmo(0);
        dg1.reload();
        assertEquals(Gun.getMaxClipAmmo(), dg1.getCurrentAmmo());

    }

    @Test
    public void testReloadLowReserveAmmo() {
        dg1.setReserveAmmo(1);
        int halfAmmo = dg1.getCurrentAmmo()/2;
        dg1.setCurrentAmmo(halfAmmo);
        dg1.reload();
        assertEquals(0, dg1.getReserveAmmo());
        assertEquals(halfAmmo+1, dg1.getCurrentAmmo());

    }

    @Test
    public void testShoot() {
        int initialCurrentAmmo = dg1.getCurrentAmmo();
        assertEquals(dg1.getCurrentAmmo(), Gun.getMaxClipAmmo());
        dg1.shoot();
        assertEquals(initialCurrentAmmo-1, dg1.getCurrentAmmo());
    }

    @Test
    public void testShootEmptyClip() {
        dg1.setCurrentAmmo(0);
        dg1.shoot();
        assertEquals(Gun.getMaxClipAmmo(), dg1.getCurrentAmmo());
    }







}
