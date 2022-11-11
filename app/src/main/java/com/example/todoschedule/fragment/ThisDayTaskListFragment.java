package com.example.todoschedule.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoschedule.DeleteTaskListener;
import com.example.todoschedule.ItemRecyclerViewAdapter;
import com.example.todoschedule.R;
import com.example.todoschedule.viewmodel.TaskViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThisDayTaskListFragment extends Fragment implements DeleteTaskListener {

    private ItemRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private TaskViewModel taskViewModel;
    private final String TAG = "ThisDayTaskListFragment";
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_this_day_task_list, container, false);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.thisday_task_list_view);
        adapter = new ItemRecyclerViewAdapter();
        adapter.setDeleteTaskListener(this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        disposable.add(taskViewModel.getAllTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasks -> adapter.setTaskList(tasks),
                        throwable -> Log.e(TAG, "Unable to read tasks",throwable)));
    }

    @Override
    public void onStop() {
        super.onStop();
        disposable.clear();
    }

    @Override
    public void onClickDeleteTask(int position) {
        disposable.add(taskViewModel.delete(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, throwable -> Log.e(TAG,
                        "Unable to delete", throwable)));
    }


}