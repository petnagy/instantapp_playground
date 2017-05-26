package com.playground.instant.instantapp_playground.pages.repos.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

import com.playground.instant.instantapp_playground.common.View;

/**
 * Created by petnagy on 2017. 05. 25..
 */

public class SpaceItemDecor extends RecyclerView.ItemDecoration {

    private final int space;

    public SpaceItemDecor(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, android.view.View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.top = space;
        outRect.left = space;
        outRect.right = space;
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = space;
        }
    }

}
