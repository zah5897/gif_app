package com.zah.app.dao;

import com.zah.app.model.GifModel;
import com.zah.app.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gifDao")
public class GifDao extends BaseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer getImgCount(String img_url) {
        String sql = "select count(*) from t_gif where img_url=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{img_url}, Integer.class);
    }

    public List<GifModel> listGif(int type, int page, int limit, String key_word) {
        String sql = "select *from t_gif where 1=1 ";
        if (!TextUtils.isEmpty(key_word)) {
            sql += "  and  description REGEXP " + ".*?" + key_word.trim() + ".*";
        }
        sql += " and channel=?  order by id desc limit ? ,?";
        return jdbcTemplate.query(sql, new Object[]{type, (page - 1) * limit, limit}, new BeanPropertyRowMapper<GifModel>(GifModel.class));
    }

    public List<GifModel> loadRandom(int type, int page, int limit) {
        String sql = "select *from t_gif where channel=?  order by id desc limit ?,? ";
        return jdbcTemplate.query(sql, new Object[]{type, (page - 1) * limit, limit}, new BeanPropertyRowMapper<GifModel>(GifModel.class));
    }

    public int getCount(int type) {
        String sql = "select count(*) from t_gif where channel=?  ";
        return jdbcTemplate.queryForObject(sql, new Object[]{type}, Integer.class);
    }


    public List<GifModel> loadfor_AppStore(int page, int limit) {
        String sql = "select *from t_gif where flag=?  order by id desc limit ?,? ";
        return jdbcTemplate.query(sql, new Object[]{1, (page - 1) * limit, limit}, new BeanPropertyRowMapper<GifModel>(GifModel.class));
    }

    public int getCountfor_AppStore() {
        String sql = "select count(*) from t_gif where flag=?  ";
        return jdbcTemplate.queryForObject(sql, new Object[]{1}, Integer.class);
    }

    public void delGifById(String id) {
        jdbcTemplate.update("delete from t_gif where id=?",new Object[]{id});
    }

    public void updateFlag(String id, int flag) {
        jdbcTemplate.update("update  t_gif set flag=? where id=?", new Object[]{flag, id});
    }
}
