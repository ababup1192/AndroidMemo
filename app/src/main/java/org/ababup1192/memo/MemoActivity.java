package org.ababup1192.memo;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.ababup1192.memo.R;

import java.util.List;

public class MemoActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Memo> {

    private Integer memoId;
    private EditText memoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        memoId = getIntent().getIntExtra("MEMO_ID", 1);
        memoText = (EditText) findViewById(R.id.memo);

        if(memoId != -1) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.memo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_save) {
            String contents = memoText.getText().toString();
            MemoRepository memoRepository = new MemoRepository(this);

            if (memoId == -1) {
                memoRepository.save(contents);
            } else {
                memoRepository.update(memoId, contents);
            }
            Intent intent = new Intent(getBaseContext(), MemoListActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Memo> onCreateLoader(int i, Bundle bundle) {
        MemoLoader memoLoader = new MemoLoader(this, memoId);
        memoLoader.forceLoad();

        return memoLoader;
    }

    @Override
    public void onLoadFinished(Loader<Memo> memoLoader, Memo memo) {
        EditText memoText = (EditText) findViewById(R.id.memo);
        memoText.setText(memo.getContents());
    }

    @Override
    public void onLoaderReset(Loader<Memo> memoLoader) {

    }
}
