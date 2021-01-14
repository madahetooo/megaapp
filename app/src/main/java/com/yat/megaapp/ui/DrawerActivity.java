package com.yat.megaapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.yat.megaapp.tools.CalculatorActivity;
import com.yat.megaapp.entertainment.Chat;
import com.yat.megaapp.entertainment.Connect3;
import com.yat.megaapp.entertainment.DogOrCatQuiz;
import com.yat.megaapp.tools.JavaTpointWebsite;
import com.yat.megaapp.tools.MapsActivity;
import com.yat.megaapp.tools.MobileTrackPDF;
import com.yat.megaapp.entertainment.MusicPlayer;
import com.yat.megaapp.entertainment.PassingBioData;
import com.yat.megaapp.R;
import com.yat.megaapp.entertainment.RandomHighestMountains;
import com.yat.megaapp.entertainment.RestaurantActivity;
import com.yat.megaapp.tools.TemperatureConverter;
import com.yat.megaapp.entertainment.VideoPlayer;
import com.yat.megaapp.tools.YatGroup;
import com.yat.megaapp.utils.MainActivity;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView ivProfileImage;
    TextView txUserName, txEmailAddress;

    View header;
    private GoogleSignInClient mSignInClient;
    private FirebaseAuth mFirebaseAuth;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DatabaseReference databaseReference;

    String googleUserName, userEmail , userPhoto;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        View header = navigationView.getHeaderView(0);

        ivProfileImage = header.findViewById(R.id.ivtrika);
        txUserName = header.findViewById(R.id.txUserName);
        txEmailAddress = header.findViewById(R.id.txEmailAddress);


        if (currentUser != null) {
            googleUserName = currentUser.getDisplayName();
            txUserName.setText(googleUserName);
            userEmail = currentUser.getEmail();
            txEmailAddress.setText(userEmail);
//            if (currentUser.getPhotoUrl()==null){
//                ivProfileImage.setImageResource(R.drawable.ic_baseline_account_circle_24);
//
//            }
            userPhoto = String.valueOf(currentUser.getPhotoUrl());
            Picasso.with(this).load(userPhoto).into(ivProfileImage);

        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Chat()).commit();
        navigationView.setCheckedItem(R.id.item_Chat);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(this, gso);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_Chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Chat()).commit();
                break;
            case R.id.item_connect3:
                Intent intent = new Intent(getApplicationContext(), Connect3.class);
                startActivity(intent);
                break;
            case R.id.item_randomHighestMountains:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RandomHighestMountains()).commit();
                break;
            case R.id.item_dogOrCat:
                Intent intent12 = new Intent(getApplicationContext(), DogOrCatQuiz.class);
                startActivity(intent12);
                break;
            case R.id.item_googleMaps:
                Intent intent2 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent2);
                break;
            case R.id.item_javaTpoingWebsite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new JavaTpointWebsite()).commit();
                break;
            case R.id.item_music:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MusicPlayer()).commit();
                break;
            case R.id.item_passingBioData:
                Intent intent3 = new Intent(getApplicationContext(), PassingBioData.class);
                startActivity(intent3);
                break;
            case R.id.item_pdfView:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MobileTrackPDF()).commit();
                break;
            case R.id.item_restaurant:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RestaurantActivity()).commit();
                break;
            case R.id.item_tempConverter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TemperatureConverter()).commit();
                break;
            case R.id.item_yatGroup:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new YatGroup()).commit();
                break;
            case R.id.item_video:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new VideoPlayer()).commit();
                break;

            case R.id.item_calculator:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalculatorActivity()).commit();
                break;

            case R.id.item_signOut:

                FirebaseAuth mAuth;
                mAuth=FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent inten = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(inten);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}