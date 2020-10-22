package com.example.demo.videogames;

import io.reactivex.rxjava3.core.Observable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

    private static final List<Game> GAMES = Arrays.asList(
            new Game("Cheese eater", 299.0d, 12),
            new Game("Space Shooter 2020", 123.0d, 2),
            new Game("Worm hunter", 77.5d, 24),
            new Game("Tea party simulator", 1299.0d, 3),
            new Game("Fishing game 9", 460.5d, 10),
            new Game("Very scary game", 33.0d, 22)
    );

    public Observable<Game> gamesForSale(){
        return Observable.create(emitter -> {
            int i = 0;
            System.out.println("Start sending games");
            while (!emitter.isDisposed() && i < GAMES.size()){
                Game game = GAMES.get(i);
                if (game.getStorage() == 0) {
                    emitter.onError(new RuntimeException("Wopsss... The game is not on stock. " + game));
                }
                emitter.onNext(game);
                i++;
            }
            System.out.println("Done sending games");
            emitter.onComplete();
        });
    }

}
