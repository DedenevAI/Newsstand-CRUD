package com.alexproject.dao;

import com.alexproject.Config;
import com.alexproject.exception.NotExistDaoException;
import com.alexproject.model.*;
import com.alexproject.sql.SqlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoSql implements Dao {
    private static final Logger log = LoggerFactory.getLogger(DaoSql.class);
    public final SqlHelper sqlHelper;

    public DaoSql(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        log.info("clear");
        sqlHelper.execute("DELETE FROM printed_product");
    }

    @Override
    public void save(Publication p) {
        log.info("save");
        sqlHelper.execute("INSERT INTO printed_product(name, type, author, issue, \"publishingHouse\") VALUES (?,?,?,?,?)",
                ps -> {
                    ps.setString(1, p.getName());
                    ps.setString(2, p.getType().toString());
                    insert(ps,p);
                    boolean res = ps.execute();
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs != null && rs.next()) {
                        int id = rs.getInt(1);
                        p.setId((long) id);
                    }
                    return res;
                });
        log.info(p.toString());
    }

    @Override
    public void update(Publication p) {
        log.info("update {}", p.getId());
        sqlHelper.execute("UPDATE printed_product SET name=?, type=?, author=?, issue=?, \"publishingHouse\"=? WHERE id=?",
                ps -> {
                    ps.setString(1, p.getName());
                    ps.setString(2, p.getType().toString());
                    insert(ps,p);
                    ps.setInt(6, p.getId().intValue());
                    return ps.execute();
                });
    }

    private Publication read(ResultSet rs) throws SQLException {
        PublicationClassFactory publicationClassFactory = new PublicationClassFactory();
        return publicationClassFactory.createPublicationFromResultSet(rs);
    }

    @Override
    public Publication get(Long id) {
        log.info("get {}", id);
        return sqlHelper.execute("SELECT * FROM printed_product WHERE id = ?", ps -> {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistDaoException(id.toString());
            }
            Publication publication = read(rs);
            log.info((publication != null) ? publication.toString() : "unknown type");
            return publication;
        });
    }

    @Override
    public void delete(Long id) {
        log.info("delete {}", id);
        sqlHelper.execute("DELETE FROM printed_product WHERE id = ?", ps -> {
            ps.setLong(1, id);
            return ps.execute();
        });
    }

    @Override
    public List<Publication> getAll() {
        log.info("getAll");
        List<Publication> res = sqlHelper.execute("SELECT * FROM printed_product ORDER BY type, id", ps -> {
            List<Publication> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(read(rs));
            }
            return list;
        });
        log.info("readed " + res.size());
        return res;
    }

    @Override
    public int size() {
        log.info("size");
        return sqlHelper.execute("SELECT COUNT(*) FROM printed_product", ps -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt(1);
        });
    }
    private void insert(PreparedStatement ps, Publication p) throws SQLException {
        switch (p.getType()) {
            case BOOK:
                ps.setString(3, ((Book) p).getAuthor());
                ps.setNull(4, Types.INTEGER);
                ps.setString(5, null);
                break;
            case MAGAZINE:
                ps.setString(3, null);
                ps.setNull(4, Types.INTEGER);
                ps.setString(5, ((Magazine) p).getPublishingHouse());
                break;
            case NEWSPAPER:
                ps.setString(3, null);
                ps.setObject(4, ((Newspaper) p).getIssueNumber());
                ps.setString(5, null);
                break;
        }
    }

    //hard
    public static void main(String[] args) {
        Dao sql = Config.getDao();

        sql.clear();

        Book book = new Book("book", "author");
        Magazine magazine = new Magazine("magazine", "house");
        Newspaper newspaper = new Newspaper("newspaper", 123);
        sql.save(book);
        sql.save(magazine);
        sql.save(newspaper);

        book.setAuthor("author new");
        magazine.setPublishingHouse("house new");
        newspaper.setIssueNumber(321);
        sql.update(book);
        sql.update(magazine);
        sql.update(newspaper);

        Book book2 = (Book) sql.get(book.getId());
        Magazine magazine2 = (Magazine) sql.get(magazine.getId());
        Newspaper newspaper2 = (Newspaper) sql.get(newspaper.getId());

        System.out.println("size = " + sql.size());

        List<Publication> list = sql.getAll();
        for (Publication publication : list) {
            System.out.println(publication);
        }

        //sql.delete(book2.getId());
        //sql.delete(magazine2.getId());
        //sql.delete(newspaper2.getId());

    }
}
