package com.hasor.config;

import net.hasor.core.ApiBinder;
import net.hasor.core.DimModule;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import net.hasor.spring.SpringModule;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * @author 刘芳
 */

@DimModule  //Hasor中的标签，表明时一个Hasor中的一个Model
@Component  //Spring中的标签，表明是一个组件
public class HasorComponent implements SpringModule {

    private DataSource dataSource;

    public HasorComponent(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Hasor 启动的时候会调用 loadModule 方法，
     * 在这里再把 DataSource 设置到 Hasor 中。
     *
     * @param apiBinder
     * @throws Throwable
     */
    @Override
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        apiBinder.installModule(new JdbcModule(Level.Full, this.dataSource));
    }
}
