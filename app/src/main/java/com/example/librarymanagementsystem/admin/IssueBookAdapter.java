package com.example.librarymanagementsystem.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagementsystem.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueBookAdapter extends RecyclerView.Adapter<IssueBookAdapter.ViewHolder> implements Filterable {
    Context context;
    List<ModelClass> book;
    List<ModelClass> listFull;

    public IssueBookAdapter(Context context, ArrayList<ModelClass> book) {
        this.context = context;
        this.book = book;
    }

    @NonNull
    @Override
    public IssueBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_book,parent,false);
        listFull=new ArrayList<>();
        listFull.addAll(book);
        return new IssueBookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(book.get(position).getBookId());
        holder.title.setText(book.get(position).getBookTitle());
        holder.auth.setText(book.get(position).getBookAuth());
        holder.pub.setText(book.get(position).getBookPub());
    }

    @Override
    public int getItemCount() {
        return book.size();
    }

    @Override
    public Filter getFilter() {
        return FilterList;
    }
    private Filter FilterList=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchText=constraint.toString().toLowerCase();

            List<ModelClass> tempList=new ArrayList<>();
//            for(ModelClass s:tempList){
//                Log.d("TAG", "performFiltering: "+s.getBookTitle());
//            }

            if(searchText.length()==0 || searchText.isEmpty()){
                tempList.addAll(listFull);
            }

            else {
                for (ModelClass model:listFull){
                    if(model.getBookTitle().toLowerCase().contains(searchText)){
                        tempList.add(model);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=tempList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            book.clear();
            book.addAll((Collection<? extends ModelClass>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,title,pub,auth;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.bookIdText);
            title=itemView.findViewById(R.id.bookTitleText);
            pub=itemView.findViewById(R.id.bookPubText);
            auth=itemView.findViewById(R.id.bookAuthText);
        }
    }

}
