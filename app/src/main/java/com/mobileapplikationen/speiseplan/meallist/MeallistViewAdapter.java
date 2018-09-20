package com.mobileapplikationen.speiseplan.meallist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapplikationenfhws.speiseplan.R;

import org.w3c.dom.Text;

import java.util.List;

public class MeallistViewAdapter extends RecyclerView.Adapter<MeallistViewAdapter.MyViewHolder>{

    private final List<Meal> foodData;

    // Constructor
    public MeallistViewAdapter(List<Meal> foodData) {
        this.foodData = foodData;
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
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price, price_prof, price_guest;

        public MyViewHolder(View itemView) {
            super(itemView);
            //id = ((TextView) itemView.findViewById(R.id.id));
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            price_prof = itemView.findViewById(R.id.price_bed);
            price_guest = itemView.findViewById(R.id.price_guest);

        }

        // Assigns Data to a specific "Card" -> Adds Meal-Data to card!
        public void assignData(Meal meal) {

            String stud_price;
            String bedi_price;
            String guest_price;
            String name = meal.getName();

            // Differentiates between TITLE and DATA -> Different FORMATTING!
            if (name.equals("GERICHT")) {
                stud_price = (meal.getPrice());
                bedi_price = (meal.getPricebed());
                guest_price = (meal.getPriceguest());
            } else {
                stud_price = (meal.getPrice() + "€ ");
                bedi_price = (meal.getPricebed() + "€ ");
                guest_price = (meal.getPriceguest() + "€");
                this.name.setText(meal.getName() + " ");
            }


            this.name.setText(name);
            this.price.setText(stud_price);
            this.price_prof.setText(bedi_price);
            this.price_guest.setText(guest_price);
        }


        // IMPORTANT when view is pressed!

        //@Override
        //public void onClick(View v) {
        //    int pos = getAdapterPosition();
        //    Meal meal = MeallistViewAdapter.this.foodData.get(pos); // inform the activity about the item click
        //}

    }

    
}
