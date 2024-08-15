package com.example.atomi.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.atomi.CustomToolBar;
import com.example.atomi.R;
import com.example.atomi.activity.LoginActivity;
import com.example.atomi.adapter.AllProductAdapter;
import com.example.atomi.adapter.CategoryAdapter;
import com.example.atomi.adapter.NewProductAdapter;
import com.example.atomi.models.AllProductModel;
import com.example.atomi.models.CategoryModel;
import com.example.atomi.models.NewsProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends Fragment {

    RecyclerView catRecyclerView,newProductRecycleView,allProductRecycleView;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    NewProductAdapter newProductAdapter;
    List<NewsProductModel> newsProductModelList;

    AllProductAdapter allProductAdapter;
    List<AllProductModel> allProductModelList;

    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_product, container, false);

        catRecyclerView = root.findViewById(R.id.rec_category);
        newProductRecycleView = root.findViewById(R.id.new_product_rec);
        allProductRecycleView = root.findViewById(R.id.popular_rec);


        CustomToolBar customToolBar = root.findViewById(R.id.custom_toolbar);

        customToolBar.setTitle("Shop đồ thể thao");
        customToolBar.setBgColor(R.color.primary);
        customToolBar.setColorTitle(R.color.white);
        customToolBar.setColorIvBack(R.color.white);
        customToolBar.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content, new UserFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });


        db = FirebaseFirestore.getInstance();
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();


        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner4, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner5, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);



        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(),categoryModelList);
        catRecyclerView.setAdapter(categoryAdapter);


        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        newProductRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newsProductModelList = new ArrayList<>();
        newProductAdapter = new NewProductAdapter(getActivity(),newsProductModelList);
        newProductRecycleView.setAdapter(newProductAdapter);
        db.collection("NewProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){

                                NewsProductModel newsProductModel = document.toObject(NewsProductModel.class);
                                newsProductModelList.add(newsProductModel);
                                newProductAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });



        allProductRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        allProductModelList = new ArrayList<>();
        allProductAdapter = new AllProductAdapter(getContext(),allProductModelList);
        allProductRecycleView.setAdapter(allProductAdapter);
        db.collection("AllProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){

                                AllProductModel allProductModel = document.toObject(AllProductModel.class);
                                allProductModelList.add(allProductModel);
                                allProductAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        return  root;

    }
}