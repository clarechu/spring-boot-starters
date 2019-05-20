package cn.com.siss.spring.boot.util.other;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanComparableUtil{

    /**
     * @校验两个对象的某些属性的值是否有变动
     * @param originObj 原有对象
     * @param currentObj 当前修改对象
     * @param validationProperties 需要校验的属性，格式-----属性1:描述名称1,属性2：描述名称2..........
     * @return 返回两个对象有变动的属性
     * @throws Exception
     */
    public static String getChangeInfo(Object originObj,Object currentObj,String validationProperties) throws Exception{
        String[] properties=validationProperties.split(",");
        Map<String,String> propertyMap=new HashMap<>();
        for (int i = 0; i < properties.length; i++) {
            String[] propertyInfo=properties[i].split(":");
            propertyMap.put(propertyInfo[0],propertyInfo[1]);
        }
        Map<String,Object> originMap=getFieldValueMapByObject(originObj,propertyMap);
        Map<String,Object> currentMap=getFieldValueMapByObject(currentObj,propertyMap);
        StringBuilder sb=new StringBuilder();
        for (Map.Entry<String,Object> entry:originMap.entrySet()){
            String key=entry.getKey();
            Object originValue=entry.getValue();
            Object currentValue=currentMap.get(key);
            if (originValue==null && currentValue==null){
                continue;
            }else if (originValue!=null && currentValue!=null){
                if (!originValue.equals(currentValue)){
                /*if (originValue instanceof Short){
                    sb.append(propertyMap.get(key)).append("改动前：").append(originValue).append("改动后").append(currentValue).append(";");
                }
                if (originValue instanceof String){
                    sb.append(propertyMap.get(key)).append("改动前：").append(originValue).append("改动后").append(currentValue).append(";");
                }
                if (originValue instanceof Integer){
                    sb.append(propertyMap.get(key)).append("改动前：").append(originValue).append("改动后").append(currentValue).append(";");
                }
                if (originValue instanceof Long){
                    sb.append(propertyMap.get(key)).append("改动前：").append(originValue).append("改动后").append(currentValue).append(";");
                }*/
                    sb.append(propertyMap.get(key)).append(";");
                }
                continue;
            }else {
                sb.append(propertyMap.get(key)).append(";");
            }

        }
        String returnStr=sb.toString();
        if (StringUtil.isEmpty(returnStr)){
            return null;
        }
        return returnStr;
    }

    private static Map<String,Object> getFieldValueMapByObject (Object object,Map<String,String> fieldMap) throws Exception {
        Map<String,Object> map=new HashMap<>();
        Class objClass = object.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field:fields) {
            String currentFieldName = field.getName();
            field.setAccessible(true);
            if(fieldMap.containsKey(currentFieldName)){
                map.put(currentFieldName,field.get(object));
            }
        }
        return map;
    }

}
