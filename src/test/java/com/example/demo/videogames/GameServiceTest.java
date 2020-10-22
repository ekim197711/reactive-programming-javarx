package com.example.demo.videogames;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.jupiter.api.Test;

public class GameServiceTest {

    @Test
    public void gamesForSale() {
        GameService gameService = new GameService();

        Observable<Game> gameObservable = gameService.gamesForSale();
//        gameObservable.subscribe(
//                data -> System.out.println("We got some data: " + data),
//                error -> System.out.println("Error occured... " + error),
//                () -> System.out.println("We are done... Thank you for the data...")
//        );
        Observer observer = new Observer<Game>(){
            Disposable disposable;
            public void doUnSubscribe(){
                disposable.dispose();
            }
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull Game game) {
//                if (game.getName().startsWith("Worm"))
//                    disposable.dispose();
                System.out.println("OnNext =  " + game);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Error observer: " + e);
            }

            @Override
            public void onComplete() {
                System.out.println("completed...");
            }
        };
//
        gameObservable.subscribe(observer);
        gameObservable.filter(game -> game.getStorage() > 0).subscribe(observer);
    }
}