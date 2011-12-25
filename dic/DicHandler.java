package sheep.dic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DicHandler {
	private static ArrayList<Group> ds = null;
	private static HashMap<String, Group> dsMap = null;

	private DicHandler() {
	}

    private static ArrayList<Group> getDs(){
        if(ds == null){
            init();
        }
        return ds;
    }

    private static HashMap<String, Group> getDsMap(){
        if(dsMap == null){
            init();
        }
        return dsMap;
    }

    // 获取所有组
    public static List<Group> getAllGroup(){
    	return getDs();
    }
    
    // 获取组对象
	public static Group getGroup(String gName) {
		return getDsMap().get(gName);
	}
	
	// 通过groupName和key查找Name
	public static String getNameByID(String gName, int key) {
        Group g = getDsMap().get(gName);
        return g == null? null:g.getItemMap().get(key);
	}

	// 通过对象和key查找Name
	public static String getNameByID(Object o, int id) {
		return getNameByID(o.getClass().getSimpleName(), id);
	}
    
	// 扫描目录读取字典文件
	private static void init() {
        ds = new ArrayList<Group>();
        dsMap = new HashMap<String, Group>();

        // 读取配置文件，获得需要扫描的常量的位置
        //String directory = ConfigUtil.getValueByKey("core.dic.scanner");
        String directory = "com.yige.dic";

        System.out.println("扫描:" + directory);

        if (directory != null) {

            ClassPathScanHandler cpsh = new ClassPathScanHandler();
            Set<Class<?>> calssList = cpsh.getPackageAllClasses(directory,
                    true);

            Iterator<Class<?>> it = calssList.iterator();

            // 每个类对应于一个group，写入数据结构
            while (it.hasNext()) {
                Group g = new Group();
                HashMap<Integer, String> items = new HashMap<Integer, String>();
                List<Item> itemList = new ArrayList<Item>();
                Class<?> c = it.next();

                // 设置group描述和groupName
                DicGroup ca = c.getAnnotation(DicGroup.class);
                if (ca != null) {
                    if (ca.value() != null) {
                        g.setDescription(ca.value());
                    } else {
                        System.out.println("常量表中没有字典组的描述,请规范该数据字典");
                        g.setDescription("");
                    }
                    g.setGroupName(c.getSimpleName());
                    // 设置所有此group下的字典
                    Field[] fields = c.getDeclaredFields();

                    for (Field f : fields) {
                        if (f.toString().toLowerCase()
                                .indexOf("static final") > 1) {
                            Item item = new Item();

                            DicItem fa = f.getAnnotation(DicItem.class);
                            if (fa != null) {
                                try {
                                    if (f.get(c) != null) {
                                        int key = Integer.parseInt(f.get(c).toString());
                                        if (fa.value() != null) {
                                            String val = fa.value();
                                            int ext = fa.ext();
                                            items.put(key, val);
                                            item.setKey(key);
                                            item.setValue(val);
                                            item.setExt(ext);
                                            itemList.add(item);
                                        }
                                        else{
                                            System.out.println("常量表中没有字典项的定义，请规范该数据字典");
                                        }
                                    } else {
                                        System.out.println("常量表中没有字典项的定义，请规范该数据字典");
                                    }
                                } catch (Exception e) {
                                    System.out.println("常量表中没有字典项值的定义，请规范该数据字典");
                                }
                            } else {
                                System.out.println("常量表中没有字典项的定义，请规范该数据字典");
                            }
                        }
                    }
                    dsMap.put(c.getSimpleName(), g);
                    g.setItemMap(items);
                    g.setItemList(itemList);
                    ds.add(g);
                } else {
                    System.out.println("常量表中没有字典组的注释定义，请规范该数据字典");
                }
            }
        }
	}    
}
