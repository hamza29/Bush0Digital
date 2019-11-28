package com.glowingsoft.bushdigital.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glowingsoft.bushdigital.CategoriesModel;
import com.glowingsoft.bushdigital.CategoryModelLocal;
import com.glowingsoft.bushdigital.CategoryModelMainList;
import com.glowingsoft.bushdigital.MainActivity;
import com.glowingsoft.bushdigital.MyApp;
import com.glowingsoft.bushdigital.R;
import com.glowingsoft.bushdigital.SplashScreenActivity;
import com.squareup.picasso.Picasso;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.objectbox.Box;
import io.objectbox.query.Query;

public class DynamicFragment extends Fragment {

    View view;
    Box<CategoryModelLocal> categoryModelLocalBox;
    Box<CategoryModelMainList> categoryModelMainListBox;
    SlimAdapter slimAdapter;
    RecyclerView list;

    public static DynamicFragment newInstance(String val) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString("someValue", val);
        fragment.setArguments(args);
        return fragment;
    }

    List<CategoryModelLocal> categorysublist = new ArrayList<>();
    List<CategoryModelLocal> categoryModelLocals = new ArrayList<>();
    List<CategoryModelMainList> categorymainlist = new ArrayList<>();
    List<CategoriesModel> categoriesModels = new ArrayList<>();
    String node;
    Box<CategoriesModel> siteModelBox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        node = getArguments().getString("someValue");
        categoryModelLocalBox = ((MyApp) Objects.requireNonNull(getActivity()).getApplication())
                .getBoxStore()
                .boxFor(CategoryModelLocal.class);
        categoryModelMainListBox = ((MyApp) Objects.requireNonNull(getActivity()).getApplication())
                .getBoxStore()
                .boxFor(CategoryModelMainList.class);
        siteModelBox = ((MyApp) Objects.requireNonNull(getActivity()).getApplication())
                .getBoxStore()
                .boxFor(CategoriesModel.class);
        Log.e("TGED", "SIZE" + node);
        list = view.findViewById(R.id.list);
        categorysublist.clear();
        categorymainlist.clear();
        categoriesModels.clear();
        categoryModelLocals.clear();
        categorymainlist = categoryModelMainListBox.getAll();
        categorysublist = categoryModelLocalBox.getAll();
        categoriesModels = siteModelBox.getAll();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(linearLayoutManager);
        slimAdapter = SlimAdapter.create()
                .register(R.layout.row_item_videp, new SlimInjector<CategoryModelLocal>() {
                    @Override
                    public void onInject(final CategoryModelLocal data, IViewInjector injector) {
                        if (node.equalsIgnoreCase(data.getCategory())) {
                            injector.text(R.id.product_des, "" + data.getDescription());
                            injector.text(R.id.product_name, "" + data.getTitle());
                            injector.with(R.id.product_image, new IViewInjector.Action() {
                                @Override
                                public void action(View view) {
                                    Picasso.get().load(data.getImage()).into((ImageView) view);
                                }
                            });
                            Log.e("TGED", "one" + data.getPOne());

                            injector.clicked(R.id.product_more, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.putExtra("product_name", data.getTitle());
                                    intent.putExtra("product_des", data.getDescription());
                                    intent.putExtra("product_image", data.getImage());
                                    intent.putExtra("Ones", "$ "+ data.getPOne()+" for 1 Day");
                                    intent.putExtra("Twos", "$ "+data.getPTwo()+" for 2 Day");
                                    intent.putExtra("Threes","$ "+ data.getPThree()+" for 3 Day");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }).attachTo(list).updateData(categorysublist);


        return view;
    }
}