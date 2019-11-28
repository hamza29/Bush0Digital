//package com.glowingsoft.bushdigital;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static android.graphics.Typeface.NORMAL;
//
//
//public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
//    Context context;
//    String val;
//    List<CategoryModelLocal> categoryModelLocals;
//    List<CategoryModelMainList> categoryModelMainLists;
//
//    public VideoAdapter(Context context, List<CategoryModelMainList> categoryModelMainLists, List<CategoryModelLocal> categoryModelLocals, String val) {
//        this.categoryModelLocals = categoryModelLocals;
//        this.categoryModelMainLists = categoryModelMainLists;
//        this.context = context;
//        this.val = val;
//
//    }
//
//
//    @Override
//    public VideoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_videp, parent, false);
//        return new VideoAdapter.MyViewHolder(view);
//    }
//
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
////        List<CategoryModelLocal> categoryModelLocals = categoryModelMainLists.get(listPosition).getCategoryModelLocals();
////        Log.e("TGED", "MODEL" + categoryModelLocals.get(listPosition).getTitle());
////        for (int i = 0; i < categoryModelLocals.size(); i++) {
////            if (categoryModelLocals.get(i).getCategory() == val) {
//                holder.s.setText("" + val);
////            }
////            }
////if(val.equalsIgnoreCase(categoryModelMainLists.get(la)))
//        Log.e("TGED", "Valllll  : " + val);
////        Log.e("TGED", "loca SIZE: " + categoryModelLocals.size());
////
////
////        for (int i = 0; i < categoryModelMainLists.size(); i++) {
////            for (int j = 0; j < categoryModelLocals.size(); j++) {
////                if (categoryModelLocals.get(j).getCategory() == val) {
////                    holder.s.setText("" + categoryModelLocals.get(j).getTitle());
////                }
////            }
////        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return categoryModelMainLists.size();
//    }
//
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView s;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//            s = itemView.findViewById(R.id.a);
//        }
//    }
//}