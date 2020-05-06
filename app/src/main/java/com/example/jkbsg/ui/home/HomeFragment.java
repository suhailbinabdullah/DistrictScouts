package com.example.jkbsg.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.jkbsg.R;
import com.example.jkbsg.pojos.home.Model;
import com.example.jkbsg.pojos.home.ModelResult;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    public String TAG = HomeFragment.class.getSimpleName();
    private Context context;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HomeAdapter homeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = root.getContext();
        recyclerView = root.findViewById(R.id.recycler_view);
        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        setStaticData();
        return root;
    }

    private void setStaticData() {
        List<Model> models = new ArrayList<>();
        Model model = new Model();
        model.setId("1");
        model.setTitle("This is the test static data");
        model.setBody("This is test static body, This is test static body,This is test static body,This is test static body,This is test static body,");
        model.setAuthor("static Author");
        model.setTimeStamp("05/05/2020 02:30 PM");
        models.add(model);
        models.add(model);
        ModelResult modelResult = new ModelResult();
        modelResult.setResults(models);
        homeAdapter = new HomeAdapter(context, modelResult);
        recyclerView.setAdapter(homeAdapter);

    }
}
