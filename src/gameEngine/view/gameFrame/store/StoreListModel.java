package gameEngine.view.gameFrame.store;

import javax.swing.DefaultListModel;

public class StoreListModel extends DefaultListModel {
    
    
    public void addItem(String display,String content){
        InfoPanelItems item = new InfoPanelItems(display,content);
        
        this.addElement(item);
    }
    
    public void addItem(String display,Double content){
        InfoPanelItems item = new InfoPanelItems(display,Double.toString(content));
        
        this.addElement(item);
    }
    
    public void addItem(String display, int content){
        InfoPanelItems item = new InfoPanelItems(display,Integer.toString(content));
        
        this.addElement(item);
    }


}
