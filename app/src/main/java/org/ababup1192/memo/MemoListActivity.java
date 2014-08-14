package org.ababup1192.memo;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemoListActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<List<Memo>> {

    private MemoListAdapter memoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);


        final ListView memoListView = (ListView) findViewById(R.id.memo_list);
        memoListAdapter = new MemoListAdapter(this);
        memoListView.setAdapter(memoListAdapter);

        // loaderの初期化
        getSupportLoaderManager().initLoader(0, null, this);

        memoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Memo memo = memoListAdapter.getMemo(position);
                Intent intent = new Intent(getBaseContext(), MemoActivity.class);
                intent.putExtra("MEMO_ID", memo.getId());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.memo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_new) {
            Intent intent = new Intent(getBaseContext(), MemoActivity.class);
            intent.putExtra("MEMO_ID", -1);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Memo>> onCreateLoader(int i, Bundle bundle) {
        MemoListLoader memoListLoader = new MemoListLoader(this);
        memoListLoader.forceLoad();

        return memoListLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Memo>> listLoader, List<Memo> memoList) {
        memoListAdapter.setMemoList(memoList);
        memoListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Memo>> listLoader) {

    }
}
