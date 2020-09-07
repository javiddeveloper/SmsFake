package com.sattar.j.smsfake.tools.destination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sattar.j.smsfake.R;
import com.sattar.j.smsfake.data.entity.Destination;
import com.sattar.j.smsfake.tools.customViews.CustomImageView;
import com.sattar.j.smsfake.tools.customViews.CustomTextView;

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

//    public String getBranchName() {
//        return branchName;
//    }

//    public void setBranchName(String branchName) {
//        this.branchName = branchName;
//    }

//    public int getSelectedIndex() {
//        return selectedIndex;
//    }

//    public int getPositionSeparator() {
//        return positionSeparator;
//    }

//    public void setPositionSeparator(int positionSeparator) {
//        this.positionSeparator = positionSeparator;
//    }

    @Override
    public DestinationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        rootView = LayoutInflater.from(context).inflate(R.layout.item_destination_layout, parent, false);
        return new ViewHolder(rootView);
//        LayoutLoginItemBinding itemBinding = LayoutLoginItemBinding.inflate(LayoutInflater.from(context), parent, false);
//        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Destination destination = list.get(position);
        holder.desName.setText(destination.getName());
        holder.desNumber.setText(destination.getNumber());
    }

//    @Override
//    public void onBindViewHolder(final BranchListAdapter.ViewHolder holder, final int position) {
//        Destination destination = list.get(position);
//        holder.desName.setText(destination.getName());
//        holder.desNumber.setText(destination.getNumber());
        //Show selected or not selected according to favorite map
//        holder.mBinding.setBranchCode(fund.getBranchCode());
//        holder.mBinding.setBranchName(fund.getBranchName());
//        holder.mBinding.setFavorite(fund.getFavorite());
//        holder.mBinding.setSelected(getBranchCode() != null && getBranchCode().equals(fund.getBranchCode()));
//        if (getBranchCode() != null && getBranchCode().equals(fund.getBranchCode()))
//            holder.mBinding.textViewBranchName.setTextColor(context.getResources().getColor(R.color.color_text_white));
//        holder.mBinding.setSeparator(position == (getPositionSeparator() - 1));
//        holder.mBinding.textViewBranchName.setGravity(View.FOCUS_RIGHT);
//        Picasso.with(context).load(new File(context.getExternalFilesDir("logos"), branchCode + ".gif"))
//                .into(holder.mBinding.imageViewBranchLogo);

//    }

//    public Branch getBranchByCode(String branchCode) {
//        Branch branch = new Branch();
//        for (int i = 0; i < list.size(); i++)
//            if (branchCode.equals(list.get(i).getBranchCode())) {
//                branch = list.get(i);
//                break;
//            }
//        return branch;
//    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


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
