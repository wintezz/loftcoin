package com.alexpetrov.loftcoin.ui.wallets;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.alexpetrov.loftcoin.R;
import com.alexpetrov.loftcoin.databinding.FragmentWalletsBinding;

public class WalletsFragment extends Fragment {

    private SnapHelper walletsSnapHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentWalletsBinding binding = FragmentWalletsBinding.bind(view);
        walletsSnapHelper = new PagerSnapHelper();
        walletsSnapHelper.attachToRecyclerView(binding.recycler);

        final TypedValue value = new TypedValue();
        view.getContext().getTheme().resolveAttribute(R.attr.walletCardWidth, value, true);
        final DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        final int padding = (int) (displayMetrics.widthPixels - value.getDimension(displayMetrics)) / 2;
        binding.recycler.setPadding(padding, 0, padding, 0);
        binding.recycler.setClipToPadding(false);

        binding.recycler.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        binding.recycler.addOnScrollListener(new CarouselScroller());

        binding.recycler.setAdapter(new WalletsAdapter());
        binding.recycler.setVisibility(View.VISIBLE);
        binding.walletCard.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        walletsSnapHelper.attachToRecyclerView(null);
        super.onDestroyView();
    }

    private static class CarouselScroller extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            final int centerX = (recyclerView.getLeft() + recyclerView.getRight()) / 2;
            for (int i = 0; i < recyclerView.getChildCount(); ++i) {
                final View child = recyclerView.getChildAt(i);
                final int childCenterX = (child.getLeft() + child.getRight()) / 2;
                final float childOffset = Math.abs(centerX - childCenterX) / (float) centerX;
                float factor = (float) (Math.pow(0.85, childOffset));
                child.setScaleX(factor);
                child.setScaleY(factor);
            }
        }
    }

}
