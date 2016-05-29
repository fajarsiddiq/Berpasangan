package com.fajarsiddiq.berpasangan.module.result;

import com.fajarsiddiq.berpasangan.module.ModuleController;

/**
 * Created by Muhammad Fajar on 28/05/2016.
 */
public class ResultController extends ModuleController {
    private ResultHandler mHandler;

    ResultController(final ResultFragment fragment) {
        super(fragment.getContext());
        mHandler = new ResultHandler(fragment);
    }
}
