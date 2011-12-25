package sheep.dic;

import java.util.HashMap;
import java.util.List;

public class Group {
	private String groupName;
	private String description;
	private HashMap<Integer,String> itemMap;
	private List<Item> itemList;

    public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public HashMap<Integer, String> getItemMap() {
        return itemMap;
    }
    public String getItemMap(int key) {
        return itemMap.get(key);
    }
    public void setItemMap(HashMap<Integer, String> itemMap) {
        this.itemMap = itemMap;
    }
    public List<Item> getItemList() {
        return itemList;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}



