package com.example.inventory.base;

public interface OnRepositoryCallback {
    void onSuccess(String message);
    void onFailure(String message);
}
