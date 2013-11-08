package gameEngine.view;

public class CanvasPanel extends Panel implements Colleague {
    Game game;

    public CanvasPanel () {
        game = new Game();
        this.add(game);
    }

    @Override
    public void addMediator (Mediator mediator) {
        // TODO Auto-generated method stub

    }

    @Override
    public void purchaseTower (String tower) {
        game.purchaseTower(tower);

    }

}
