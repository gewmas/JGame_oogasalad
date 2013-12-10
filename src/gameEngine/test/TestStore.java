package gameEngine.test;

import static org.junit.Assert.*;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.gameFrame.tools.store.StoreButtonAction;
import gameEngine.view.gameFrame.tools.store.StoreItemButton;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestStore {
    StoreItemButton button;
    @BeforeClass
    public static void setUpBeforeClass () throws Exception {
        
    }

    @Before
    public void setUp () throws Exception {
        PurchaseInfo purcahseInfo = new PurchaseInfo("Tower", "Shooter", "src/gameEngine/test/block.png", "", 40);
        button = new StoreItemButton("src/gameEngine/test/block.png",
                                                     purcahseInfo,
                                                     null, null, null);
    }

    @Test
    public void test () {
       
        assertTrue(button.toggleButtonActivation(50));
        assertFalse(button.toggleButtonActivation(10));
        PurchaseInfo purcahseInfo = new PurchaseInfo("Tower", "Shooter", "src/gameEngine/test/block.png", "", 200);
        button = new StoreItemButton("src/gameEngine/test/block.png",
                                     purcahseInfo,
                                     null, null, null);
        assertFalse(button.toggleButtonActivation(50));
        assertTrue(button.toggleButtonActivation(1000));
    }

}
