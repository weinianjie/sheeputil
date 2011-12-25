package sheep.dic;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: weinianjie
 * Date: 2011-9-13
 * Time: 17:30:33
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseDic {
    private static Group _group = null;

    private Group getGroup(){
        if(_group == null){
            _group = DicHandler.getGroup(this.getClass().getSimpleName());
        }
        return _group;
    }

    public String getGroupName(){
        return getGroup().getGroupName();
    }

    public String getDescription(){
        return getGroup().getDescription();
    }

    public String getVal(int key){
        return getGroup().getItemMap(key);
    }

    public int getExt(int key){
        return getItem(key).getExt();
    }

    public Item getItem(int key){
        for(Item i:getItemList()){
            if(i.getKey() == key){
                return i;
            }
        }
        return null;
    }

    public List<Item> getItemList(){
        return getGroup().getItemList();
    }
}
