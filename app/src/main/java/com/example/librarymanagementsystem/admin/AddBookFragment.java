package com.example.librarymanagementsystem.admin;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.librarymanagementsystem.DatabaseHandler;
import com.example.librarymanagementsystem.DbModel;
import com.example.librarymanagementsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextInputEditText id1,title1,auth1,pub1;
    TextInputLayout id,title,auth,pub;
    CardView register;
    public AddBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBookFragment newInstance(String param1, String param2) {
        AddBookFragment fragment = new AddBookFragment();
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
        View view =  inflater.inflate(R.layout.fragment_add_book, container,false);
        id1=view.findViewById(R.id.bookID1);
        title1=view.findViewById(R.id.bookTitle1);
        auth1=view.findViewById(R.id.bookAuth1);
        pub1=view.findViewById(R.id.bookPub1);
        id=view.findViewById(R.id.bookID);
        title=view.findViewById(R.id.bookTitle);
        auth=view.findViewById(R.id.bookAuth);
        pub=view.findViewById(R.id.bookPub);
        register=view.findViewById(R.id.returnBookCard);
        register.setOnClickListener(new UserRegistration());
        return view;
    }
    class UserRegistration implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (id1.getText().toString().isEmpty() || id1.getText().toString().contains(" "))
                id.setError("Invalid user id");
            else id.setError(null);
            if (title1.getText().toString().isEmpty())
                title1.setError("Invalid book title");
            else title.setError(null);
            if (auth1.getText().toString().isEmpty())
                auth.setError("Invalid author");
            else auth.setError(null);
            if (pub1.getText().toString().isEmpty())
                pub1.setError("Invalid Publisher");
            else pub.setError(null);
            if (id.getError() == null && title.getError() == null && auth.getError() == null && pub.getError() == null) {
                DatabaseHandler db = new DatabaseHandler(getActivity());
                DbModel model = new DbModel();
                model.setBook_id(id1.getText().toString());
                model.setBook_title(title1.getText().toString());
                model.setBook_auth(auth1.getText().toString());
                model.setBook_pub(pub1.getText().toString());
                model.setStatus(0);
                db.addBook(model);
                Toast.makeText(getActivity(), "Book Added", Toast.LENGTH_SHORT).show();
                id1.setText("");
                auth1.setText("");
                pub1.setText("");
                title1.setText("");
            }
        }
    }
}