package com.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author tanyaofei
 * @since 2024/4/23
 **/
@Component
public class SQLHelper {

    private final JdbcTemplate jdbc;

    public SQLHelper(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void setInnodbLockWaitTimeout(long seconds) {
        jdbc.update("set innodb_lock_wait_timeout = " + seconds);
    }


}
