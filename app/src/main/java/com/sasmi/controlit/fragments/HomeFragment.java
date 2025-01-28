package com.sasmi.controlit.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sasmi.controlit.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the CardView in the fragment layout
        CardView cardView = view.findViewById(R.id.cardView1);

        // Set a long click listener on the CardView
        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Show a custom pop-up
                showPopup(v);
                return true; // Indicate the event was handled
            }
        });
    }

    private void showPopup(View anchorView) {
        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        // Create the PopupWindow
        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);

        // Find views in the custom layout
        TextView popupText = popupView.findViewById(R.id.popupText);
        Button popupButton = popupView.findViewById(R.id.popupButton);
        Button popupButton2 = popupView.findViewById(R.id.popupButton2);

        // Customize the views in the pop-up
        popupText.setText("Are you sure you want to delete?");
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the pop-up
                popupWindow.dismiss();
                //Toast.makeText(requireContext(), "Action Completed", Toast.LENGTH_SHORT).show();
            }
        });
        popupButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the pop-up
                popupWindow.dismiss();
                //Toast.makeText(requireContext(), "Action Cancelled By User", Toast.LENGTH_SHORT).show();
            }
        });

        // Show the pop-up relative to the anchor view
        popupWindow.showAsDropDown(anchorView, 0, 0); // Adjust the offset if needed
    }
}