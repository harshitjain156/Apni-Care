package com.example.apnicare.ModelResponses.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnicare.R;
import com.example.apnicare.RetrofitClient;
import com.example.apnicare.SharedPrefManager;
import com.example.apnicare.myCart.AddItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class searchMedicineAdapter extends RecyclerView.Adapter<searchMedicineAdapter.ViewHolder>{
    private Context context;
    private List<items> Items;
    SharedPrefManager sharedPrefManager;

    public searchMedicineAdapter(Context context, List<items> items) {
        this.context = context;
        this.Items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_card,parent,false);
        sharedPrefManager=new SharedPrefManager(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        items searchresponse=Items.get(position);

        holder.name.setText(Items.get(position).getName());
        holder.price.setText(Items.get(position).getMrp().toString());
    }



    @Override
    public int getItemCount() {
        return Items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView price;
        Button add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this::onClick);
            name=itemView.findViewById(R.id.name_medicine);
            price=itemView.findViewById(R.id.price);
            add=itemView.findViewById(R.id.addtocartsearchbtn);
            add.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            String id;
            id=Items.get(getAdapterPosition()).getSlug();
            addtocart(id);

        }
    }

    private void addtocart(String id) {
        Call<AddItemResponse> call= RetrofitClient.getInstance().getApi().additemtocart(id,"Bearer "+sharedPrefManager.getData().getAccess_token());
        call.enqueue(new Callback<AddItemResponse>() {
            @Override
            public void onResponse(Call<AddItemResponse> call, Response<AddItemResponse> response) {
                Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    Toast.makeText(context,"item added to cart",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddItemResponse> call, Throwable t) {

            }
        });

    }
}