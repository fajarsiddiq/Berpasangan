package com.fajarsiddiq.berpasangan.module.extra.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;
import com.norbsoft.typefacehelper.TypefaceHelper;

import static android.view.View.OnClickListener;
import static com.fajarsiddiq.berpasangan.R.id.id_store_fragment_board_linear_layout;
import static com.fajarsiddiq.berpasangan.R.id.id_store_fragment_card_linear_layout;
import static com.fajarsiddiq.berpasangan.R.id.id_store_fragment_encyclopedia_linear_layout;
import static com.fajarsiddiq.berpasangan.R.layout.layout_store_fragment;


/**
 * Created by fajar on 8/10/16.
 */

public class StoreFragment extends ModuleFragment implements OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_store_fragment, null);
        view.findViewById(id_store_fragment_encyclopedia_linear_layout).setOnClickListener(this);
        view.findViewById(id_store_fragment_card_linear_layout).setOnClickListener(this);
        view.findViewById(id_store_fragment_board_linear_layout).setOnClickListener(this);
        TypefaceHelper.typeface(view);
        return view;
    }

    @Override
    public void onClick(View view) {
//        StoreContentFragment contentFragment = new StoreContentFragment();
//        Bundle bundle = new Bundle();
//        if(view.getId() == id_store_fragment_encyclopedia_linear_layout) {
//            bundle.putString("Key", StoreActivity.ENCYCLOPEDIA_TAG);
//            contentFragment.setArguments(bundle);
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            transaction.replace(id_store_frame_layout, contentFragment, StoreActivity.ENCYCLOPEDIA_TAG);
//            transaction.commit();
//        } else if(view.getId() == id_store_fragment_card_linear_layout) {
//            Log.i("Test", "Latar Tile");
//        } else if(view.getId() == id_store_fragment_board_linear_layout) {
//            Log.i("Test", "Latar Papan");
//        }
    }
}
