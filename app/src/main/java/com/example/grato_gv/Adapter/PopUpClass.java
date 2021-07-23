package com.example.grato_gv.Adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Model.AddQuizToClass;
import com.example.grato_gv.R;

import java.util.ArrayList;

public class PopUpClass {
    ///////
    RecyclerView addQuizToClassRecyclerview;
    //////
    //PopupWindow display method
    public void getData() {
        // Khai báo data giả cho mảng
        ArrayList<AddQuizToClass> lstQuiz = new ArrayList<>();
        lstQuiz.add(new AddQuizToClass("Class L01"));
        lstQuiz.add(new AddQuizToClass("Class L02"));
        lstQuiz.add(new AddQuizToClass("Class L03"));
        lstQuiz.add(new AddQuizToClass("Class L04"));

        // tạo adapter
        truongAddQuizToClass listAddQuizToClass = new truongAddQuizToClass(lstQuiz);

        // performance
        addQuizToClassRecyclerview.setHasFixedSize(true);

        // set adapter cho Recycler View
        addQuizToClassRecyclerview.setAdapter(listAddQuizToClass);
    }
    //////

    public void showPopupWindow(final View view) {

        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.truong_add_quiz_to_class, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //Initialize the elements of our window, install the handler

        //TextView test2 = popupView.findViewById(R.id.titleText);
        //test2.setText(R.string.textTitle);

        Button messageButton = popupView.findViewById(R.id.messageButton);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //As an example, display the message
//                Toast.makeText(view.getContext(), "Wow, popup action button", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

        //Handler for clicking on the inactive zone of the window

//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //Close the window when clicked
//                popupWindow.dismiss();
//                return true;
//            }
//        });
    }

}
