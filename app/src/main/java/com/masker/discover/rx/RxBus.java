package com.masker.discover.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description:
 */

public class RxBus {
    private final Subject<Object,Object> mBus;
    private static volatile RxBus sInstance;

    private RxBus(){
        mBus = new SerializedSubject<>(PublishSubject.create());
    }


    public static RxBus getInstance(){
        if(sInstance == null){
            synchronized (RxBus.class){
                if(sInstance == null){
                    sInstance = new RxBus();
                }
            }
        }
        return sInstance;
    }

    /**
     * send event
     * @param event
     */
    public static void post(Object event){
        getInstance().mBus.onNext(event);
    }

    /**
     * receive event
     * @param klass
     * @param <T>
     * @return
     */
    public static <T> Observable<T> toObservable(Class<T> klass){
        return getInstance().mBus.ofType(klass);
    }

}
