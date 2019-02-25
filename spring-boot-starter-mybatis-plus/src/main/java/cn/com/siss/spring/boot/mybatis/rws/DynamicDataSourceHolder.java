package cn.com.siss.spring.boot.mybatis.rws;


/**
 * 数据库源路由设置 DynamicDataSourceHolder
 * @author John Deng
 *
 */

public final class DynamicDataSourceHolder {

    private static final ThreadLocal<DynamicDataSourceOptions> holder = new ThreadLocal<DynamicDataSourceOptions>();

    private DynamicDataSourceHolder() {

    }

    public static void putDataSource(DynamicDataSourceOptions dataSource){
        holder.set(dataSource);
    }

    public static DynamicDataSourceOptions getDataSource(){
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }

}
