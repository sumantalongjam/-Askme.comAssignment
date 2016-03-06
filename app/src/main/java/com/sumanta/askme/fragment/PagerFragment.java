package com.sumanta.askme.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.sumanta.askme.R;
import com.sumanta.askme.entities.ItemEntity;

/**
 * Created by sumanta on 5/3/16.
 */
public class PagerFragment extends Fragment {

    private ItemEntity itemEntity;
    public static PagerFragment newInstance(ItemEntity itemEntity) {
        PagerFragment pagerFragment = new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("itemEntity", itemEntity);
        pagerFragment.setArguments(bundle);
        return pagerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemEntity = getArguments() != null ? (ItemEntity)getArguments().getSerializable("itemEntity") : null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pager_fragment, container, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.template_imageView);
        TextView textView = (TextView)view.findViewById(R.id.template_body_label);
        if(itemEntity!=null) {
            Picasso.with(getActivity()).load(itemEntity.getImageUrl())
                    .fit()
                    .centerCrop()
                    .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
            textView.setText(itemEntity.getLabel());
        }
        return view;
    }
}
