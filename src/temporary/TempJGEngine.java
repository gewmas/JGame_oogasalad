package temporary;

import temporary.buff.Buff;
import temporary.buff.SlowBuff;
import gameEngine.model.Rule;
import gameEngine.model.Wave;
import jgame.JGPoint;
import jgame.platform.JGEngine;


/**
 * @author Jiaran
 *         for test .
 */
public class TempJGEngine extends JGEngine {

    @Override
    public void initCanvas () {
        setCanvasSettings(16, 43, 60, 16, null, null, null);

    }

    @Override
    public void initGame () {
        setFrameRate(35, 2);
        defineMedia("media.tbl");
        setGameState("InGame");
    }

    public static void main (String[] args) {
        new TempJGEngine(new JGPoint(960, 700));
    }

    /** Application constructor. */
    public TempJGEngine (JGPoint size) {
        initEngine(size.x, size.y);
    }

    public void startInGame () {

        setPFSize(3 * 16, 43);
        Rule r = new Rule();
        // r.addWave(new Wave(null, 5, 1000));
        // r.addWave(new Wave(null, 5, 1000));
        r.ruleStart();
        new TempAOE(null, false, 400, 400, 0, null);

    }

    public void paintFrameInGame () {

    }

    public void doFrameInGame () {
        this.moveObjects();

    }
}
