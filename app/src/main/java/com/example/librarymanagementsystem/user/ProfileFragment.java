package com.example.librarymanagementsystem.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.librarymanagementsystem.DatabaseHandler;
import com.example.librarymanagementsystem.DbModel;
import com.example.librarymanagementsystem.MainActivity;
import com.example.librarymanagementsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;
import static android.hardware.SensorManager.getOrientation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    CardView logout;
    TextView name,mail,ph,id;
    SharedPreferences sharedpreferences;
    CircleImageView circleImageView;

    private static Bitmap Image = null;
    private static Bitmap rotateImage = null;
    private static final int GALLERY = 1;
    public static final String MyPREFERENCES = "UserLogin" ;
    Bitmap bitmap;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view =  inflater.inflate(R.layout.fragment_profile, container,false);
        name=view.findViewById(R.id.nm);
        mail=view.findViewById(R.id.mail);
        ph=view.findViewById(R.id.ph);
        id=view.findViewById(R.id.id);
        circleImageView=view.findViewById(R.id.imageView_profile);

        DatabaseHandler db=new DatabaseHandler(getActivity());

        sharedpreferences = Objects.requireNonNull(getContext()).getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String nm=sharedpreferences.getString("Name",null);
//        Log.d("TAG", "onCreateView: nm = "+nm);
        List<DbModel> list1=db.getAllStud();
        for (DbModel stud:list1){
//            String nn=stud.getStud_name();
//            Log.d("TAG", "onCreateView: nn = "+nm+" name = "+stud.getStud_id());
            if(nm.equals(stud.getStud_id())){
                String firstWord = "";
                if(stud.getStud_name().contains(" ")){
                    firstWord= stud.getStud_name().substring(0, stud.getStud_name().indexOf(" "));
                }
                id.setText(stud.getStud_id());
                name.setText(firstWord);
                mail.setText(stud.getStud_mail());
                ph.setText(stud.getStud_ph());
            }
        }

        logout=view.findViewById(R.id.userLogout);
        sharedpreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent1=new Intent(getActivity(),MainActivity.class);
                startActivity(intent1);
            }
        });

        return view;
    }


}