package com.sattar.j.smsfake.tools.destination;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sattar.j.smsfake.R;
import com.sattar.j.smsfake.data.entity.Destination;
import com.sattar.j.smsfake.tools.customViews.CustomEditText;
import com.sattar.j.smsfake.tools.customViews.CustomImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Developed by javid
 */
public class CustomDestinationList extends FrameLayout {
    private PopupWindow dialogBranches;

    private Context context;
    private List<Destination> destinations = new ArrayList<>();
    private Destination destination;
    private CustomEditText edit_destName;
    private CustomImageView imageView_logo;
    private CustomImageView imageView_arrowDown;
    //    private RecyclerView rlc_destination;
    private View rootView;
    private View popUpView;

    public CustomDestinationList(@NonNull Context context) {
        super(context);
        this.context = context;
        initView(null, context);
    }

    public CustomDestinationList(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(attrs, context);
    }


    public CustomDestinationList(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs, context);
    }

    private void initView(AttributeSet attrs, Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.item_destination_layout, null,false);
        edit_destName = rootView.findViewById(R.id.edit_destName);
        imageView_logo = rootView.findViewById(R.id.imageView_logo);
        imageView_arrowDown = rootView.findViewById(R.id.imageView_arrowDown);
        imageView_arrowDown = rootView.findViewById(R.id.imageView_arrowDown);

//        FrameLayout bottomSheet = dialogBranches.findViewById(com.google.android.material.R.id.design_bottom_sheet);
//        BottomSheetBehavior.from(bottomSheet)
//                .setState(BottomSheetBehavior.STATE_EXPANDED);
        if (destination != null) {
            edit_destName.setText(destination.getNumber());
        } else {
            edit_destName.setText(null);
            edit_destName.setHint(R.string.enterPhoneOrName);
        }
        edit_destName.setOnClickListener(view -> {
            showPopupListBranch();
        });
        addView(rootView);
    }

    public void initCombo() {

    }

//    public void showBranchList(View view) {
//        if (clickList != null)
//            clickList.onClickList();
//        showPopupListBranch();
//    }

    private void showPopupListBranch() {
        if (destinations.isEmpty()) {
//            if (adapter != null) {
//                adapter.setList(destinations);
//                adapter.notifyDataSetChanged();
//            }
        }
        popUpView = LayoutInflater.from(context).inflate(R.layout.item_destination_layout, null);
        dialogBranches = new PopupWindow(popUpView.getContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dialogBranches.setWindowLayoutType(Window.FEATURE_NO_TITLE);
        }
        dialogBranches.setContentView(popUpView);
        DestinationListAdapter adapter = new DestinationListAdapter(context, destinations);
        RecyclerView rlc_destination = popUpView.findViewById(R.id.rlc_destination);
        rlc_destination.setLayoutManager(new LinearLayoutManager(popUpView.getContext(),LinearLayoutManager.VERTICAL,false));
        rlc_destination.setAdapter(adapter);
//        mBranchListBinding.imageViewClear.setVisibility(View.VISIBLE);
//        mBranchListBinding.imageViewSearch.setVisibility(View.VISIBLE);
//        mBranchListBinding.imageViewFundRefresh.setOnClickListener(view -> {
//            YoYo.with(Techniques.RotateIn).duration(400).playOn(mBranchListBinding.imageViewFundRefresh);
//            ((MainActivity) Objects.requireNonNull(context)).showWaitingProgressBar(true);
//            FundManager.getInstance().callBranchLogos();
//            FundManager.getInstance().callFundList();
//        });
//        mBranchListBinding.editTextSearch.setOnClickListener(view ->
//                dialogBranches.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN));
//
//        mBranchListBinding.editTextSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(final Editable editable) {
//                arraySearch.setCurrentSearchText(editable.toString().trim());
//                arraySearch.getSearchedItems();
//                adapter.setList(arraySearch.getSearchedItems());
//                adapter.notifyDataSetChanged();
//                if (arraySearch.getSearchedItems().size() == 0) {
//                    mBranchListBinding.textViewNotFoundSearch.setVisibility(View.VISIBLE);
//                    YoYo.with(Techniques.FadeInDown)
//                            .duration(1000)
//                            .playOn(mBranchListBinding.textViewNotFoundSearch);
//                } else {
//                    mBranchListBinding.textViewNotFoundSearch.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        mBranchListBinding.imageViewClear.setOnClickListener(view -> {
//            mBranchListBinding.editTextSearch.setTag("");
//            dialogBranches.dismiss();
//        });
//        mBranchListBinding.editTextSearch.setText("");
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        mBranchListBinding.recyclerBranchList.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(5f)));
//        if (AccountManager.getInstance().getCurrentFund() != null) {
//            adapter.setBranchCode(AccountManager.getInstance().getCurrentFund().getBranchCode());
//        }
//
//        adapter.setBranchClick(branch -> {
//            isRegister = false;
//            if (branchClick != null)
//                branchClick.onBranchClick(branch);
//            AccountManager.getInstance().setCurrentFund(branch);
//            AccountManager.getInstance().setSavedUserPass(branch);
//
//            FundManager.getInstance().callCheckAccessRegister(branch);
//            FundManager.getInstance().callParam(AppParam.ALLOW_ASK_QUESTION);
//
//            FundManager.getInstance().setOrderingFundToDB(AccountManager.getInstance().getCurrentFund());
//            destinations = FundManager.getInstance().readBranchs();
//            arraySearch.setItems(destinations, () -> {
//                adapter.setList(destinations);
//                adapter.setPositionSeparator(FundManager.getInstance().getCountFavorite());
//                adapter.notifyDataSetChanged();
//            });
//            mBinding.textViewBranch.setText(AccountManager.getInstance().getCurrentFund().getBranchName());
//            File imgFile = new File(context.getExternalFilesDir("logos"),
//                    AccountManager.getInstance().getCurrentFund().getBranchCode() + ".gif");
//            if (imgFile.exists()) {
//                Picasso.with(context).load(imgFile)
//                        .into(mBinding.imageViewBranchLogo, new Callback() {
//                            @Override
//                            public void onSuccess() {
//                                mBinding.imageViewBranchLogo.setColorFilter(null);
//                                Bitmap imageBitmap = ((BitmapDrawable) mBinding.imageViewBranchLogo.getDrawable()).getBitmap();
//                                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), imageBitmap);
//                                imageDrawable.setCircular(true);
//                                imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
//                                mBinding.imageViewBranchLogo.setImageDrawable(imageDrawable);
//                            }
//
//                            @Override
//                            public void onError() {
//                            }
//                        });
//            }
//
//            if (!AccountManager.getInstance().getCurrentFund().getBranchCode().equals(""))
//                mBinding.textViewBranch.setText(AccountManager.getInstance().getCurrentFund().getBranchName());
//            else
//                AccountManager.getInstance().setCurrentFund(destinations.get(0));
//            if (dialogBranches != null)
//                dialogBranches.dismiss();
//        });
//
//        adapter.setOnFavoriteSelectListener((branch, position) -> {
//            mBranchListBinding.editTextSearch.setText(null);
//            FundManager.getInstance().setFavoriteFundToDB(branch);
//            destinations = FundManager.getInstance().readBranchs();
//            arraySearch.setItems(destinations, () -> {
//                adapter.setList(destinations);
//                adapter.setPositionSeparator(FundManager.getInstance().getCountFavorite());
//                adapter.notifyDataSetChanged();
//            });
//        });
//        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(adapter);
//        mBranchListBinding.recyclerBranchList.setLayoutManager(layoutManager);
//        animationAdapter.setDuration(250);
////        animationAdapter.setFirstOnly(true);
//        mBranchListBinding.recyclerBranchList.setAdapter(animationAdapter);
//        dialogBranches.sho;
    }

    public interface DestinationClick {
        void onDestinationClick(Destination branch);
    }
}
