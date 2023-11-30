package com.example.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import java.io.File;

public class ImageDialogFragment extends DialogFragment {

    public static final String ARG_SUSPECT_IMAGE = "SuspectImage";

    public static ImageDialogFragment newInstance(File photoFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SUSPECT_IMAGE, photoFile);
        ImageDialogFragment fragment = new ImageDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_image, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.dialog_image_image_view);
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                File photoFile = (File) getArguments().getSerializable(ARG_SUSPECT_IMAGE);
                Bitmap image = PictureUtils.getScaledBitmap(photoFile.getPath(), getActivity());
                imageView.setImageBitmap(image);
            }
        });

        return new AlertDialog.Builder(getActivity()).setView(imageView).create();
    }


}
