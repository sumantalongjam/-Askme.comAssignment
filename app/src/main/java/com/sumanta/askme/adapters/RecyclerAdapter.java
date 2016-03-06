package com.sumanta.askme.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.sumanta.askme.R;
import com.sumanta.askme.activity.MainActivity;
import com.sumanta.askme.component.CirclePageIndicator;
import com.sumanta.askme.entities.DataEntity;
import com.sumanta.askme.entities.ItemEntity;
import java.util.ArrayList;

/**
 * Created by sumanta on 5/3/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TEMPLATE_ONE = 1;
    private final int TEMPLATE_TWO = 2;
    private final int TEMPLATE_THREE = 3;
    private Context context;
    private ArrayList<DataEntity> dataList;

    public RecyclerAdapter(Context context, ArrayList<DataEntity> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1: {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_template_one, parent, false);
                return new TemplateOneViewHolder(view);
            }
            case 2: {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_template_two, parent, false);
                return new TemplateTwoViewHolder(view);
            }
            case 3: {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_template_three, parent, false);
                return new TemplateThreeViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataEntity dataEntity = dataList.get(position);
        if (dataEntity != null) {
            switch (getItemViewType(position)) {
                case TEMPLATE_ONE : {
                    ItemEntity itemEntity = dataEntity.getItemList().get(0);
                    ((TemplateOneViewHolder) holder).template_one_label.setText(dataEntity.getLabel() != null ? dataEntity.getLabel() : "");
                    Picasso.with(context).load(itemEntity.getImageUrl())
                            .fit()
                            .centerCrop()
                            .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
                            .into(((TemplateOneViewHolder) holder).template_one_imageView);
                    ((TemplateOneViewHolder) holder).template_one_body_label.setText(itemEntity.getLabel());
                    break;
                }
                case TEMPLATE_TWO : {
                    ArrayList<ItemEntity> itemList = dataEntity.getItemList();
                    TemplateTwoPagerAdapter templateTwoPagerAdapter = new TemplateTwoPagerAdapter(((MainActivity)context).getSupportFragmentManager(), itemList);
                    ((TemplateTwoViewHolder) holder).template_two_viewpager.setId(position+1);
                    ((TemplateTwoViewHolder) holder).template_two_viewpager.setAdapter(templateTwoPagerAdapter);
                    ((TemplateTwoViewHolder) holder).template_two_label.setText(dataEntity.getLabel());
                    break;
                }
                case TEMPLATE_THREE : {
                    ArrayList<ItemEntity> itemList = dataEntity.getItemList();
                    TemplateThreePagerAdapter pagerAdapter = new TemplateThreePagerAdapter(((MainActivity)context).getSupportFragmentManager(), itemList);
                    ((TemplateThreeViewHolder) holder).template_three_viewpager.setId(position+1);
                    ((TemplateThreeViewHolder) holder).template_three_viewpager.setAdapter(pagerAdapter);
                    ((TemplateThreeViewHolder) holder).template_three_indicator.setViewPager(((TemplateThreeViewHolder) holder).template_three_viewpager);
                    ((TemplateThreeViewHolder) holder).template_three_label.setText(dataEntity.getLabel());
                    break;
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList != null) {
            String template = dataList.get(position).getTemplate();
            if (!TextUtils.isEmpty(template)) {
                return "product-template-1".equals(template) ? TEMPLATE_ONE
                        : "product-template-2".equals(template) ? TEMPLATE_TWO
                        : "product-template-3".equals(template) ? TEMPLATE_THREE : 0;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class TemplateOneViewHolder extends RecyclerView.ViewHolder {
        private TextView template_one_label;
        private ImageView template_one_imageView;
        private TextView template_one_body_label;
        public TemplateOneViewHolder(View view) {
            super(view);
            this.template_one_label = (TextView) view.findViewById(R.id.template_one_label);
            this.template_one_imageView = (ImageView) view.findViewById(R.id.template_one_imageView);
            this.template_one_body_label = (TextView) view.findViewById(R.id.template_one_body_label);
        }
    }

    public class TemplateTwoViewHolder extends RecyclerView.ViewHolder {
        private TextView template_two_label;
        private ViewPager template_two_viewpager;
        public TemplateTwoViewHolder(View view) {
            super(view);
            this.template_two_viewpager = (ViewPager) view.findViewById(R.id.template_two_viewpager);
            int pagerPadding = 20;
            this.template_two_viewpager.setClipToPadding(false);
            this.template_two_viewpager.setPadding(pagerPadding, 0, pagerPadding, 0);
            this.template_two_viewpager.setPageMargin(20);
            this.template_two_label = (TextView) view.findViewById(R.id.template_two_label);
        }
    }

    public class TemplateThreeViewHolder extends RecyclerView.ViewHolder {
        private TextView template_three_label;
        private ViewPager template_three_viewpager;
        private CirclePageIndicator template_three_indicator;
        public TemplateThreeViewHolder(View view) {
            super(view);
            this.template_three_label = (TextView) view.findViewById(R.id.template_three_label);
            this.template_three_viewpager = (ViewPager) view.findViewById(R.id.template_three_viewpager);
            this.template_three_indicator = (CirclePageIndicator) view.findViewById(R.id.template_three_indicator);
        }
    }
}
