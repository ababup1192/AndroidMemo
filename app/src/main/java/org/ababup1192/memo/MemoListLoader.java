package org.ababup1192.memo;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class MemoListLoader extends AsyncTaskLoader<List<Memo>> {

    public MemoListLoader(Context context) {
        super(context);
    }

    @Override
    public List<Memo> loadInBackground() {
        MemoRepository memoRepository = new MemoRepository(getContext());

        return memoRepository.findAll();
    }
}
