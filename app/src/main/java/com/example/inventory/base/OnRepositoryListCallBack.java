package com.example.inventory.base;

import java.util.List;

public interface OnRepositoryListCallBack {
    void onFailure(String message);
    //El tipo debe definirse en la interfaz que lo necesite
    <t> void onSucces(List<t> list);
    void onDeleteSuccess(String message);
    void onUndoSuccess(String message);
}
