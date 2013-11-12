package gameEngine.view.gameFrame;

/**
 * Keys of each colleague used by the Mediator class
 * If a new colleague is added, a new key should be added
 * 
 * @author Lalita Maraj
 * 
 */
public enum ColleagueKeys {
 
    INFOPANEL("info");
    private String key;

    ColleagueKeys (String key) {
        this.key = key;
    }

    public String toString () {
        return key;
    }

}
