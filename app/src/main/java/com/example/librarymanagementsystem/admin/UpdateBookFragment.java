package com.example.librarymanagementsystem.admin;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.librarymanagementsystem.DatabaseHandler;
import com.example.librarymanagementsystem.DbModel;
import com.example.librarymanagementsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextInputEditText id1,title1,auth1,pub1;
    TextInputLayout id,title,auth,pub;
    CardView update,details;
    public UpdateBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateBookFragment newInstance(String param1, String param2) {
        UpdateBookFragment fragment = new UpdateBookFragment();
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
        View view =  inflater.inflate(R.layout.fragment_update_book, container,false);
        id1=view.findViewById(R.id.updateBookID1);
        title1=view.findViewById(R.id.updateBookTitle1);
        auth1=view.findViewById(R.id.updateBookAuth1);
        pub1=view.findViewById(R.id.updateBookPub1);
        id=view.findViewById(R.id.updateBookID);
        title=view.findViewById(R.id.updateBookTitle);
        auth=view.findViewById(R.id.updateBookAuth);
        pub=view.findViewById(R.id.updateBookPub);
        update=view.findViewById(R.id.updateBookCard);
        details=view.findViewById(R.id.getDetails);

        details.setOnClickListener(new GetDetails());
        update.setOnClickListener(new UpdateBook());
        return view;
    }

    class GetDetails implements View.OnClickListener{
        int flag=0;
        @Override
        public void onClick(View v) {
            if (id1.getText().toString().isEmpty() || id1.getText().toString().contains(" ")){
                id.setError("Invalid user id");
                flag=1;
            }

            else id.setError(null);
            if(flag==0) {
                DatabaseHandler db = new DatabaseHandler(getActivity());
                List<DbModel> list = db.getAllBooks();
                for (DbModel book : list) {
                    if (book.getBook_id().equals(id1.getText().toString())) {
                        flag=2;
                        title1.setText(book.getBook_title());
                        auth1.setText(book.getBook_auth());
                        pub1.setText(book.getBook_pub());
                        title1.setEnabled(true);
                        auth1.setEnabled(true);
                        pub1.setEnabled(true);
                    }
                }
            }
            if(flag!=2){
                id.setError("No such book available");
            }
        }
    }

    class UpdateBook implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int f=0;

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
                List<DbModel> list=db.getAllBooks();
                for (DbModel stud:list){
                    if(stud.getBook_id().equals(id1.getText().toString())){
                        f=1;
                        model.setBook_title(title1.getText().toString());
                        model.setBook_auth(auth1.getText().toString());
                        model.setBook_pub(pub1.getText().toString());
                        db.updateBook(model);
                        Toast.makeText(getActivity(), "Book Updated", Toast.LENGTH_SHORT).show();
                    }
                }
                if(f==0) id.setError("No suck book id available");
            }
        }
    }
}