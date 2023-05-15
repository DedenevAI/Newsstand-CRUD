package com.alexproject.sql;

import com.alexproject.exception.DaoException;
import com.alexproject.exception.ExistDaoException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static DaoException convertException(SQLException e) {
        if (e instanceof PSQLException) {

//            http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) {
                return new ExistDaoException(null);
            }
        }
        return new DaoException(e);
    }
}
