package com.alexproject.model;

import com.alexproject.dao.DaoSql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.alexproject.model.PublicationType.*;

public class PublicationClassFactory {
    public Publication createPublicationFromResultSet(ResultSet rs) throws SQLException {
        PublicationType type = PublicationType.valueOf(rs.getString("type"));
        Publication publication = null;
        switch (type) {
            case BOOK:
                publication = new Book(rs.getLong("id"), rs.getString("name"),
                        rs.getString("author"));
                break;
            case MAGAZINE:
                publication = new Magazine(rs.getLong("id"), rs.getString("name"),
                        rs.getString("publishingHouse"));
                break;
            case NEWSPAPER:
                publication = new Newspaper(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("issue"));
                break;
        }
        return publication;
    }
}
