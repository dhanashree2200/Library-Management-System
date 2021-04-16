package com.example.librarymanagementsystem.admin;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.librarymanagementsystem.DatabaseHandler;
import com.example.librarymanagementsystem.DbModel;
import com.example.librarymanagementsystem.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IssueBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IssueBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextInputEditText bid1,sid1;
    TextInputLayout bid,sid;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CardView issue;
    public IssueBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IssueBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IssueBookFragment newInstance(String param1, String param2) {
        IssueBookFragment fragment = new IssueBookFragment();
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
        View view =  inflater.inflate(R.layout.fragment_issue_book, container,false);
        sid=view.findViewById(R.id.issueStudID);
        bid=view.findViewById(R.id.issueBookID);
        sid1=view.findViewById(R.id.issueStudID1);
        bid1=view.findViewById(R.id.issueBookID1);
        issue=view.findViewById(R.id.issueBookButton);


        issue.setOnClickListener(new IssueBook());

        return view;
    }
    public class IssueBook implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (bid1.getText().toString().isEmpty() || bid1.getText().toString().contains(" "))
                bid.setError("Invalid book id");
            else bid.setError(null);
            if (sid1.getText().toString().isEmpty())
                sid1.setError("Invalid student id");
            else sid.setError(null);
            if (bid.getError() == null && sid.getError() == null) {
                DatabaseHandler db = new DatabaseHandler(getActivity());
                DbModel model = new DbModel();
                DbModel dbModelStud = db.getSingleStud(sid1.getText().toString());
                DbModel dbModelBook = db.getSingleBook(bid1.getText().toString());

                if (dbModelStud.getStud_id() != null) {
                    if (dbModelBook.getBook_id() != null) {
                        if (dbModelBook.getStatus() == 0) {
                            if (dbModelStud.getBook() == 0) {
                                model.setI_book_id(bid1.getText().toString());
                                model.setI_stud_id(sid1.getText().toString());
                                long millis = System.currentTimeMillis();
                                java.sql.Date date = new java.sql.Date(millis);
                                model.setI_date(date.toString());
                                model.setR_date("0");
                                db.addIssueBook(model);
                                db.updateStudStatus(sid1.getText().toString(),1);
                                db.updateBookStatus(bid1.getText().toString(),1);
                                Toast.makeText(getActivity(), "Book Issued", Toast.LENGTH_LONG).show();

                                bid1.setText("");
                                sid1.setText("");
                            } else {
                                sid.setError("Student has already issued book");
                            }
                        } else {
                            bid.setError("Book already issued");
                        }
                    }
                 else {
                    bid.setError("No such book available");
                }
            }
            else{
                    sid.setError("No such Student available");
                }

            }
        }
    }
}