package com.mobileapplikationen.speiseplan.meallist;
//package com.mobileapplickationen*;
import com.mobileapplikationen.speiseplan.meallist.MeallistActivity.*;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapplikationenfhws.speiseplan.R;
import com.mobilieapplikationen.speiseplan.mealdetail.MealdetailActivity;

import org.w3c.dom.Text;

import java.util.List;

public class MeallistViewAdapter extends RecyclerView.Adapter<MeallistViewAdapter.MyViewHolder>{

    private List<Meal> foodData;

    private final OnMealsClickListener onMealsClickListener;

    // Constructor
    public MeallistViewAdapter(List<Meal> foodData, final OnMealsClickListener onMealsClickListener ) {
        this.foodData = foodData;
        this.onMealsClickListener = onMealsClickListener;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        return new MyViewHolder(v);
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.assignData(foodData.get(position));
    }


    // Returns the item-ID
    public long getItemId(int position) {
        return Long.parseLong(foodData.get(position).getId());
    }


    // Count items in RECYCLER
    public int getItemCount() {
        return foodData.size();
    }


    // Inner class of Adapter
    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        private TextView name, price, price_prof, price_guest;

        public MyViewHolder(View itemView) {
            super(itemView);
            //id = ((TextView) itemView.findViewById(R.id.id));
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            price_prof = itemView.findViewById(R.id.price_bed);
            price_guest = itemView.findViewById(R.id.price_guest);
            itemView.setOnClickListener(this);

        }

        // Assigns Data to a specific "Card" -> Adds Meal-Data to card!
        public void assignData(Meal meal) {

            String stud_price;
            String bedi_price;
            String guest_price;
            String name = meal.getName();


            stud_price = (meal.getPrice() + "€ ");
            bedi_price = (meal.getPricebed() + "€ ");
            guest_price = (meal.getPriceguest() + "€");
            this.name.setText(meal.getName());



            this.name.setText(name);
            this.price.setText(stud_price);
            this.price_prof.setText(bedi_price);
            this.price_guest.setText(guest_price);
        }

        @Override
        public void onClick(View v) {

            if (this.name.getText().equals("HEUTE GIBT ES LEIDER KEIN ESSEN FÜR DICH!")) {

            } else {
                int pos = getAdapterPosition();
                Meal meal = MeallistViewAdapter.this.foodData.get(pos);
                onMealsClickListener.onMealClick(meal);
            }


            //
        }



    }



    
}
