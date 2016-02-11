package app.consumity.Getter_Setter;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ameba on 1/6/16.
 */
public class Expnd_List_Product_Item
{
    ArrayMap<String, String>            header = new ArrayMap<>();
    ArrayList<ArrayMap<String, String>> child  = new ArrayList<>();

    public Expnd_List_Product_Item(ArrayMap<String, String> header, ArrayList<ArrayMap<String, String>> child)
    {
        this.header = header;
        this.child = child;
    }

    public ArrayMap<String, String> getHeader()
    {
        return header;
    }

    public ArrayList<ArrayMap<String, String>> getChild()
    {
        return child;
    }
}
