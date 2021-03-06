package com.example.inventory.ui.dependency;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.base.BaseDialogFragment;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentDependencyListBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DependencyListFragment extends Fragment implements DependencyListContract.view ,DependencyAdapter.OnManageDependencyListener{
    FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;
private DependencyListContract.Presenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Se indica a la activity que se va a modificar el menu
        setHasOptionsMenu(true);
        presenter=new DependencyListPresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter=null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_dependency_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_order_dependency:
                Toast.makeText(getActivity(), "Opcion ordenar dependencias", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDependencyListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvDependency();
    }

    /**
     * M??todo que inicializa el componente recyclerview
     */
    private void initRvDependency() {
        //1- Inicializo el adapter
        adapter = new DependencyAdapter(new ArrayList<>(),this);
        //2- Indico el dise??o del recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //3- Asigno Layout al recyclerview
        binding.rvDependency.setLayoutManager(linearLayoutManager);
        //4- Asigno adapter al recyclerview
        binding.rvDependency.setAdapter(adapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <t> void onSucces(List<t> list) {

    }

    @Override
    public void onDeleteSuccess(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }

    @Override
    public void showData(List<Dependency> list) {
        adapter.update(list);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void onEditDependency(Dependency dependency) {
        Snackbar.make(getView(), "Se ha realizado una pulsaci??n CORTA", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteDependency(Dependency dependency) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, "Elimminar Elemento");
        bundle.putString(BaseDialogFragment.MESSAGE, "??Desea eliminar el elemento: " + dependency.getName() + "?");
        //Conectar el dialogFragment en el grefico de navegacion para poder navegar
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Si la respuesta es true, se realiza lo que programemos aqui
                if (bundle.getBoolean(BaseDialogFragment.KEY_BUNDLE)){
                    presenter.delete(dependency);
                }
            }
        });
       // NavHostFragment.findNavController(this).navigate(R.id.action_dependencyListFragment_to_baseDialogFragment, bundle);
        //
    }
}