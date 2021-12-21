package com.alexpetrov.loftcoin.widget;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.alexpetrov.loftcoin.util.OnItemClick;

import io.reactivex.Observable;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.annotations.NonNull;

public class RecyclerViews {

    @NonNull
    public static Observable<Integer> onSnap(@NonNull RecyclerView rv, @NonNull SnapHelper helper) {
        return Observable.create((emitter) -> {
            MainThreadDisposable.verifyMainThread();
            final RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                        final View snapView = helper.findSnapView(rv.getLayoutManager());
                        if (snapView != null) {
                            final RecyclerView.ViewHolder holder = rv.findContainingViewHolder(snapView);
                            if (holder != null) {
                                emitter.onNext(holder.getAdapterPosition());
                            }
                        }
                    }
                }
            };
            emitter.setCancellable(() -> rv.removeOnScrollListener(listener));
            rv.addOnScrollListener(listener);
        });
    }

    @NonNull
    public static Observable<Integer> onClick(@NonNull RecyclerView rv) {
        return Observable.create((emitter) -> {
            MainThreadDisposable.verifyMainThread();
            final RecyclerView.OnItemTouchListener listener = new OnItemClick((v) -> {
                final RecyclerView.ViewHolder holder = rv.findContainingViewHolder(v);
                if (holder != null) {
                    emitter.onNext(holder.getAdapterPosition());
                }
            });
            emitter.setCancellable(() -> rv.removeOnItemTouchListener(listener));
            rv.addOnItemTouchListener(listener);
        });
    }

}