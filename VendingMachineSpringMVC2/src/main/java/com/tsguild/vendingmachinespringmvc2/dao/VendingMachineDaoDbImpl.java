/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.dao;

import com.tsguild.vendingmachinespringmvc2.model.Item;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoDbImpl implements VendingMachineDao{

    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_LIST_ALL_INVENTORY = "select * from Item";
    @Override
    public List<Item> listTotalInventory() {
        return jdbcTemplate.query(SQL_LIST_ALL_INVENTORY, new ItemMapper());
    }

    private static final String SQL_GET_ITEM_BY_ID 
                                = "select * from Item where item_id = ?";
    @Override
    public Item getItemBySelection(long itemId) {
      try{
        return jdbcTemplate.queryForObject(SQL_GET_ITEM_BY_ID, new ItemMapper(),
                                            itemId); 
      } catch(EmptyResultDataAccessException e){
          return null;
      }
    }

    private static final String SQL_REMOVE_ITEM = "update Item set "
                                + "item_quantity = ? where item_id = ?";
    @Override
    public void removeItem(Item item) {
        jdbcTemplate.update(SQL_REMOVE_ITEM, item.getItemQuantity(), item.getInventoryId());
    }

    @Override
    public void loadInventory() throws VendingMachinePersistenceException, URISyntaxException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item currentItem = new Item(rs.getLong("item_id"));
            currentItem.setCost(rs.getString("item_cost"));
            currentItem.setItemType(rs.getString("item_type"));
            currentItem.setItemQuantity(rs.getString("item_quantity"));
            return currentItem;
        }
        
    }
}
