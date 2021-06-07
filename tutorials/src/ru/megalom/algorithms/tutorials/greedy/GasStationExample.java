package ru.megalom.algorithms.tutorials.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Пример жадного алогритма для нахождения оптимального числа заправочных станций, на которых
// необходимо сделать остановку чтобы доехать из пункта А в Пункт Б
public class GasStationExample {
    public static void main(String[] args) {
        float[] gasStationDistances={0.0F,200.0F,375.0F,550.0F,750.0F,950.0F};
        float maxRange = 400.0F;
        System.out.println("Даны расстояния: "+ Arrays.toString(gasStationDistances));
        System.out.println("Оптимальным будет маршрут через следующие станции:"+
                Arrays.toString(getOptimalStations(gasStationDistances,maxRange)));
    }

    // Возвращает массив из рассотяний от пункта А в пункт Б на которых необходимо остановиться
    // для заправки
    //O(n)
    public static float[] getOptimalStations(float[] gasStationDistances,float maxRange){
        List<Float> stationsList = new ArrayList<>();
        float fuelLeft=maxRange;
        //Проходим чере все станции и если топлива не хватит до следующей станции, то заправляемся
        // O(n)
        for(int i =0;i<gasStationDistances.length-1;i++){
            float distanceToNextStation=gasStationDistances[i+1]-gasStationDistances[i];
            if(distanceToNextStation>fuelLeft) {
                if(distanceToNextStation>maxRange)
                    throw new RuntimeException(
                            "Расстояние до следующей станции больше," +
                                    " чем может проехать машина на полном баке");
                stationsList.add(gasStationDistances[i]);
                fuelLeft=maxRange-distanceToNextStation;

            }
            else {
                fuelLeft -= distanceToNextStation;
            }
        }
        // если нужна конечная станция в списке то добавить
        // stationsList.add(gasStationDistances[gasStationDistances.length-1]);

        // Помещаем список точек с заправочными станциями в массив
        // O(n)
        float[] optimalStations=new float[stationsList.size()];
        for(int i=0;i<optimalStations.length;i++){
            optimalStations[i]=stationsList.get(i);
        }
        return optimalStations;
    }
}
