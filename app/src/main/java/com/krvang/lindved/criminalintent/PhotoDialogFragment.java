package com.krvang.lindved.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PhotoDialogFragment extends DialogFragment {

    private static final String PHOTO_PATH = "photo_path";

    private ImageView mCrimePhoto;
    private ImageButton mCloseButton;

    public static PhotoDialogFragment newInstance(String photoPath) {
        PhotoDialogFragment fragment = new PhotoDialogFragment();

        Bundle args = new Bundle();
        // Place args here...
        args.putString(PHOTO_PATH, photoPath);

        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String photoPath = getArguments().getString(PHOTO_PATH);

        Bitmap bitmap = PictureUtils.getScaledBitmap(photoPath, getActivity());

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photo, null);

        mCrimePhoto = v.findViewById(R.id.crime_photo);
        mCrimePhoto.setImageBitmap(bitmap);

        mCloseButton = v.findViewById(R.id.close);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
}
