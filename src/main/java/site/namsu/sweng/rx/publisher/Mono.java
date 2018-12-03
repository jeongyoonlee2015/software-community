package site.namsu.sweng.rx.publisher;

import site.namsu.sweng.rx.scheduler.Scheduler;
import site.namsu.sweng.rx.subscription.Subscription;

import java.util.Collections;
import java.util.concurrent.Flow;

/**
 * @Author : Hyunwoong
 * @When : 2018-12-03 오후 12:23
 * @Homepage : https://github.com/gusdnd852
 */
@SuppressWarnings("unchecked")
public class Mono<T> extends Publisher<T> {
    public static <T> Mono<T> main(T input) {
        return new Mono<>() {
            @Override public void subscribe(Flow.Subscriber<? super T> subscriber) {
                Scheduler.main()
                        .execute(() -> subscriber.onSubscribe(new Subscription<>(subscriber, Collections.singletonList(input))));
            }
        };
    }

    public static <T> Mono<T> background(T input) {
        return new Mono<>() {
            @Override public void subscribe(Flow.Subscriber<? super T> subscriber) {
                Scheduler.background()
                        .execute(() -> subscriber.onSubscribe(new Subscription<>(subscriber, Collections.singletonList(input))));
            }
        };
    }
}