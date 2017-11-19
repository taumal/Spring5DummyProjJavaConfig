package com.dummies.dao;

import com.dummies.model.Dummy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author taumal
 * @since 11/14/17 05:15 PM
 */
public class DummyDAOImpl implements DummyDAO{

    private JdbcTemplate jdbcTemplate;

    public DummyDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Dummy dummy) {
        if (dummy.getId() != null && dummy.getId() > 0 ){
            String sql = "UPDATE tbl_user SET first_name = ?, last_name = ?, email = ?, " +
                    "username = ?, password = ?, country = ? WHERE uid = ?";
            jdbcTemplate.update(sql, dummy.getFirstName(), dummy.getLastName(), dummy.getEmail(),  dummy.getUserName(),
                    dummy.getPassword(), dummy.getCountry(), dummy.getId());
        } else {
            String sql = "INSERT INTO tbl_user (first_name, last_name, email, username, password, country)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, dummy.getFirstName(), dummy.getLastName(), dummy.getEmail(),  dummy.getUserName(),
                    dummy.getPassword(), dummy.getCountry());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM tbl_user WHERE uid = ? ";
        jdbcTemplate.update(sql, id);
    }

    public Dummy getDummyDataById(int id) {
        String sql = "SELECT * FROM tbl_user WHERE uid = " + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Dummy>() {
            @Nullable
            public Dummy extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()){
                    Dummy dummyObj = new Dummy();

                    dummyObj.setId(resultSet.getInt("uid"));
                    dummyObj.setFirstName(resultSet.getString("first_name"));
                    dummyObj.setLastName(resultSet.getString("last_name"));
                    dummyObj.setEmail(resultSet.getString("email"));
                    dummyObj.setUserName(resultSet.getString("username"));
                    dummyObj.setPassword(resultSet.getString("password"));
                    dummyObj.setCountry(resultSet.getString("country"));

                    return dummyObj;
                }
                return null;
            }
        });
    }

    public List<Dummy> getAllDummyData() {
        String sql = "SELECT * FROM tbl_user";
        List<Dummy> dummyData = jdbcTemplate.query(sql, new RowMapper<Dummy>() {

            @Nullable
            public Dummy mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Dummy objDummy = new Dummy();

                objDummy.setId(resultSet.getInt("uid"));
                objDummy.setFirstName(resultSet.getString("first_name"));
                objDummy.setLastName(resultSet.getString("last_name"));
                objDummy.setEmail(resultSet.getString("email"));
                objDummy.setUserName(resultSet.getString("username"));
                objDummy.setPassword(resultSet.getString("password"));
                objDummy.setCountry(resultSet.getString("country"));

                return objDummy;
            }
        });
        return dummyData;
    }
}
