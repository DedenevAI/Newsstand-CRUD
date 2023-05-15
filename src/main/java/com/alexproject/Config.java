package com.alexproject;

import com.alexproject.dao.Dao;
import com.alexproject.dao.DaoSql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File("/db.properties");
    private static final Config INSTANCE = new Config();

    private final File daoDir;
    private final Dao dao;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("/db.properties")) {
            Properties props = new Properties();
            props.load(is);
            String driver = props.getProperty("db.driver");
            Class.forName(driver);
            daoDir = new File(props.getProperty("storage.dir"));
            dao = new DaoSql(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static File getDaoDir() {
        return get().daoDir;
    }

    public static Dao getDao() {
        return get().dao;
    }
}
