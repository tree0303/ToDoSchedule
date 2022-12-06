package com.example.todoschedule.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoschedule.DeleteTaskListener;
import com.example.todoschedule.R;
import com.example.todoschedule.database.Task;
import com.example.todoschedule.fragment.adapter.AfterMonth;
import com.example.todoschedule.fragment.adapter.AfterThisMonthItemRecyclerViewAdapter;
import com.example.todoschedule.fragment.adapter.AssortmentOfDateTime;
import com.example.todoschedule.fragment.adapter.ThisDay;
import com.example.todoschedule.fragment.adapter.ThisDayItemRecyclerViewAdapter;
import com.example.todoschedule.fragment.adapter.ThisMonth;
import com.example.todoschedule.fragment.adapter.ThisMonthItemRecyclerViewAdapter;
import com.example.todoschedule.fragment.adapter.ThisWeek;
import com.example.todoschedule.fragment.adapter.ThisWeekItemRecyclerViewAdapter;
import com.example.todoschedule.viewmodel.TaskViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TaskListFragment extends Fragment implements DeleteTaskListener, AssortmentOfDateTime {

    private ThisDayItemRecyclerViewAdapter dayadapter;
    private ThisWeekItemRecyclerViewAdapter weeekadapter;
    private ThisMonthItemRecyclerViewAdapter monthadapter;
    private AfterThisMonthItemRecyclerViewAdapter aftermonthkadapter;
    private ThisDay thisDay;
    private ThisWeek thisWeek;
    private ThisMonth thisMonth;
    private AfterMonth afterMonth;
    private RecyclerView recyclerView;
    private TaskViewModel taskViewModel;
    private final String TAG = "TaskListFragment";
    private CompositeDisposable disposable = new CompositeDisposable();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        thisDay = new ThisDay();
        dayadapter = new ThisDayItemRecyclerViewAdapter();
        thisWeek = new ThisWeek();
        weeekadapter = new ThisWeekItemRecyclerViewAdapter();
        thisMonth = new ThisMonth();
        monthadapter = new ThisMonthItemRecyclerViewAdapter();
        afterMonth = new AfterMonth();
        aftermonthkadapter = new AfterThisMonthItemRecyclerViewAdapter();

        dayadapter.setDeleteTaskListener(this);
        weeekadapter.setDeleteTaskListener(this);
        monthadapter.setDeleteTaskListener(this);
        aftermonthkadapter.setDeleteTaskListener(this);


        recyclerView = view.findViewById(R.id.task_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ConcatAdapter concatAdapter = new ConcatAdapter(thisDay, dayadapter,
                thisWeek, weeekadapter,
                thisMonth, monthadapter,
                afterMonth, aftermonthkadapter);

        recyclerView.setAdapter(concatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        disposable.add(taskViewModel.getAllTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasks -> {
                    dayadapter.setTaskList(getThisDayTaskList(tasks));
                    weeekadapter.setTaskList(getThisWeekTaskList(tasks));
                    monthadapter.setTaskList(getThisMonthTaskList(tasks));
                    aftermonthkadapter.setTaskList(getAfterThisMonthTaskList(tasks));
                },
                        throwable -> Log.e(TAG, "Unable to read tasks",throwable)));
    }

    @Override
    public void onStop() {
        super.onStop();
        disposable.clear();
    }


    @Override
    public void onClickDeleteTask(Task task) {
        disposable.add(taskViewModel.delete(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, throwable -> Log.e(TAG, "Unable to delete", throwable)));
    }

}