package com.elysino.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.elysino.rxjava.metwork.MyWebServices;
import com.elysino.rxjava.model.Food;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SimpleFood extends AppCompatActivity {
  //  private CompositeDisposable disposable = new CompositeDisposable();
    RecyclerView recyclerView;
    DetailsAdapter detailsAdapter;
    List<Food> foodArrayList;
    MyWebServices myWebServices;
    Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_food);

        myWebServices = MyWebServices.retrofit.create(MyWebServices.class);

        recyclerView=findViewById(R.id.recyclerView);
        foodArrayList=new ArrayList<>();
        detailsAdapter=new DetailsAdapter(this,foodArrayList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(detailsAdapter);

        getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Food>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable=d;
            }

            @Override
            public void onNext(@NonNull List<Food> foodList) {

                getObserver(foodList);
                Log.d("size",""+foodList.size());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                onFailure(e);
            }
            @Override
            public void onComplete() {
            }
        });
    }

    private Observable<List<Food>> getObservable()
    {
        return myWebServices.getAllFoodList();
    }

    private void getObserver(List<Food> foodList)
    {
        if(foodList!= null && foodList.size()!=0)
        {
            detailsAdapter.setData(foodList);
        }
    }

    private void onFailure(Throwable t)
    {
        Log.d("main", "onFailure: "+t.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}