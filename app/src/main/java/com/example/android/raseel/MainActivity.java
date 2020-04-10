package com.example.android.raseel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * This class is responsible for creating and displaying the layout of the first screen
 * to appear when the user launches the app
 */

public class MainActivity extends AppCompatActivity {

    private ServiceAdapter mServiceAdapter;

    private ViewPager mCardViewPager;

    private TabLayout mTabLayout;

    private ImageView mPhone;

    private ImageView mEnail;

    private ImageView mAddress;

    private ImageView mWorkingHours;

    private Dialog mDialog;

    private ImageView mCloseButton;

    private TextView mPopUpIntroduction;

    private TextView mPopUpText;

    private Button mPopUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing all the Object Variables

        mPhone = findViewById(R.id.phone_button);

        mEnail = findViewById(R.id.email_button);

        mAddress = findViewById(R.id.address_button);

        mWorkingHours = findViewById(R.id.working_hours_button);

        mDialog = new Dialog(this);

        mDialog.setContentView(R.layout.popup_card);

        mCloseButton = mDialog.findViewById(R.id.close_button);

        mPopUpIntroduction = mDialog.findViewById(R.id.popup_card_introduction);

        mPopUpText = mDialog.findViewById(R.id.popup_text);

        mPopUpButton = mDialog.findViewById(R.id.popup_button);


        //Creating an ArrayList of all the Services that will be displayed by the ViewPager in the form of Cards

        ArrayList<ServiceModel> servicesList = new ArrayList<>();

        servicesList.add(new ServiceModel(R.drawable.service_one, getString(R.string.service_one_title), getString(R.string.service_one_description)));
        servicesList.add(new ServiceModel(R.drawable.service_two, getString(R.string.service_two_title), getString(R.string.service_two_description)));
        servicesList.add(new ServiceModel(R.drawable.service_three, getString(R.string.service_three_title), getString(R.string.service_three_description)));
        servicesList.add(new ServiceModel(R.drawable.service_four, getString(R.string.service_four_title), getString(R.string.service_four_description)));
        servicesList.add(new ServiceModel(R.drawable.service_five, getString(R.string.service_five_title), getString(R.string.service_five_description)));
        servicesList.add(new ServiceModel(R.drawable.service_six, getString(R.string.service_six_title), getString(R.string.service_six_description)));


        // initializing the Viewpager, TabLayout, and the Adapter

        mCardViewPager = findViewById(R.id.viewpager);

        mServiceAdapter = new ServiceAdapter(servicesList, this);

        mTabLayout = findViewById(R.id.indicator);


        //Setting the Adapter to the ViewPager through the setAdapter method

        mCardViewPager.setAdapter(mServiceAdapter);


        // Setting up the TabLayout with the ViewPager

        mTabLayout.setupWithViewPager(mCardViewPager, true);


        //Attaching an OnClickListener to the phone button that determines the behavior that will happen
        // when the user clicks on that button

        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCallUsCard();

            }
        });

        //Attaching an OnClickListener to the email button that determines the behavior that will happen
        // when the user clicks on that button

        mEnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmailCard();
            }
        });

        //Attaching an OnClickListener to the address button that determines the behavior that will happen
        // when the user clicks on that button

        mAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddressCard();
            }
        });

        //Attaching an OnClickListener to the working hours button that determines the behavior that will happen
        // when the user clicks on that button

        mWorkingHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWorkingHoursCard();
            }
        });

        //Attaching an OnClickListener to the close button that will dismiss or close the popup card/dialog

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });



    }

    /**
     * showCallUsCard is a method that is called when the phone button is clicked where it displays the contact
     * information on the pop up card/dialog along with a "Give Us A Call" button
     */


    private void showCallUsCard() {

        //setting the visibility of mPopUpText Object to visible
        // because it will be set to GONE in case the user has already clicked on the address or working hours buttons
        // so it needs to be set to visible again so that we can set the text on it
        mPopUpText.setVisibility(View.VISIBLE);

        mPopUpIntroduction.setText(R.string.phone_card_introduction);
        mPopUpText.setText(R.string.phone_number);

        //setting the visibility of mPopUpButton to visible
        //because it will be set to GONE in case the user has already clicked on the address or working hours buttons
        //so it needs to be visible again so that we can set the text and OnClickListener on it

        mPopUpButton.setVisibility(View.VISIBLE);
        mPopUpButton.setText(R.string.call_button);
        mPopUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initializing a phone call intent to open the dialer/phone app and dial a phone number

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+20226079434"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //setting the background of the dialog window to transparent

        if (mDialog.getWindow() != null) {
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mDialog.show();

    }

    /**
     * showEmailCard is a method that is called when the email button is clicked where it displays the email
     * text on the pop up card/dialog along with a "Send Us An Email" button
     *
     */


    private void showEmailCard() {
        mPopUpText.setVisibility(View.VISIBLE);

        mPopUpIntroduction.setText(R.string.email_card_introduction);
        mPopUpText.setText(R.string.email);

        mPopUpButton.setVisibility(View.VISIBLE);
        mPopUpButton.setText(R.string.email_button);
        mPopUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initializing an email intent to compose an email specifying both the email recipient and subject

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

                emailIntent.setData(Uri.parse("mailto:")); // only email apps should handle this


                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] {getString(R.string.email_recipient)});

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));

                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }

            }
        });

        if (mDialog.getWindow() != null) {
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mDialog.show();

    }

    /**
     * showAddressCard is a method that is called when the address button is clicked where it displays the address
     * text on the pop up card/dialog
     */


    private void showAddressCard() {



        mPopUpIntroduction.setText(R.string.address);

        //setting the visibility of mPopUpText and mPopupButton to GONE to show the text in the mPopUpIntroduction TextView only
        mPopUpText.setVisibility(View.GONE);
        mPopUpButton.setVisibility(View.GONE);


        if (mDialog.getWindow() != null) {
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        mDialog.show();

    }

    /**
     * showWorkingHoursCard is a method that is called when the working hours button is clicked where it displays the
     * working hours on the pop up card/dialog
     */

    private void showWorkingHoursCard() {

        mPopUpIntroduction.setText(R.string.working_hours);

        //setting the visibility of mPopUpText and mPopupButton to GONE to show the text in the
        // mPopUpIntroduction TextView only

        mPopUpText.setVisibility(View.GONE);
        mPopUpButton.setVisibility(View.GONE);

        if (mDialog.getWindow() != null) {
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mDialog.show();

    }

}

