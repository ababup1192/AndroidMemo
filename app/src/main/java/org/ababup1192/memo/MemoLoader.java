package org.ababup1192.memo;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class MemoLoader extends AsyncTaskLoader<Memo> {

    private Integer id;

    public MemoLoader(Context context, Integer id) {
        super(context);
        this.id = id;
    }

    @Override
    public Memo loadInBackground() {
        MemoRepository memoRepository = new MemoRepository(getContext());
        return memoRepository.findById(id);
    }
}
