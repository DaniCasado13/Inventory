package com.example.inventory.data.repository;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.dependency.DependencyListContract;

import java.util.ArrayList;

public class DependencyRepository implements DependencyListContract.Repository {
    private DependencyListContract.OnDependencyCallback callback;
    private static DependencyRepository repository;
    private ArrayList<Dependency> list;

    private DependencyRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        list.add(new Dependency("Sistemas de Gestion empresarial", "SGEMP", null, null));
        list.add(new Dependency("Desarrollo de interfaces", "DEINT", null, null));
        list.add(new Dependency("Programacion Multimedia", "PMDMO", null, null));
        list.add(new Dependency("Programacion de procesos", "PSPRO", null, null));
        list.add(new Dependency("Acceso a Datos", "ACDAT", null, null));
    }

    public static DependencyRepository getInstance(DependencyListContract.OnDependencyCallback callback){
        if (repository == null){
            repository = new DependencyRepository();
        }
        repository.callback = callback;
        return repository;
    }

    @Override
    public void getList() {
        callback.onSucces(list);
    }

    @Override
    public void delete(Dependency dependency) {

    }

    @Override
    public void undo(Dependency dependency) {

    }
}
