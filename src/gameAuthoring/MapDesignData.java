package gameAuthoring;

public class MapDesignData {

    private boolean[][] myMap;
    private String myMapBGImage;
    private String myPathImage;

    public MapDesignData () {
        // TODO Auto-generated constructor stub
    }

    public void initializeMap (int width, int height) {
        myMap = new boolean[width][height];
    }

    public void setMapData (int x, int y, boolean isPath) {
        myMap[x][y] = isPath;
    }

    public boolean[][] getMap () {
        return myMap;
    }

    public void setMapBGImage (String mapBGImage) {
        myMapBGImage = mapBGImage;
    }

    public String getMapBGImage () {
        return myMapBGImage;
    }

    public void setPathImage (String pathImage) {
        myPathImage = pathImage;
    }

    public String getPathImage () {
        return myPathImage;
    }
}
