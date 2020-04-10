package com.example.android.raseel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

/**
 * This Class provides the Adapter to populate pages inside of the ViewPager
 */

public class ServiceAdapter extends PagerAdapter {

    private Context mContext;

    private ArrayList<ServiceModel> mServicesList;


    public ServiceAdapter(ArrayList<ServiceModel> servicesList, Context context) {
        mServicesList = servicesList;
        mContext = context;
    }

    /**
     * This method counts the number of views that are in the data set represented by this Adapter.
     * @return int the number of views available
     */
    @Override
    public int getCount() {
        return mServicesList.size();
    }

    /**
     * This method determines whether a page View is associated with a specific key object as returned by instantiateItem(ViewGroup, int).
     * This method is required for a PagerAdapter to function properly.
     * @param view Page View to check for association with object
     * @param object Object to check for association with view
     * @return boolean true if view is associated with the key object object
     */

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    /**
     *This method creates the page for the given position. The adapter is responsible for adding the view to the container given here,
     *  although it only must ensure this is done by the time it returns from finishUpdate(ViewGroup).
     * @param container ViewGroup: The containing View in which the page will be shown.
     * @param position int: The page position to be instantiated.
     * @return returns an Object representing the new page.
     */

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.service_card, container, false);



        ImageView mImageView = view.findViewById(R.id.service_image);
        TextView mTitleTextView = view.findViewById(R.id.service_title);
        TextView mDescriptionTextView = view.findViewById(R.id.service_description);


        mImageView.setImageResource(mServicesList.get(position).getSerivceImage());
        mTitleTextView.setText(mServicesList.get(position).getServiceTitle());
        mDescriptionTextView.setText(mServicesList.get(position).getServiceDescription());

        container.addView(view, 0);
        return view;


    }

    /**
     * This method remove a page for the given position. The adapter is responsible for removing the view from its container,
     * although it only must ensure this is done by the time it returns from finishUpdate(ViewGroup).
     * @param container ViewGroup: The containing View from which the page will be removed.
     * @param position int: The page position to be removed.
     * @param object Object: The same object that was returned by instantiateItem(View, int).
     */

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


}
