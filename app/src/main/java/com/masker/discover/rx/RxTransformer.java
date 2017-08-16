package com.masker.discover.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: masker
 * Date: 2017/8/16
 * Description:
 */

public class RxTransformer {
    public  static <T> Observable.Transformer<T,T> ioMain(){
      return new Observable.Transformer<T, T>() {
          @Override
          public Observable<T> call(Observable<T> tObservable) {
              return tObservable
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread());
          }
      };
    }
}
