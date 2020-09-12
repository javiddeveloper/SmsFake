package com.sattar.j.smsfake.view.navigations.sendMessage.destination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sattar.j.smsfake.R;
import com.sattar.j.smsfake.data.entity.Destination;
import com.sattar.j.smsfake.view.customViews.CustomImageView;
import com.sattar.j.smsfake.view.customViews.CustomTextView;

import java.util.List;

/**
 * Created by javid on 10/29/18.
 */
public class DestinationListAdapter extends RecyclerView.Adapter<DestinationListAdapter.ViewHolder> {

    private List<Destination> list;
    private View rootView;
    private Context context;

    public DestinationListAdapter(Context context, List<Destination> list) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public DestinationListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        rootView = LayoutInflater.from(context).inflate(R.layout.item_destination_layout, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Destination destination = list.get(position);
        holder.desName.setText(destination.getName());
        holder.desNumber.setText(destination.getNumber());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CustomTextView desName;
        CustomTextView desNumber;
        CustomImageView destAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desName = itemView.findViewById(R.id.txt_name);
            desNumber = itemView.findViewById(R.id.txt_number);
            destAvatar = itemView.findViewById(R.id.img_avatar);

        }
    }

    interface OnDestItemClick {
        void onClick(Destination destination);
    }
}
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }

//    public class ViewHolder extends RecyclerView.ViewHolder {
//        LayoutLoginItemBinding mBinding;
//
//        public ViewHolder(LayoutLoginItemBinding mBinding) {
//            super(mBinding.getRoot());
//            this.mBinding = mBinding;
//            StyleManager.getInstance().changeColorSelectedBranchItem(mBinding.LinearLayoutSelectedItem);
//
//            this.mBinding.textViewBranchName.setOnClickListener(view -> {
//                mBinding.textViewBranchName.setTextColor(context.getResources().getColor(R.color.color_text_white));
//                setBranchCode(list.get(getAdapterPosition()).getBranchCode());
//                setBranchName(list.get(getAdapterPosition()).getBranchName());
//                selectedIndex = getAdapterPosition();
//                branchClick.onBranchSelected(list.get(getAdapterPosition()));
//            });
//
//
//            this.mBinding.imageViewFavorite.setOnClickListener(view -> {
//                //Show selected or not selected according to favorite map
//                if (list.get(getAdapterPosition()).getFavorite() == 0) {
//                   // mBinding.imageViewFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.fav));
//                    list.get(getAdapterPosition()).setFavorite(1);
//                    notifyItemChanged(getAdapterPosition());
//                } else {
//                   // mBinding.imageViewFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.un_fav));
//                    list.get(getAdapterPosition()).setFavorite(0);
//                    notifyItemChanged(getAdapterPosition());
//                }
//
//                if (onFavoriteSelectListener != null) {
//                    onFavoriteSelectListener.onFavoriteSelect(list.get(getAdapterPosition()), getAdapterPosition());
//                }
//            });
//
//        }
//
//    }

//}
