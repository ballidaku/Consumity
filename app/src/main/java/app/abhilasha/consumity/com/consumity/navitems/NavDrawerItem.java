package app.abhilasha.consumity.com.consumity.navitems;

/**
 * Created by ameba on 9/12/15.
 */
public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private int image;


    public NavDrawerItem() {

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public NavDrawerItem(boolean showNotify, String title, int image) {
        this.showNotify = showNotify;
        this.title = title;
        this.image = image;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
