package com.fajarsiddiq.berpasangan.module.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;
import com.fajarsiddiq.berpasangan.module.board.BoardActivity;
import com.nispok.snackbar.Snackbar;

import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_exit_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_extra_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_help_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_start_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_main_fragment_statistic_image_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_main_fragment;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_title;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_positive;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_negative;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_select_level_title;
import static com.fajarsiddiq.berpasangan.R.array.array_level_name;
import static java.lang.Integer.parseInt;

/**
 * Created by Muhammad Fajar on 17/03/2016.
 */
public class MainFragment extends ModuleFragment implements View.OnClickListener {
    private ImageView mStartImageView;
    private ImageView mExtraImageView;
    private ImageView mStatisticImageView;
    private ImageView mSettingImageView;
    private ImageView mExitImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_main_fragment, null);
        mStartImageView = (ImageView) view.findViewById(id_main_fragment_start_image_view);
        mExtraImageView = (ImageView) view.findViewById(id_main_fragment_extra_image_view);
        mStatisticImageView = (ImageView) view.findViewById(id_main_fragment_statistic_image_view);
        mSettingImageView = (ImageView) view.findViewById(id_main_fragment_help_image_view);
        mExitImageView = (ImageView) view.findViewById(id_main_fragment_exit_image_view);

        mStartImageView.setOnClickListener(this);
        mExtraImageView.setOnClickListener(this);
        mStatisticImageView.setOnClickListener(this);
        mSettingImageView.setOnClickListener(this);
        mExitImageView.setOnClickListener(this);
        Snackbar.with(getContext())
                .position(Snackbar.SnackbarPosition.TOP)
                .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                .text("Halo gan!").show(getActivity());
        return view;
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (view.getId()) {
            case id_main_fragment_start_image_view:
                final String[] levels = getResources().getStringArray(array_level_name);
                builder.setTitle(getString(string_main_activity_select_level_title));
                builder.setItems(array_level_name, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        for (int ii = 0; ii < levels.length; ii++) {
                            if (ii == which) {
                                int row = parseInt(levels[ii].split(" ")[0]);
                                int column = parseInt(levels[ii].split(" ")[2]);
                                startActivity(new Intent(getContext(), BoardActivity.class).putExtra(BoardActivity.row, row).putExtra(BoardActivity.column, column));
                            }
                        }
                    }
                });
                builder.create().show();
                break;
            case id_main_fragment_extra_image_view:
                break;
            case id_main_fragment_statistic_image_view:
                break;
            case id_main_fragment_help_image_view:
                break;
            case id_main_fragment_exit_image_view:
                builder.setMessage(getString(string_main_activity_exit_prompt))
                        .setTitle(getString(string_main_activity_exit_prompt_title));
                builder.setPositiveButton(getString(string_main_activity_exit_prompt_positive), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton(getString(string_main_activity_exit_prompt_negative), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do nothing
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
        }
    }
}

