package com.example.myreditt.base;

/**
 * Created by Sanoop
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
    void showError(String error);

}
