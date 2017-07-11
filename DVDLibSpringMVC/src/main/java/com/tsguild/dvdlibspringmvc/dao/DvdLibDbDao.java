/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.dvdlibspringmvc.dao;

import com.tsguild.dvdlibspringmvc.model.DVD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class DvdLibDbDao implements DVDLibDao{
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_DVD
                = "insert into Dvd"
                + "(dvd_title, release_date, dvd_director, dvd_rating, dvd_notes) "
                + "values (?, ?, ?, ?, ?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public DVD addDVD(DVD dvd) {
        
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getDvdTitle(),
                dvd.getReleaseDate(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getExtraNotes());
        int dvdID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        dvd.setDvdId(dvdID);
        return dvd;
    }
    
    private static final String SQL_DELETE_DVD = "delete from Dvd where dvd_id = ?";
    @Override
    public void removeDVD(long dvdId) {
       jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
    }
    
    private static final String SQL_EDIT_DVD 
            = "update Dvd set "
            + "dvd_title = ?, release_date = ?, dvd_director = ?, "
            + "dvd_rating = ?, dvd_notes = ? where dvd_id = ?";
    @Override
    public void editDVD(DVD dvd) {
        jdbcTemplate.update(SQL_EDIT_DVD,
                dvd.getDvdTitle(),
                dvd.getReleaseDate(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getExtraNotes(),
                dvd.getDvdId());
    }
    
    private static final String SQL_SELECT_ALL_DVDS = "select * from Dvd";
    @Override
    public List<DVD> getAllDVDs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DvdMapper());
    }

    private static final String SQL_SELECT_DVD_BY_ID = "select * from Dvd where dvd_id = ?";
    @Override
    public DVD getDVDById(long dvdId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD_BY_ID,
                                               new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria) {
       if(criteria.isEmpty()) {
           return getAllDVDs();
       } else {
           StringBuilder sQuery = new StringBuilder("select * from Dvd where ");
           int numParams = criteria.size();
           int paramPosition = 0;
           String[] paramValues = new String[numParams];
           Set<SearchTerm> keySet = criteria.keySet();
           Iterator<SearchTerm> iter = keySet.iterator();
           while(iter.hasNext()) {
               SearchTerm currentKey = iter.next();
               if(paramPosition > 0){
                   sQuery.append(" and ");
               }
               sQuery.append(currentKey);
               sQuery.append(" = ? ");
               paramValues[paramPosition] = criteria.get(currentKey);
               paramPosition++;
           }
           return jdbcTemplate.query(sQuery.toString(),
                   new DvdMapper(),
                   paramValues);
       }
    }
    
    private static final class DvdMapper implements RowMapper<DVD> {

        @Override
        public DVD mapRow(ResultSet rs, int i) throws SQLException {
            DVD dvd = new DVD();
            dvd.setDvdId(rs.getLong("dvd_id"));
            dvd.setDvdTitle(rs.getString("dvd_title"));
            dvd.setReleaseDate(rs.getString("release_date"));
            dvd.setDirector(rs.getString("dvd_director"));
            dvd.setRating(rs.getString("dvd_rating"));
            dvd.setExtraNotes(rs.getString("dvd_notes"));
            return dvd;
        }
        
    }
}
