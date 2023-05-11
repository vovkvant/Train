package com.example.app.db;

import com.example.app.entity.Train;

import java.util.ArrayList;

public class ListBase {

    public static ArrayList<Train> createTrainList() {

        ArrayList<Train> trains = new ArrayList<>();

        trains.add(new Train(123L, "234", 43));
        trains.add(new Train(456L, "546", 89));
        trains.add(new Train(489L, "689", 97));

        return trains;
    }

    public static Train findTrainById(Long id) {

        ArrayList<Train> trains = createTrainList();
        Train neededTrain = new Train();
        for (Train train : trains) {
            Long currentId = train.getId();
            if (currentId.equals(id)) {
                neededTrain = train;
                break;
            }
        }
        return neededTrain;
    }

}
