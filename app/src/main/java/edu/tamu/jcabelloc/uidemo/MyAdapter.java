package edu.tamu.jcabelloc.uidemo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView mTextView;

        final ImageView productIconView;
        final TextView productNameView;
        /*
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
        */
        public ViewHolder(View view) {
            super(view);
            productIconView = view.findViewById(R.id.product_icon);
            productNameView = view.findViewById(R.id.product_name);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            String productName = mDataset[adapterPosition];

            //TextView tv = (TextView)view.findViewById(R.id.product_name);
            //tv.setText("Selected");
            //view.setBackgroundResource(R.color.activated);

            boolean activate = (view.isActivated())? false : true;
            view.setActivated(activate);

            Log.d("Selected Product: ", adapterPosition + " - " + productName);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        //TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d("Position: ", mDataset[position]);
        //holder.mTextView.setText(mDataset[position]);
        holder.productIconView.setImageResource(R.drawable.product_img);
        holder.productNameView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}