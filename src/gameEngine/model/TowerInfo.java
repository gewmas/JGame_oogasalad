package gameEngine.model;

/*
 * wenxin shi
 */
public class TowerInfo {
    private String myImage;
    private int myCost;
    private String myName;
    private String myDescription;

    public TowerInfo (String image, int cost, String name, String description) {
        myImage = image;
        myCost = cost;
        myName = name;
        myDescription = description;
    }

    public String getImage () {
        return myImage;
    }

    public int getCost () {
        return myCost;
    }

    public String getName () {
        return myName;
    }

    public String getDescription () {
        return myDescription;
    }

}
