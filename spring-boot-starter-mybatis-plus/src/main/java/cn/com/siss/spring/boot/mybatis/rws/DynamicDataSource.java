package cn.com.siss.spring.boot.mybatis.rws;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Setter
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Object readDataSource; //读数据源
    private Object writeDataSource; //写数据源

    public DynamicDataSource(Object readDataSource, Object writeDataSource) {
        this.readDataSource = readDataSource;
        this.writeDataSource = writeDataSource;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.writeDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        setDefaultTargetDataSource(writeDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceOptions.WRITE.name(), writeDataSource);
        if(readDataSource != null) {
            targetDataSources.put(DynamicDataSourceOptions.READ.name(), readDataSource);
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String lookupKey = DynamicDataSourceOptions.WRITE.name(); // default

        DynamicDataSourceOptions dynamicDataSourceOptions = DynamicDataSourceHolder.getDataSource();

        if(null != dynamicDataSourceOptions
                && dynamicDataSourceOptions == DynamicDataSourceOptions.READ) {
            lookupKey = DynamicDataSourceOptions.READ.name();
        }
        log.info("determined [{}] Strategy", lookupKey);
        return lookupKey;
    }
}
