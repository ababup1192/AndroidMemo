package org.ababup1192.memo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MemoRepository {

    private DatabaseHelper helper;


    public MemoRepository(Context context) {
        helper = new DatabaseHelper(context);
    }

    public List<Memo> findAll() {

        List<Memo> memoList = null;

        try {
            Dao<Memo, Integer> dao = helper.getDao(Memo.class);
            memoList = dao.query(dao.queryBuilder().orderBy("updateTime", false).prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memoList;
    }

    public Memo findById(Integer id) {
        Memo memo = null;

        try {
            Dao<Memo, Integer> dao = helper.getDao(Memo.class);
            memo = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memo;
    }

    public void update(Integer id, String contents) {
        Memo memo = null;
        if (id == null) {
            memo = new Memo(contents);
        } else {
            memo = new Memo(id, contents, new Date());
        }

        try {
            Dao<Memo, Integer> dao = helper.getDao(Memo.class);
            dao.createOrUpdate(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(String contents) {
        update(null, contents);
    }


}
