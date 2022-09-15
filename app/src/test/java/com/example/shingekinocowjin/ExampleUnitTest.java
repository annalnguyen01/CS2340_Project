package com.example.shingekinocowjin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    TestClass testClass;


    @Test
    public void addition_isCorrect() {

        testClass = new TestClass();
        assertEquals(4, 2 + 2);
    }

    //monument health = 0 when gameover screen pop ups
    @Test
    public void checkGameOverHealth() {
        testClass = new TestClass();

        assertEquals(0, testClass.getGameOverHealth());
    }

    //difficulty variable = 1 when gamemode = easy

    @Test
    public void checkEasyDifficulty() {
        testClass = new TestClass();

        assertEquals(1 , testClass.getDifficulty1());
    }

    //difficulty variable = 2 when gamemode = medium

    @Test
    public void checkMediumDifficulty() {
        testClass = new TestClass();

        assertEquals(2, testClass.getDifficulty2());
    }

    //difficulty variable = 3 when gamemode = hard

    @Test
    public void checkHardDifficulty() {
        testClass = new TestClass();

        assertEquals(3, testClass.getDifficulty3());
    }

    //cow price = 1 when gamemode = easy

    @Test
    public void EasyCowPrice() {
        testClass = new TestClass();

        assertEquals(1, testClass.getCowPriceEasy());
    }

    //cow price = 3 when gamemode = medium

    @Test
    public void MediumCowPrice() {
        testClass = new TestClass();

        assertEquals(3, testClass.getCowPriceMedium());
    }

    //cow price = 5 when gamemode = hard

    @Test
    public void HardCowPrice() {
        testClass = new TestClass();

        assertEquals(5, testClass.getCowPriceHard());
    }

    //money is set to 3 when gamemode = easy

    @Test
    public void checkEasyMoney() {
        testClass = new TestClass();

        assertEquals(3, testClass.getEasyMoney());
    }

    //money is set to 6 when gamemode = medium

    @Test
    public void checkMediumMoney() {
        testClass = new TestClass();

        assertEquals(6, testClass.getMediumMoney());
    }

    //money is set to 9 when gamemode = hard

    @Test
    public void checkHardMoney() {
        testClass = new TestClass();

        assertEquals(9, testClass.getHardMoney());
    }

    @Test
    public void checkGameOverMoney() {
        testClass = new TestClass();

        assertEquals(0, testClass.getGameOverMoney());
    }

    @Test
    public void checkGameOverCowCost() {
        testClass = new TestClass();

        assertEquals(0, testClass.getGameOverCowCost());
    }

    @Test
    public void checkGameOverDifficulty() {
        testClass = new TestClass();

        assertEquals(0, testClass.getGameOverDifficulty());
    }


    @Test
    public void checkNumberofFarmers() {
        testClass = new TestClass();

        assertEquals(3, testClass.getNumberOfFarmers());
    }
    @Test
    public void checkisNameChosen() {
        testClass = new TestClass();

        assertEquals(false, testClass.isNameChosen());
    }
    @Test
    public void checkNameChosen() {
        testClass = new TestClass();

        assertEquals("COWMANDER", testClass.getName());
    }

// Milestone 5 JUnits

    @Test
    public void checkFarmerHealth() {
        testClass = new TestClass();

        assertEquals(1, testClass.getFarmerHealth());
    }

    @Test
    public void checkNumberEnemies() {
        testClass = new TestClass();

        assertEquals(3, testClass.getNumEnemies());
    }

    @Test
    public void checkTowerDamage() {
        testClass = new TestClass();

        assertEquals(5, testClass.getTowerDamage());
    }

    @Test
    public void checkMonumentHealth() {
        testClass = new TestClass();

        assertEquals(5, testClass.getMonumentHealth());
    }

    @Test
    public void checkBasicFarmerGold() {
        testClass = new TestClass();

        assertEquals(1, testClass.getBasicFarmerGold());
    }

    @Test
    public void checkMediumFarmerGold() {
        testClass = new TestClass();

        assertEquals(3, testClass.getMediumFarmerGold());
    }

    @Test
    public void checkFastFarmerGold() {
        testClass = new TestClass();

        assertEquals(5, testClass.getFastFarmerGold());
    }

    @Test
    public void checkRoundEndGold() {
        testClass = new TestClass();

        assertEquals(9, testClass.getRoundEndGold());
    }
}
/**
 * Test to see if full screen is properly done on the android phone.
 */