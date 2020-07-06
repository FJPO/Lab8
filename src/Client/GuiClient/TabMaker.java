package Client.GuiClient;

import javafx.scene.Parent;


public abstract class TabMaker {
    private static final TabMaker[] makers = new TabMaker[3];
    static {
        makers[0] = new TableScene();
        makers[1] = new DiagramScene();
        makers[2] = new CommandScene();
    }

    public abstract String getName();
    protected abstract Parent getLayout();
    protected static TabMaker getMaker(String tag){
        for(TabMaker tab : makers) if(tab.getName().equals(tag)) return tab;
        throw new IllegalArgumentException();
    }
    protected static TabMaker[] getMakers(){
        return makers;
    }
}
