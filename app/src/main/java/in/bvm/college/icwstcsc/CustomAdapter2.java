package in.bvm.college.icwstcsc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder2> {

    private ArrayList<DataModel2> dataSet;

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDescription;
        ImageView imageViewIcon;

        public MyViewHolder2(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName2);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription2);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView2);
        }
    }

    public CustomAdapter2(ArrayList<DataModel2> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.people_item2, parent, false);


        MyViewHolder2 myViewHolder = new MyViewHolder2(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder2 holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewDescription = holder.textViewDescription;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewDescription.setText(dataSet.get(listPosition).getDescription());
        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
